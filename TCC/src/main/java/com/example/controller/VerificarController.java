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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

@Controller
public class VerificarController {

    private static final Logger logger = LoggerFactory.getLogger(VerificarController.class);

    @Autowired
    private TwoFactorService twoFactorService;

    @Autowired
    private emailService emailService;

    usuarioDAO usuarioDAO = new usuarioDAO();

    //Verificar LOGIN

    @GetMapping("/verificar")
    public String paginaVerificacao(HttpSession session) {

        // Segurança: impedir acesso direto
        if (session.getAttribute("email2FA") == null) {
            return "redirect:/login";
        }

        return "verificar";
    }

    @PostMapping("/verificar")
    public String verificarCodigo(LoginDTO usuarioDTO,
                                  @RequestParam String codigo,
                                  HttpSession session,
                                  Model model) throws SQLException {

        String email = (String) session.getAttribute("email2FA");

        //  Segurança
        if (email == null) {
            logger.warn("Email invalido com valor: {}", email);
            return "redirect:/login";
        }

        if (twoFactorService.validarCodigo(email, codigo)) {
            logger.info("O usuario passou na validação de token para login");

            // BUSCA USUÁRIO REAL
            LoginDTO usuario = usuarioDAO.buscarPorEmail(email);

            // CRIA SESSÃO CORRETA
            session.setAttribute("usuarioLogado", usuario);
            session.setMaxInactiveInterval(900);
            logger.info("A sessão do usuario foi criada com sucesso ID: {}", session.getId());

            session.removeAttribute("email2FA");

            return "redirect:/home";
        }

        // Código inválido
        model.addAttribute("erro", "Código inválido ou expirado");
        logger.warn("Codigo 2FA inválido inserido");
        return "verificar";
    }

    //Verificar RESET

    @GetMapping("/verificarReset")
    public String paginaVerificacaoReset(HttpSession session) {

        // Segurança: impedir acesso direto
        if (session.getAttribute("email2FA") == null) {
            logger.warn("Email invalido com valor: {}", session.getAttribute("email2FA"));
            return "redirect:/ResetPassword_Verification";
        }

        return "verificarReset";
    }

    @PostMapping("/ResetVerificar")
    public String verificarCodigoRest(LoginDTO usuarioDTO,
                                  @RequestParam String codigo,
                                  HttpSession session,
                                  Model model) throws SQLException {

        String email = (String) session.getAttribute("email2FA");

        //  Segurança
        if (email == null) {
            logger.warn("Email invalido com valor: {}", email);
            return "redirect:/ResetPassword_Verification";
        }

        if (twoFactorService.validarCodigo(email, codigo)) {
            logger.info("O usuario passou na validação de token para Reset de senha");

            // BUSCA USUÁRIO REAL
            LoginDTO usuario = usuarioDAO.buscarPorEmail(email);

            return "redirect:/ResetPassword";
        }

        // Código inválido
        model.addAttribute("erro", "Código inválido ou expirado");
        logger.warn("Codigo 2FA inválido inserido");
        return "verificarReset";
    }
}
