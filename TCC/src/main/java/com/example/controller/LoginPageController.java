package com.example.controller;

import com.example.dto.UsuarioDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginPageController {

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("usuario", new UsuarioDTO());
        return "loginPage";
    }

    @PostMapping("/login")
    public String fazerLogin(
            @Valid @ModelAttribute("usuario") UsuarioDTO usuario,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return "loginPage";
        }

//        //troca por query
        if ("admin@email.com".equals(usuario.getEmail()) &&
                "123456".equals(usuario.getSenha())) {

            return "telaSucesso";
        }

        model.addAttribute("erroLogin", "Email ou senha inválidos");
        return "loginPage";
    }
}