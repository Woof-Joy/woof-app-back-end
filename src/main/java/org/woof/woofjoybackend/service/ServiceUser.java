package org.woof.woofjoybackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.woof.woofjoybackend.entity.Cliente;
import org.woof.woofjoybackend.entity.Parceiro;
import org.woof.woofjoybackend.entity.Usuario;
import org.woof.woofjoybackend.repository.ClienteRepository;
import org.woof.woofjoybackend.repository.ParceiroRepository;
import org.woof.woofjoybackend.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class ServiceUser {
    private UsuarioRepository usuarioRepository;
    private ClienteRepository clienteRepository;
    private ParceiroRepository parceiroRepository;

    @Autowired
    public ServiceUser(UsuarioRepository usuarioRepository, ClienteRepository clienteRepository, ParceiroRepository parceiroRepository) {
        this.usuarioRepository = usuarioRepository;
        this.clienteRepository = clienteRepository;
        this.parceiroRepository = parceiroRepository;
    }

    public void cadastrarUsuario(Usuario usuario, int tipo) {
        usuarioRepository.save(usuario);
        if (tipo == 0) {
            clienteRepository.save(new Cliente(usuario));
        }
        parceiroRepository.save(new Parceiro(usuario));
    }

    public List<Usuario> listaUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario listaUsuarioPorId(Integer id) {
        return usuarioRepository.findById(id).get();
    }

    public Usuario attUsuario(Usuario usuarioAtt, Integer id) {
        usuarioAtt.setId(id);
        return usuarioRepository.save(usuarioAtt);
    }

    public void deleteUsuario(Integer id) {
        usuarioRepository.deleteById(id);
    }

    public boolean idValido(Integer id) {
        return usuarioRepository.existsById(id);
    }

    public boolean emailExiste(String email) {
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findByEmail(email);

        if (!usuarioEncontrado.isPresent() || usuarioEncontrado.isPresent() && usuarioEncontrado.get().getEmail().equals(email)) {
            return true;
        }
        return false;
    }

    public boolean cadastrado(Usuario usuario, int tipo) {
        if (tipo == 0 && isNull(usuario.getCliente()) || tipo == 1 && isNull(usuario.getParceiro())) {
            return false;
        }
        return true;
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

//    public int getIndexUsuarioLogado() {
//        return indexUsuarioLogado;
//    }
//
//    public void setIndexUsuarioLogado(int indexUsuarioLogado) {
//        this.indexUsuarioLogado = indexUsuarioLogado;
//    }
}