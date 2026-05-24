package com.example.controller;

import com.example.service.TwoFactorService;
import com.example.service.emailService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.DAO.usuarioDAO;

import java.sql.SQLException;
import java.util.ArrayList;

@Controller
public class ConsultaDadosController {

    ArrayList<String> Arrayresultado = new ArrayList<>();
    private static final Logger logger = LoggerFactory.getLogger(LoginPageController.class);


    @Autowired
    private usuarioDAO usuarioDAO;

    @Autowired
    private emailService emailService;

    @Autowired
    private TwoFactorService twoFactorService;

    @GetMapping("/UserData")
    public String ConsultaDados(HttpSession session) throws SQLException {

    final Logger logger = LoggerFactory.getLogger(ResetPaswordController.class);


        if(session.getAttribute("usuarioLogado") == null){
            return "redirect:/login";
        }

        Arrayresultado = usuarioDAO.QueryUserData(session.getAttribute("email2FA").toString());
        session.setAttribute("nome", Arrayresultado.get(0));
        session.setAttribute("sobrenome", Arrayresultado.get(1));
        session.setAttribute("email", Arrayresultado.get(2));
        String ano =  Arrayresultado.get(3).substring(0,4);
        String mes =  Arrayresultado.get(3).substring(5,7);
        String dia =  Arrayresultado.get(3).substring(8,10);
        String data = dia + "/" + mes + "/" + ano;
        session.setAttribute("dataRegistro", data);

        logger.info("O usuario com email:" +session.getAttribute("email")+ " acessou a página de consulta de dados");

        return "UserData";
    }

    @GetMapping("/ExportarDados")
    public String ExportarDados(HttpSession session) throws SQLException {

        if(session.getAttribute("usuarioLogado") == null){
            return "redirect:/login";
        }

        Arrayresultado = usuarioDAO.QueryUserData(session.getAttribute("email2FA").toString());
        session.setAttribute("nome", Arrayresultado.get(0));
        session.setAttribute("sobrenome", Arrayresultado.get(1));
        session.setAttribute("email", Arrayresultado.get(2));
        String ano =  Arrayresultado.get(3).substring(0,4);
        String mes =  Arrayresultado.get(3).substring(5,7);
        String dia =  Arrayresultado.get(3).substring(8,10);
        String data = dia + "/" + mes + "/" + ano;
        session.setAttribute("dataRegistro", data);

        String email = session.getAttribute("email2FA").toString();

        emailService.enviarEmailDados(email, session.getAttribute("nome").toString(), session.getAttribute("sobrenome").toString(), session.getAttribute("email").toString(), session.getAttribute("dataRegistro").toString());

        logger.info("O usuario com email:" +session.getAttribute("email")+ " exportou seus dados pessoais por email");

        return "UserData";
    }

    @GetMapping("/ExcluirDados")
    public String ExcluirDados(HttpSession session) throws SQLException {

        if(session.getAttribute("usuarioLogado") == null){
            return "redirect:/login";
        }

        String email = session.getAttribute("email2FA").toString();

        //2FA
        logger.info("Envio de codigo 2FA para o email: {}", email);

        //Gerar token 2FA
        String codigo = twoFactorService.gerarCodigo(email);

        //Enviar token 2FA para o email
        emailService.enviarCodigo(email, codigo);

        session.setAttribute("email2FA", email);
        session.setAttribute("redirect", "ExcluirDados");

        return "redirect:/verificar";
    }

}
