package com.example.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class TwoFactorService {
    private static Map<String, String> codigos = new HashMap<>();
    private static Map<String, Long> expiracao = new HashMap<>();

    public static String gerarCodigo(String email) {
        String codigo = String.valueOf(new Random().nextInt(900000) + 100000);

        codigos.put(email, codigo);
        expiracao.put(email, System.currentTimeMillis() + (5 * 60 * 1000)); // 5 min

        return codigo;
    }

    public boolean validarCodigo(String email, String codigo) {
        if (!codigos.containsKey(email)) return false;

        if (System.currentTimeMillis() > expiracao.get(email)) {
            codigos.remove(email);
            expiracao.remove(email);
            return false;
        }

        return codigos.get(email).equals(codigo);
    }

    public void limparCodigo(String email) {
        codigos.remove(email);
        expiracao.remove(email);
    }
}
