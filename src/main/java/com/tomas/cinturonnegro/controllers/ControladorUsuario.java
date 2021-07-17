package com.tomas.cinturonnegro.controllers;

import com.tomas.cinturonnegro.models.User;
import com.tomas.cinturonnegro.services.ServicioUsuario;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class ControladorUsuario {

    //1 = admin
    //2 = usuario
    private final ServicioUsuario servicioUsuario;

    public ControladorUsuario(ServicioUsuario servicioUsuario) {
        this.servicioUsuario = servicioUsuario;
    }

    @GetMapping("/")
    public String index(@ModelAttribute("user")User user, HttpSession session){
        if(session.getAttribute("idusuario") != null){
            return "redirect:/packages";
        }else{
            return "index.jsp";
        }
    }

    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session){
        userValidator.validate(user, result);
        if(result.hasErrors()){
            return "index.jsp";
        }
        if(servicioUsuario.emailExist(user.getEmail())){

        }
    }
}
