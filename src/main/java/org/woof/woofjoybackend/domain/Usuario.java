package org.woof.woofjoybackend.domain;

import org.woof.woofjoybackend.entity.object.Item;

import java.util.Date;

public abstract class Usuario {
    private int id;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String cep;
    private String numero;
    private String email;
    private String senha;
    private Date dataNasc;


    //o Construtor recebe id msm?
    public Usuario(int id, String nome, String sobrenome, String cpf, String cep, String numero, String email, String senha, Date dataNasc) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.cep = cep;
        this.numero = numero;
        this.email = email;
        this.senha = senha;
        this.dataNasc = dataNasc;
    }

    public Usuario() {
    }

    public abstract void doarItem(Item it);



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }
}

