package com.example.controller;

import com.example.dto.UsuarioDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SubscriptionPageController {

    @GetMapping("/Subscription")
    public ModelAndView loginPage() {
        ModelAndView mv = new ModelAndView("subscriptionPage");
        return mv;
    }

    @PostMapping("/SendFormRegister")
    public ModelAndView SendForm(String email, String senha) {

        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setEmail(email);
        usuario.setSenha(senha);

        System.out.println(usuario.getEmail() + " " + usuario.getSenha());

        ModelAndView mv = new ModelAndView("TelaSucesso");
        return mv;
    }

}
