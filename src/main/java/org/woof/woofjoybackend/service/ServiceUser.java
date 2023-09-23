package org.woof.woofjoybackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.woof.woofjoybackend.domain.Usuario;
import org.woof.woofjoybackend.domain.iVerificaveis;
import org.woof.woofjoybackend.entity.Cliente;
import org.woof.woofjoybackend.entity.Parceiro;
import org.woof.woofjoybackend.repository.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceUser implements iVerificaveis {
    @Autowired
    private UsuarioRepository repository;
    public int indexUsuarioLogado = -1;
    private int contadorId = 0;


    public void cadastrarUsuario(Usuario usuario) {
        repository.save(usuario);
    }

//    public ResponseEntity<String> loginUsuario(String email, String senha) {
//        if (!verificarEmailSenha(email, senha)) {
//            return ResponseEntity.status(400).build();
//        }
//        for (int i = 0; i < usuarioList.size(); i++) {
//            String emailCadastrado = usuarioList.get(i).getEmail();
//            String senhaCadastrado = usuarioList.get(i).getSenha();
//            if (email.equals(emailCadastrado)) {
//                if (senha.equals(senhaCadastrado)) {
//                    setIndexUsuarioLogado(i);
//                    return ResponseEntity.status(200).body("Login Realizado com sucesso\nBem vindo!");
//                }
//            }
//        }
//        return ResponseEntity.status(401).build();
//    }

    public void deleteUsuario(int id) {
        repository.deleteById(id);
    }

    public boolean existe(int id) {
        return repository.existsById(id);
    }


//    public List<Usuario> getProfissionais() {
//        if (!usuarioList.isEmpty()) {
//            List<Usuario> userList = new ArrayList<>();
//            for (Usuario u : usuarioList) {
//                if (u instanceof Parceiro) {
//                    userList.add(u);
//                }
//            }
//            return userList;
//        }
//        return usuarioList;
//    }

//    public List<Usuario> getClientes() {
//        if (!usuarioList.isEmpty()) {
//            List<Usuario> userList = new ArrayList<>();
//            for (Usuario u : usuarioList) {
//                if (u instanceof Cliente) {
//                    userList.add(u);
//                }
//            }
//            return userList;
//        }
//        return usuarioList;
//    }

    public List<Usuario> getUsers() {
        return repository.findAll();
    }

    public int getIndexUsuarioLogado() {
        return indexUsuarioLogado;
    }

    public void setIndexUsuarioLogado(int indexUsuarioLogado) {
        this.indexUsuarioLogado = indexUsuarioLogado;
    }
}
