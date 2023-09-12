package org.woof.woofjoybackend.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.woof.woofjoybackend.domain.Usuario;
import org.woof.woofjoybackend.domain.iVerificaveis;
import org.woof.woofjoybackend.entity.Cliente;
import org.woof.woofjoybackend.entity.Profissional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceUser implements iVerificaveis {

    private List<Usuario> usuarioList = new ArrayList<>();
    private int indexUsuario = -1;

    public ResponseEntity<String> cadastrarUsuario(Usuario usuario) {
        for (int i = 0; i < usuarioList.size(); i++) {
            String emailCadastrado = usuarioList.get(i).getEmail();
            if (usuario.getEmail().equals(emailCadastrado)) {
                return ResponseEntity.status(409).body("E-mail ja cadastrado");
            }
        }
        if (!verificarEmailSenha(usuario)) {
            return ResponseEntity.status(400)
                    .body("E-mail deve conter um '@' " +
                            "\n Senha deve ter no mínimo 5 caracteres ");
        }
        usuario.setId(usuarioList.size() + 1);
        usuarioList.add(usuario);
        ResponseEntity.status(200).body(usuario);
        return ResponseEntity.status(200).body("Usuário cadastrado com sucesso!");
    }

    public ResponseEntity<String> loginUsuario(String email, String senha) {
        if (!verificarEmailSenha(email, senha)) {
            return ResponseEntity.status(400).build();
        }
        for (int i = 0; i < usuarioList.size(); i++) {
            String emailCadastrado = usuarioList.get(i).getEmail();
            String senhaCadastrado = usuarioList.get(i).getSenha();
            if (email.equals(emailCadastrado)) {
                if (senha.equals(senhaCadastrado)) {
                    indexUsuario = i;
                    return ResponseEntity.status(200).body("Login Realizado com sucesso\nBem vindo!");
                }
            }
        }
        return ResponseEntity.status(401).build();
    }

    public ResponseEntity<String> deleteUsuario(int id) {
        if (transformaIdEmIndex(id, usuarioList) >= 0) {
            int index = transformaIdEmIndex(id, this.usuarioList);
            usuarioList.remove(index);
            return ResponseEntity.status(204).body("Usuário" + usuarioList.get(index).getEmail() + " apagado com sucesso");
        }
        return ResponseEntity.status(404).build();
    }

    public ResponseEntity<String> putUsuario(int id, Usuario usuario) {
        if (transformaIdEmIndex(id, usuarioList) < 0) {
            return ResponseEntity.status(404).build();
        }

        if (!verificarEmailSenha(usuario)) {
            return ResponseEntity.status(400)
                    .body("E-mail deve conter um '@' " +
                            "\n Senha deve ter no mínimo 5 caracteres ");
        }
        int index = transformaIdEmIndex(id, usuarioList);
        usuario.setId(usuarioList.get(index).getId());
        usuarioList.set(index, usuario);
        return ResponseEntity.status(200).body("Usuário alterado com sucesso!");


    }


    public List<Usuario> getProfissionais() {
        if (!usuarioList.isEmpty()) {
            List<Usuario> userList = new ArrayList<>();
            for (Usuario u : usuarioList) {
                if (u instanceof Profissional) {
                    userList.add(u);
                }
            }
            return userList;
        }
        return usuarioList;
    }

    public List<Usuario> getClientes() {
        if (!usuarioList.isEmpty()) {
            List<Usuario> userList = new ArrayList<>();
            for (Usuario u : usuarioList) {
                if (u instanceof Cliente) {
                    userList.add(u);
                }
            }
            return userList;
        }
        return usuarioList;
    }

    public ResponseEntity<List<Usuario>> getUsers() {
        if (!usuarioList.isEmpty()) {
            return ResponseEntity.status(200).body(usuarioList);
        }
        return ResponseEntity.status(204).body(usuarioList);
    }

    public int getIndexUsuario() {
        return indexUsuario;
    }

}
