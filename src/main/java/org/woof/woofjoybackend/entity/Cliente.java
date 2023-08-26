package org.woof.woofjoybackend.entity;

import org.springframework.web.bind.annotation.GetMapping;
import org.woof.woofjoybackend.domain.Usuario;
import org.woof.woofjoybackend.entity.object.Item;

import java.util.Date;

public class Cliente extends Usuario {
    public Cliente(int id, String nome, String sobrenome, String cpf, String cep, String numero, String email, String senha, Date dataNasc) {
        super(id, nome, sobrenome, cpf, cep, numero, email, senha, dataNasc);
    }

    public Cliente() {
    }

    @Override
    public void doarItem(Item it) {

    }
}
