package com.example.controller;

import com.example.dto.SubscriptionDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import DAO.usuarioDAO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.SQLException;

@Controller
public class SubscriptionPageController {

    @GetMapping("/Subscription")
    public String loginPage(org.springframework.ui.Model model) {
        model.addAttribute("usuario", new SubscriptionDTO());
        return "subscriptionPage";
    }

    @PostMapping("/Subscription")
    public String registrar(
            @Valid @ModelAttribute("usuario") SubscriptionDTO usuario,
            BindingResult result) throws SQLException {

        if (result.hasErrors()) {
            return "subscriptionPage";
        }

        //Encriptador
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

        //Inserção de dados no BD
        usuarioDAO usuarioDAO = new usuarioDAO();
        usuarioDAO.InsertCadastroIntoBD(usuario.getNome(), usuario.getSobrenome(), usuario.getEmail(), encoder.encode(usuario.getSenha()));

        return "loginPage";
    }
}

// teste.M.Santhiago