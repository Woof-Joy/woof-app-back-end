package org.woof.woofjoybackend.entity.dto;

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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idParceiro;

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

    @Max(value = 5)
    @Min(value = 0)
    private Double estrelas;


    public ParceiroDTO(Integer idParceiro, LocalDate dataEntrada, Integer maxDogs, Boolean aceitaDogEspecial, Boolean aceitaDogIdoso, Boolean aceitaDogBravo, Boolean aceitaDogGrande, Boolean aceitaDogCio, String nome, String sobrenome, String cpf, String cep, String numero, String email,  LocalDate dataNasc, String descricao, Double estrelas) {
        this.idParceiro = idParceiro;
        this.dataEntrada = dataEntrada;
        this.maxDogs = maxDogs;
        this.aceitaDogEspecial = aceitaDogEspecial;
        this.aceitaDogIdoso = aceitaDogIdoso;
        this.aceitaDogBravo = aceitaDogBravo;
        this.aceitaDogGrande = aceitaDogGrande;
        this.aceitaDogCio = aceitaDogCio;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.cep = cep;
        this.numero = numero;
        this.email = email;
        this.dataNasc = dataNasc;
        this.descricao = descricao;
        this.estrelas = estrelas;
    }
}
