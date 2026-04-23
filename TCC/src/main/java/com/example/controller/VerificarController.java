package com.example.controller;

import com.example.dto.LoginDTO;
import com.example.service.TwoFactorService;
import com.example.service.emailService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import DAO.usuarioDAO;

import java.sql.SQLException;

@Controller
public class VerificarController {

    @Autowired
    private TwoFactorService twoFactorService;

    @Autowired
    private emailService emailService;

    usuarioDAO usuarioDAO = new usuarioDAO();

    @GetMapping("/verificar")
    public String paginaVerificacao(HttpSession session) {

        // 🔒 Segurança: impedir acesso direto
        if (session.getAttribute("email2FA") == null) {
            return "redirect:/login";
        }

        return "verificar"; // templates/verificar.html
    }

    @PostMapping("/verificar")
    public String verificarCodigo(LoginDTO usuarioDTO,
                                  @RequestParam String codigo,
                                  HttpSession session,
                                  Model model) throws SQLException {

        String email = (String) session.getAttribute("email2FA");

        //  Segurança
        if (email == null) {
            return "redirect:/login";
        }

        if (twoFactorService.validarCodigo(email, codigo)) {

            // BUSCA USUÁRIO REAL
            LoginDTO usuario = usuarioDAO.buscarPorEmail(email);

            // CRIA SESSÃO CORRETA
            session.setAttribute("usuarioLogado", usuario);
            session.setMaxInactiveInterval(900);

            session.removeAttribute("email2FA");

            return "redirect:/home";
        }

        // Código inválido
        model.addAttribute("erro", "Código inválido ou expirado");
        return "verificar";
    }

    @PostMapping("/verificarReset")
    public String verificarCodigoRest(LoginDTO usuarioDTO,
                                  @RequestParam String codigo,
                                  HttpSession session,
                                  Model model) throws SQLException {

        String email = (String) session.getAttribute("email2FA");

        //  Segurança
        if (email == null) {
            return "redirect:/ResetPassword_Verification";
        }

        if (twoFactorService.validarCodigo(email, codigo)) {

            // BUSCA USUÁRIO REAL
            LoginDTO usuario = usuarioDAO.buscarPorEmail(email);

            session.removeAttribute("email2FA");

            return "redirect:/ResetPassword";
        }

        // Código inválido
        model.addAttribute("erro", "Código inválido ou expirado");
        return "verificar";
    }
}
