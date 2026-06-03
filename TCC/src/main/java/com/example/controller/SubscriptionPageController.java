package com.example.controller;

import com.example.dto.SubscriptionDTO;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.DAO.usuarioDAO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.SQLException;

@Controller
public class SubscriptionPageController{

    @Autowired
    private usuarioDAO usuarioDAO;

    private static final Logger logger = LoggerFactory.getLogger(SubscriptionPageController.class);

    @GetMapping("/Subscription")
    public String loginPage(org.springframework.ui.Model model) {
        model.addAttribute("usuario", new SubscriptionDTO());
        return "subscriptionPage";
    }

    @PostMapping("/Subscription")
    public String registrar(@Valid @ModelAttribute("usuario") SubscriptionDTO usuario, BindingResult result) throws SQLException {

        if (result.hasErrors()) {
            logger.warn("Erro ao registrar o usuario com email:" +usuario.getEmail()+ " Erro: " + result.getAllErrors());
            return "subscriptionPage";
        }

        //Encriptador
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

        //Inserção de dados no BD
        usuarioDAO.InsertCadastroIntoBD(usuario.getNome(), usuario.getSobrenome(), usuario.getEmail().toLowerCase(), encoder.encode(usuario.getSenha()));
        logger.info("Sucesso ao cadastrar novo usuario com email: " + usuario.getEmail());

        return "loginPage";
    }
}