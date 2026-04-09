package com.example.controller;

import com.example.dto.UsuarioDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import DAO.usuarioDAO;

import java.sql.SQLException;

@Controller
public class SubscriptionPageController {

    @GetMapping("/Subscription")
    public String loginPage(org.springframework.ui.Model model) {
        model.addAttribute("usuario", new UsuarioDTO());
        return "subscriptionPage";
    }

    @PostMapping("/Subscription")
    public String registrar(
            @Valid @ModelAttribute("usuario") UsuarioDTO usuario,
            BindingResult result) throws SQLException {

        if (result.hasErrors()) {
            return "subscriptionPage";
        }

        //vira query para BD
//        System.out.println(usuario.getEmail() + " " + usuario.getSenha());
        usuarioDAO usuarioDAO = new usuarioDAO();
        usuarioDAO.InsertCadastroIntoBD(usuario.getNome(), usuario.getSobrenome(), usuario.getEmail(), usuario.getSenha());

        return "telaSucesso";
    }
}
