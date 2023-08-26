package org.woof.woofjoybackend.entity;

import org.woof.woofjoybackend.domain.Usuario;
import org.woof.woofjoybackend.entity.object.Item;

import java.util.Date;

public class Prestador extends Usuario {

    public Prestador(int id, String nome, String sobrenome, String cpf, String cep, String numero, String email, String senha, Date dataNasc) {
        super(id, nome, sobrenome, cpf, cep, numero, email, senha, dataNasc);
    }

    @Override
    public void doarItem(Item it) {

    }

}
