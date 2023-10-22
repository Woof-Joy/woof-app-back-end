package org.woof.woofjoybackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.woof.woofjoybackend.configuration.security.AuthenticationProvider;
import org.woof.woofjoybackend.configuration.security.jwt.GerenciadorTokenJwt;
import org.woof.woofjoybackend.entity.*;
import org.woof.woofjoybackend.repository.ClienteRepository;
import org.woof.woofjoybackend.repository.ParceiroRepository;
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
    private final ClienteRepository clienteRepository;
    private final ParceiroRepository parceiroRepository;
    private final PasswordEncoder passwordEncoder;
    private final GerenciadorTokenJwt gerenciadorTokenJwt;
    private final AuthenticationProvider authenticationProvider;


    public void postUsuario(Usuario usuario, int tipo) {
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);

        if (!usuarioExiste(usuario.getEmail())) {
            usuarioRepository.save(usuario);
        }
        String email = usuario.getEmail();
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findByEmail(email);
        Usuario usuarioFk = usuarioEncontrado.get();

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

    public boolean deleteUsuario(Integer id, String role) {
        if (existsById(id)) {
            Usuario usuario = usuarioRepository.findById(id).get();
            String cargo = usuario.getRole();

            if (cargo.equalsIgnoreCase(role) || cargo.equalsIgnoreCase("A")) {

                switch (cargo) {
                    case "P":
                    case "C":
                        usuarioRepository.deleteById(id);
                        break;
                    case "A":
                        if (role.equalsIgnoreCase("C")) {
                            usuario.setCliente(null);
                        } else if (role.equalsIgnoreCase("P")) {
                            usuario.setParceiro(null);
                        } else {
                            return false;
                        }
                        usuarioRepository.save(usuario);
                        break;
                }

                return true;
            }
        }
        return false;
        //	MUDO AQUI
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
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findByEmail(email);
        //VERIFICA SE O USUARIO NO BANCO TEM UM CLIENTE OU PARCEIRO CADASTRADO
        if (usuarioEncontrado.isPresent()) {
            Optional<Cliente> cliente = usuarioEncontrado.get().getCliente();
            Optional<Parceiro> parceiro = usuarioEncontrado.get().getParceiro();
            if (tipo == 0 && cliente.isEmpty()) {
                return true;
            }
            if (tipo == 1 && parceiro.isEmpty()) {
                return true;
            }
        }
        if (usuarioEncontrado.isEmpty()) {
            return true;
        }
        return false;
    }

    public boolean usuarioExiste(String email) {
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findByEmail(email);
        if (usuarioEncontrado.isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean cadastrado(Usuario usuario, int tipo) {
        if (tipo == 0 && usuario.getCliente().isEmpty() || tipo == 1 && usuario.getParceiro().isEmpty()) {
            return false;
        }
        return true;
    }


    public UsuarioTokenDto autenticar(UsuarioLoginDto usuarioLoginDto) {

        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                usuarioLoginDto.getEmail(), usuarioLoginDto.getSenha());

        final Authentication authentication = this.authenticationProvider.authenticate(credentials, usuarioLoginDto.getRole());

        Usuario usuarioAutenticado =
                usuarioRepository.findByEmail(usuarioLoginDto.getEmail())
                        .orElseThrow(
                                () -> new ResponseStatusException(404, "Email do usuário não cadastrado", null)
                        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication, usuarioLoginDto.getRole());

        return UsuarioMapper.of(usuarioAutenticado, token);
    }

}