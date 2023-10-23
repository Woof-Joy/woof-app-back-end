package org.woof.woofjoybackend.service;

import lombok.RequiredArgsConstructor;
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
import org.woof.woofjoybackend.repository.UsuarioRepository;
import org.woof.woofjoybackend.service.autenticacao.UsuarioLoginDto;
import org.woof.woofjoybackend.service.autenticacao.UsuarioTokenDto;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ServiceUser {
    private final UsuarioRepository usuarioRepository;
    private final ClienteRepository clienteRepository;
    private final ParceiroRepository parceiroRepository;
    private final PasswordEncoder passwordEncoder;
    private final GerenciadorTokenJwt gerenciadorTokenJwt;
    private final AuthenticationManager authenticationManager;


    public void postUsuario(Usuario usuario, int tipo) {
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        if (!usuarioExiste(usuario.getEmail())) {
            usuarioRepository.save(usuario);
        }
        String email = usuario.getEmail();
        List<Usuario> usuarioEncontrado = usuarioRepository.findByEmail(email);
        Usuario usuarioFk = usuarioEncontrado.get(0);

        if (tipo == 0) {
            Cliente cliente = new Cliente(usuarioFk);
            usuario.setCliente(cliente);
            clienteRepository.save(cliente);
            return;
        }
        Parceiro parceiro = new Parceiro(usuarioFk);
        usuario.setParceiro(parceiro);
        parceiroRepository.save(parceiro);
    }

    public boolean putUsuario(Usuario usuario, int id) {
        Optional<Usuario> usuarioOriginal = usuarioRepository.findById(id);
        if (usuarioOriginal.isPresent()) {
            usuario.setId(id);
            usuarioRepository.save(usuario);
            return true;
        }
        return false;
    }

    public boolean deleteUsuario(Integer id) {
        if (existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Usuario> listaUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario listaUsuarioPorId(Integer id) {
        return usuarioRepository.findById(id).get();
    }


    public boolean existsById(Integer id) {
        return usuarioRepository.existsById(id);
    }

    public boolean usuarioPodeSerCadastrado(Usuario usuario, int tipo) {
        String email = usuario.getEmail();
        List<Usuario> usuarioEncontrado = usuarioRepository.findByEmail(email);
        //VERIFICA SE O USUARIO NO BANCO TEM UM CLIENTE OU PARCEIRO CADASTRADO
        if (usuarioEncontrado.size() > 0) {
            Cliente cliente = usuarioEncontrado.get(0).getCliente();
            Parceiro parceiro = usuarioEncontrado.get(0).getParceiro();
            if (tipo == 0 && isNull(cliente)) {
                return true;
            }
            if (tipo == 1 && isNull(parceiro)) {
                return true;
            }
        }
        if (usuarioEncontrado.size() <= 0 || usuarioEncontrado.isEmpty()) {
            return true;
        }
        return false;
    }

    public boolean usuarioExiste(String email) {
        List<Usuario> usuarioEncontrado = usuarioRepository.findByEmail(email);
        if (usuarioEncontrado.size() <= 0 || usuarioEncontrado.isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean cadastrado(Usuario usuario, int tipo) {
        if (tipo == 0 && isNull(usuario.getCliente()) || tipo == 1 && isNull(usuario.getParceiro())) {
            return false;
        }
        return true;
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

}