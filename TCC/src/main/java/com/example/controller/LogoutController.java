package com.example.controller;

import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

@Controller
public class LogoutController {

    private static final Logger logger = LoggerFactory.getLogger(LogoutController.class);

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        if(session.getAttribute("usuarioLogado") == null){
            return "redirect:/home";
        }

        logger.info("Sessão encerrada para o usuário: {}", MDC.get("usuario"));
        logger.info("Sessão de ID: {} invalidada", MDC.get("sessionId"));

        MDC.remove("usuario");
        MDC.remove("sessionId");
        session.invalidate();

        return "redirect:/home";
    }
}
