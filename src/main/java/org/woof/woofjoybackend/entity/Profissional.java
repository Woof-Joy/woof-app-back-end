package org.woof.woofjoybackend.entity;

import org.woof.woofjoybackend.domain.Usuario;
import org.woof.woofjoybackend.entity.object.Item;

import java.util.Date;

public class Profissional extends Usuario {

    public Profissional(String nome, String sobrenome, String cpf, String cep, String numero, String email, String senha, Date dataNasc) {
        super(nome, sobrenome, cpf, cep, numero, email, senha, dataNasc);
    }

    public Profissional() {
    }

}
