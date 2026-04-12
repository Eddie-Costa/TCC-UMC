package com.example.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SubscriptionDTO {

    @Size(max = 50, message = "O nome deve ter no maximo 50 caracteres")
    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @Size(max = 50, message = "O sobrenome deve ter no maximo 50 caracteres")
    @NotBlank(message = "O sobrenome é obrigatório")
    private String sobrenome;

    @Size(max = 100, message = "O e-mail deve ter no maximo 100 caracteres")
    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "E-mail inválido")
    private String email;

    @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).*$",
            message = "Senha deve conter maiúscula, minúscula, número e caractere especial"
    )
    private String senha;
}
