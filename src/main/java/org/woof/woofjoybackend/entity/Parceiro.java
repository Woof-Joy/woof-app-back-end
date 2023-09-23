package org.woof.woofjoybackend.entity;

import org.woof.woofjoybackend.domain.Usuario;

import java.util.Date;

public class Parceiro extends Usuario {

    public Parceiro(String nome, String sobrenome, String cpf, String cep, String numero, String email, String senha, Date dataNasc) {
        super(nome, sobrenome, cpf, cep, numero, email, senha, dataNasc);
    }

    public Parceiro() {
    }

}
