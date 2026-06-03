package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import jakarta.servlet.http.HttpSession;

@Controller
public class ProfileController {

    @GetMapping("/profile")
    public String profile(HttpSession session) {

        final Logger logger = LoggerFactory.getLogger(ResetPaswordController.class);


        if(session.getAttribute("usuarioLogado") == null){
            return "redirect:/login";
        }

        logger.info("O usuario com email:" +session.getAttribute("email")+ " acessou a página de perfil");

        return "profile";
    }

}
