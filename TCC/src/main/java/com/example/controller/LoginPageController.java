package com.example.controller;


import com.example.dto.LoginDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import DAO.usuarioDAO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.swing.JOptionPane;


import java.sql.SQLException;

@Controller
public class LoginPageController {

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("usuario", new LoginDTO());
        return "loginPage";
    }

    @PostMapping("/login")
    public String fazerLogin(
            @Valid @ModelAttribute("usuario") LoginDTO usuario,
            BindingResult result,
            Model model) throws SQLException {

        if (result.hasErrors()) {
            model.addAttribute("mensagemDeErro", "Erro ao fazer login, tente novamente!!!");
            return "loginPage";
        }

        //Encriptador
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        usuarioDAO usuarioDAO = new usuarioDAO();

        //Requisição para o BD Buscar a senha criptografada e comparar com a senha digitada.
        String senhaHash = usuarioDAO.QueryLoginUsuario(usuario.getEmail());

        if(encoder.matches(usuario.getSenha(), senhaHash)) {
            return "index";
        }

        model.addAttribute("mensagem", "Erro ao fazer login, tente novamente!!!");
        return "loginPage";
    }
}