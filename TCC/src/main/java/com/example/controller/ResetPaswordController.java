package com.example.controller;

import com.example.service.LoginAttemptService;
import com.example.service.TwoFactorService;
import com.example.service.emailService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.dto.LoginDTO;

@Controller
public class ResetPaswordController {

    @Autowired
    private LoginAttemptService loginAttemptService;

    @Autowired
    private TwoFactorService twoFactorService;

    @Autowired
    private emailService emailService;

    @GetMapping("/ResetPassword_Verification")
    public String ResetPasword(){
        return "ResetPassword_Verification";
    }

    @PostMapping("/ResetVerification")
    public String Reset(@Valid @ModelAttribute("usuario") LoginDTO usuarioDTO, BindingResult result, Model model, HttpSession session){

        String email = usuarioDTO.getEmail();

        //2FA
        String codigo = twoFactorService.gerarCodigo(email);

        emailService.enviarCodigo(email, codigo);

        return "redirect:/verificar";
    }

}
