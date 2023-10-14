package org.woof.woofjoybackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.woof.woofjoybackend.configuration.security.jwt.GerenciadorTokenJwt;
import org.woof.woofjoybackend.entity.*;
import org.woof.woofjoybackend.repository.ClienteRepository;
import org.woof.woofjoybackend.repository.ParceiroRepository;
import org.woof.woofjoybackend.entity.object.Item;
import org.woof.woofjoybackend.repository.ItemRepository;
import org.woof.woofjoybackend.repository.UsuarioRepository;
import org.woof.woofjoybackend.service.autenticacao.UsuarioLoginDto;
import org.woof.woofjoybackend.service.autenticacao.UsuarioTokenDto;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class ServiceUser {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final ClienteRepository clienteRepository;
    private final ParceiroRepository parceiroRepository;
    private final ItemRepository itemRepository;
    private final GerenciadorTokenJwt gerenciadorTokenJwt;
    private final AuthenticationManager authenticationManager;

    public void cadastrarUsuario(Usuario usuario, int tipo) {
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);

        usuarioRepository.save(usuario);
        if (tipo == 0) {
            clienteRepository.save(new Cliente(usuario));
            return;
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

    public boolean idExiste(Integer id) {
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

    public Item cadastrarItem(Item it, Integer idUsuario) {
        Usuario usuario = listaUsuarioPorId(idUsuario);
        it.setDono(usuario);
        return itemRepository.save(it);
    }

    public Item listaItemPorId(Integer id) {
        return itemRepository.findById(id).get();
    }

    public Item attItem(Item it, Integer idItem) {
        it.setId(idItem);
        return itemRepository.save(it);
    }

    public void deleteItem(Integer idItem) {
        itemRepository.deleteById(idItem);
    }

    public UsuarioTokenDto autenticar(UsuarioLoginDto usuarioLoginDto) {

        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                usuarioLoginDto.getEmail(), usuarioLoginDto.getSenha());

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Usuario usuarioAutenticado =
                usuarioRepository.findByEmail(usuarioLoginDto.getEmail())
                        .orElseThrow(
                                () -> new ResponseStatusException(404, "Email do usuário não cadastrado", null)
                        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);

        return UsuarioMapper.of(usuarioAutenticado, token);
    }

//    public void criar(UsuarioCriacaoDto usuarioCriacaoDto){
//        final Usuario novoUsuario = UsuarioMapper.of(usuarioCriacaoDto);
//        this.usuarioRepository.save(novoUsuario);
//    }

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