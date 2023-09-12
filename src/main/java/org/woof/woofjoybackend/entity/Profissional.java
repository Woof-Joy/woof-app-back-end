package org.woof.woofjoybackend.entity;

import org.woof.woofjoybackend.domain.Usuario;
import org.woof.woofjoybackend.entity.object.Item;

import java.util.Date;

public class Profissional extends Usuario {
    private double mediaAvaliacoes;

    public Profissional(String nome, String sobrenome, String cpf, String cep, String numero, String email, String senha, Date dataNasc) {
        super(nome, sobrenome, cpf, cep, numero, email, senha, dataNasc);
        this.mediaAvaliacoes = 0;
    }

    public Profissional() {
    }

    @Override
    public void putPerfil(Usuario usuario, Usuario login) {

    }

    public double getMediaAvaliacoes() {
        return mediaAvaliacoes;
    }

    public void setMediaAvaliacoes(double mediaAvaliacoes) {
        this.mediaAvaliacoes = mediaAvaliacoes;
    }
}
