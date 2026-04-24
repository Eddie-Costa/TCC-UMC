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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

@Controller
public class LoginPageController {

    private static final Logger logger = LoggerFactory.getLogger(LoginPageController.class);

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
            logger.warn("Tentativa de login em Conta bloqueada");
            model.addAttribute("mensagemDeErro", "Conta bloqueada por muitas tentativas. Tente mais tarde.");
            return "loginPage";
        }

        if (result.hasErrors()) {
            logger.warn("Erro ao fazer login");
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
            logger.info("Envio de codigo 2FA par o email: {}", email);

            //Gerar token 2FA
            String codigo = twoFactorService.gerarCodigo(email);

            //Enviar token 2FA para o email
            emailService.enviarCodigo(email, codigo);

            session.setAttribute("email2FA", email);

            return "redirect:/verificar";
        }

        //Erro de Login
        logger.warn("Erro ao fazer login");
        loginAttemptService.loginFalhou(email);
        logger.warn("Erro ao fazer login numero de tentativas: {}", loginAttemptService.getTentativas(email));

        if (loginAttemptService.getTentativas(email) >= 5) {
            logger.warn("Conta bloqueada por 10 minutos");
            model.addAttribute("mensagemDeErro", "Conta bloqueada por 10 minutos.");
        } else {
            logger.warn("Email ou senha incorreto");
            model.addAttribute("mensagemDeErro", "Email ou senha inválidos.");
        }
        return "loginPage";
    }
}
