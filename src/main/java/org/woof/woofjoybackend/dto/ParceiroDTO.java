package org.woof.woofjoybackend.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import jdk.jfr.BooleanFlag;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;


public class ParceiroDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idParceiro;

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


    public ParceiroDTO(Integer idParceiro, LocalDate dataEntrada, Integer maxDogs, Boolean aceitaDogEspecial, Boolean aceitaDogIdoso, Boolean aceitaDogBravo, Boolean aceitaDogGrande, Boolean aceitaDogCio, String nome, String sobrenome, String cpf, String cep, String numero, String email,  LocalDate dataNasc, String descricao) {
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
    }

    public Integer getIdParceiro() {
        return idParceiro;
    }

    public void setIdParceiro(Integer idParceiro) {
        this.idParceiro = idParceiro;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Integer getMaxDogs() {
        return maxDogs;
    }

    public void setMaxDogs(Integer maxDogs) {
        this.maxDogs = maxDogs;
    }

    public Boolean getAceitaDogEspecial() {
        return aceitaDogEspecial;
    }

    public void setAceitaDogEspecial(Boolean aceitaDogEspecial) {
        this.aceitaDogEspecial = aceitaDogEspecial;
    }

    public Boolean getAceitaDogIdoso() {
        return aceitaDogIdoso;
    }

    public void setAceitaDogIdoso(Boolean aceitaDogIdoso) {
        this.aceitaDogIdoso = aceitaDogIdoso;
    }

    public Boolean getAceitaDogBravo() {
        return aceitaDogBravo;
    }

    public void setAceitaDogBravo(Boolean aceitaDogBravo) {
        this.aceitaDogBravo = aceitaDogBravo;
    }

    public Boolean getAceitaDogGrande() {
        return aceitaDogGrande;
    }

    public void setAceitaDogGrande(Boolean aceitaDogGrande) {
        this.aceitaDogGrande = aceitaDogGrande;
    }

    public Boolean getAceitaDogCio() {
        return aceitaDogCio;
    }

    public void setAceitaDogCio(Boolean aceitaDogCio) {
        this.aceitaDogCio = aceitaDogCio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
