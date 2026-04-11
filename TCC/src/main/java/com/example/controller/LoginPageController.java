package com.example.controller;

import com.example.dto.UsuarioDTO;
import jakarta.servlet.http.HttpSession; // Modifiquei
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import DAO.usuarioDAO;

import java.sql.SQLException;

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
            Model model,
            HttpSession session) throws SQLException { // Modifiquei

        if (result.hasErrors()) {
            return "loginPage";
        }

        usuarioDAO usuarioDAO = new usuarioDAO();

        String resultado = usuarioDAO.QueryLoginUsuario(usuario.getEmail(), usuario.getSenha());

        // precisa validar 2FA
        if ("2FA".equals(resultado)) {
            session.setAttribute("email2FA", usuario.getEmail());
            return "pagina2fa";
        }

        // login direto
        if ("1".equals(resultado)) {
            return "telaSucesso";
        }

        // erro
        model.addAttribute("erroLogin", "Email ou senha inválidos");
        return "loginPage";
    }

    @PostMapping("/validar2fa")
    public String validar2FA(
            @RequestParam String codigo,
            HttpSession session,
            Model model) throws SQLException {

        String email = (String) session.getAttribute("email2FA");

        if (email == null) {
            return "loginPage"; 
        }

        usuarioDAO usuarioDAO = new usuarioDAO();

        if ("1".equals(usuarioDAO.validar2FA(email, codigo))) {
            session.removeAttribute("email2FA"); 
            return "telaSucesso";
        }

        model.addAttribute("erro", "Código inválido ou expirado");
        return "pagina2fa";
    }
}