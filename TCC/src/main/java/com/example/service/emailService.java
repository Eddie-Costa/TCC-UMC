package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class emailService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarCodigo(String para, String codigo) {

        SimpleMailMessage mensagem = new SimpleMailMessage();
        mensagem.setTo(para);
        mensagem.setSubject("Código de verificação - 2FA");
        mensagem.setText("Seu código de verificação é: " + codigo);

        mailSender.send(mensagem);
    }

}
