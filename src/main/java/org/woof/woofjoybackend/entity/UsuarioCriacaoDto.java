package org.woof.woofjoybackend.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Getter
@Setter
public class UsuarioCriacaoDto {

    @NotBlank
    @Size(max = 50)
    private String nome;

    @Size(max = 50)
    private String sobrenome;

    @CPF
    private String cpf;

    @Past
    private LocalDate dataNascimento;

    @Size(min = 8, max = 8)
    private String cep;

    @Size(max = 10)
    private String num;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String Senha;
}
