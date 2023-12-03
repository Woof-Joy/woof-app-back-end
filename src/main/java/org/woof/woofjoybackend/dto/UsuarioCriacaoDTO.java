package org.woof.woofjoybackend.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
@Data
public class UsuarioCriacaoDTO {
    @NotBlank
    @Size(max = 50)
    private String nome;
    @Size(max = 50)
    private String sobrenome;
    @CPF
    private String cpf;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String senha;
    @Past
    private LocalDate dataNasc;
    @Size(min = 8, max = 8)
    @NotBlank
    private String cep;
    @NotBlank
    private String numero;
    @NotBlank
    private String rua;
    @NotBlank
    private String cidade;
    @NotBlank
    private String Estado;

}
