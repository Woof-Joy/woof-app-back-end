package org.woof.woofjoybackend.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import jdk.jfr.BooleanFlag;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;
import java.time.LocalDate;
import java.util.List;

@Data
public class ParceiroDTO {
    @NotBlank
    @Size(max = 50)
    private String nome;
    @Size(max = 50)
    private String sobrenome;
    @Size(min = 8, max = 8)
    private String cep;
    @Size(max = 10)
    private String numero;
    @NotBlank
    @Email
    private String email;
    @Past
    private LocalDate dataNasc;

    private List<FichaServicoDTO> servicos;

}

