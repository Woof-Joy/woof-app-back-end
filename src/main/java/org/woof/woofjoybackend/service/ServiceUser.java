package org.woof.woofjoybackend.service;

import org.springframework.stereotype.Service;
import org.woof.woofjoybackend.domain.Usuario;

import java.util.ArrayList;
import java.util.List;
@Service
public class ServiceUser {

    private List<Usuario> usuarios = new ArrayList<>();

    public void cadastrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }
    public List<Usuario> getUsuarios() {return usuarios;}
    public List<Usuario> getPrestadores() {return usuarios;}
    public List<Usuario> getItens() {return usuarios;}
}
