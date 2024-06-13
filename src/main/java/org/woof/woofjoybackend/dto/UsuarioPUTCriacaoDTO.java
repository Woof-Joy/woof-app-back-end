package org.woof.woofjoybackend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Data
public class UsuarioPUTCriacaoDTO {

    @Size(max = 50)
    private String nome;
    @Size(max = 50)
    private String sobrenome;
    @Email
    private String email;

}
