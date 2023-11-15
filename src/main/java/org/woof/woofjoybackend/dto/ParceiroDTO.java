package org.woof.woofjoybackend.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import jdk.jfr.BooleanFlag;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Data
public class ParceiroDTO {
    @Min(0)
    @Max(5)
    private Double avaliacao;

    @PastOrPresent
    private LocalDate dataEntrada;


    @DecimalMin(value = "1")

    private Integer maxDogs;

    @BooleanFlag
    private Boolean aceitaDogEspecial;

    @BooleanFlag
    private Boolean aceitaDogIdoso;

    @BooleanFlag
    private Boolean aceitaDogBravo;

    @BooleanFlag
    private Boolean aceitaDogGrande;

    @BooleanFlag
    private Boolean aceitaDogCio;

    @NotBlank
    @Size(max = 50)
    private String nome;

    @Size(max = 50)
    private String sobrenome;

    @CPF
    private String cpf;

    @Size(min = 8, max = 8)
    private String cep;

    @Size(max = 10)
    private String numero;

    @NotBlank
    @Email
    private String email;


    @Past
    private LocalDate dataNasc;

    @Size(max = 500)
    private String descricao;


    f = cpf;
        this.cep = cep;
        this.numero = numero;
        this.email = email;
        this.dataNasc = dataNasc;
        this.descricao = descricao;
    }
}

