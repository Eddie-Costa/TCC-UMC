package com.example.controller;


import com.example.dto.LoginDTO;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import DAO.usuarioDAO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.example.service.LoginAttemptService;
import com.example.service.TwoFactorService;
import com.example.service.emailService;

import java.sql.SQLException;

@Controller
public class LoginPageController {

    @Autowired
    private LoginAttemptService loginAttemptService;

    @Autowired
    private TwoFactorService twoFactorService;

    @Autowired
    private emailService emailService;

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("usuario", new LoginDTO());
        return "loginPage";
    }

    @PostMapping("/login")
    public String fazerLogin(@Valid @ModelAttribute("usuario") LoginDTO usuarioDTO, BindingResult result, Model model, HttpSession session) throws SQLException {

        String email = usuarioDTO.getEmail();

        //Verifica bloqueio
        if (LoginAttemptService.estaBloqueado(email)) {
            model.addAttribute("mensagemDeErro", "Conta bloqueada por muitas tentativas. Tente mais tarde.");
            return "loginPage";
        }

        if (result.hasErrors()) {
            model.addAttribute("mensagemDeErro", "Erro ao fazer login, tente novamente!!!");
            return "loginPage";
        }

        //Encriptador
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        usuarioDAO usuarioDAO = new usuarioDAO();

        //Requisição para o BD Buscar a senha criptografada e comparar com a senha digitada.
        String senhaHash = usuarioDAO.QueryLoginUsuario(usuarioDTO.getEmail());

        if(senhaHash != null && encoder.matches(usuarioDTO.getSenha(), senhaHash)) {
            //Sucesso de Login
            loginAttemptService.loginSucesso(email);

            //2FA
            String codigo = twoFactorService.gerarCodigo(email);

            emailService.enviarCodigo(email, codigo);

            session.setAttribute("email2FA", email);

            return "redirect:/verificar";
        }

        //Erro de Login
        loginAttemptService.loginFalhou(email);

        if (loginAttemptService.getTentativas(email) >= 5) {
            model.addAttribute("mensagemDeErro", "Conta bloqueada por 10 minutos.");
        } else {
            model.addAttribute("mensagemDeErro", "Email ou senha inválidos.");
        }
        return "loginPage";
    }
}
