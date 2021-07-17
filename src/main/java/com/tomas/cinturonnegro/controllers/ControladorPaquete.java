package com.tomas.cinturonnegro.controllers;

import com.tomas.cinturonnegro.models.Paquete;
import com.tomas.cinturonnegro.models.User;
import com.tomas.cinturonnegro.services.ServicioPaquete;
import com.tomas.cinturonnegro.services.ServicioUsuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class ControladorPaquete {

    private final ServicioUsuario servicioUsuario;
    private final ServicioPaquete servicioPaquete;

    public ControladorPaquete(ServicioUsuario servicioUsuario, ServicioPaquete servicioPaquete) {
        this.servicioUsuario = servicioUsuario;
        this.servicioPaquete = servicioPaquete;
    }

    //1 = admin
    //2 = usuario

    @GetMapping("/packages")
    public String paquete(HttpSession session, @ModelAttribute("paquete") Paquete paquete, Model model){
        if(session.getAttribute("idusuario") == null){
            return "redirect:/";
        }
        User user = servicioUsuario.findUserById((Long) session.getAttribute("idusuario"));
        if(user.getRol() == 2){
            return "redirect:/users/"+user.getId();
        }else{
            List<User> usuarios = servicioUsuario.allData();
            List<Paquete> paquetes = servicioPaquete.allData();
            model.addAttribute("usuarios", usuarios);
            model.addAttribute("paquetes", paquetes);
            return "paquetes.jsp";
        }
    }

    @PostMapping("/packages")
    public String agregarPaquete(@Valid @ModelAttribute("paquete") Paquete paquete, BindingResult result, Model model){
        if(result.hasErrors()){
            return "paquetes.jsp";
        }else{
            List<User> usuarios = servicioUsuario.allData();
            List<Paquete> paquetes = servicioPaquete.allData();
            model.addAttribute("usuarios", usuarios);
            model.addAttribute("paquetes", paquetes);
            servicioPaquete.create(paquete);
            return "redirect:/packages";
        }
    }




}
