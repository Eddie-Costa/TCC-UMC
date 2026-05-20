package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import jakarta.servlet.http.HttpSession;

@Controller
public class ProfileController {

    @GetMapping("/profile")
    public String profile(HttpSession session) {

//        if(session.getAttribute("usuarioLogado") == null){
//            return "redirect:/login";
//        }

        return "profile";
    }

}
