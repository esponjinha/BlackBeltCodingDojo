package com.tomas.cinturonnegro.controllers;

import com.tomas.cinturonnegro.models.Paquete;
import com.tomas.cinturonnegro.models.User;
import com.tomas.cinturonnegro.services.ServicioPaquete;
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
import java.util.Calendar;
import java.util.Date;

@Controller
public class ControladorUsuario {

    //1 = admin
    //2 = usuario
    private final ServicioUsuario servicioUsuario;
    private final UserValidator userValidator;
    private final ServicioPaquete servicioPaquete;

    public ControladorUsuario(ServicioUsuario servicioUsuario, UserValidator userValidator, ServicioPaquete servicioPaquete) {
        this.servicioUsuario = servicioUsuario;
        this.userValidator = userValidator;
        this.servicioPaquete = servicioPaquete;
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
            return "index.jsp";
        } else {
            if(servicioUsuario.count() > 0) {
                user.setRol(2);
                User usuario = servicioUsuario.registerUser(user);
                Paquete paquete = servicioPaquete.findByName("Basico");
                usuario.setPaquete(paquete);
                Calendar hoy= Calendar.getInstance();
                hoy.add(Calendar.DATE, 30);
                Date fecha=hoy.getTime();
                usuario.setFechaPago(fecha);
                servicioUsuario.update(usuario);
                session.setAttribute("idusuario", usuario.getId());
                return "redirect:/users/" + usuario.getId();
            } else {
                user.setRol(1);
                Paquete paquete = new Paquete();
                paquete.setPackageName("Basico");
                servicioPaquete.create(paquete);
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
            session.setAttribute("idusuario", usuario.getId());
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

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}
