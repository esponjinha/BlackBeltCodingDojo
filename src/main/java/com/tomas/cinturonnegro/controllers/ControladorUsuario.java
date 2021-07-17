package com.tomas.cinturonnegro.controllers;

import com.tomas.cinturonnegro.models.User;
import com.tomas.cinturonnegro.services.ServicioUsuario;
import com.tomas.cinturonnegro.validator.UserValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class ControladorUsuario {

    //1 = admin
    //2 = usuario
    private final ServicioUsuario servicioUsuario;
    private final UserValidator userValidator;

    public ControladorUsuario(ServicioUsuario servicioUsuario, UserValidator userValidator) {
        this.servicioUsuario = servicioUsuario;
        this.userValidator = userValidator;
    }

    @GetMapping("/")
    public String index(@ModelAttribute("user")User user, HttpSession session){
        if(session.getAttribute("idusuario") != null){
            Long idusuario = (Long) session.getAttribute("idusuario");
            User usuario = servicioUsuario.findUserById(idusuario);
            if(usuario.getRol() == 1) {
                return "redirect:/packages";
            } else {
                return "redirect:/users/" + usuario.getId();
            }
        }else{
            return "index.jsp";
        }
    }

    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session, Model model){
        userValidator.validate(user, result);
        if(result.hasErrors()){
            return "index.jsp";
        }
        if(servicioUsuario.emailExist(user.getEmail())){
            String error = "Email ya existe en la base de datos";
            model.addAttribute("error", error);
            // colocar error
            return "index.jsp";
        } else {
            if(servicioUsuario.count() > 0) {
                user.setRol(2);
                User usuario = servicioUsuario.registerUser(user);
                session.setAttribute("idusuario", usuario.getId());
                return "redirect:/users/" + usuario.getId();
            } else {
                user.setRol(1);
                User usuario = servicioUsuario.registerUser(user);
                session.setAttribute("idusuario", usuario.getId());
                return "redirect:/packages";
            }
        }
    }

    @PostMapping("/login")
    public String loginUsuario(@ModelAttribute("user") User user, @RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
        boolean autenticado = servicioUsuario.authenticateUser(email, password);
        if(autenticado) {
            User usuario = servicioUsuario.findByEmail(email);
            session.setAttribute("userId", usuario.getId());
            if(usuario.getRol() == 1) {
                return "redirect:/packages";
            } else {
                return "redirect:/users/" + usuario.getId();
            }
        } else {
            String error = "Datos invalidos";
            model.addAttribute("error", error);
            return "index.jsp";
        }
    }
}
