package org.woof.woofjoybackend.service.users;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.woof.woofjoybackend.configuration.security.AuthenticationProvider;
import org.woof.woofjoybackend.configuration.security.jwt.GerenciadorTokenJwt;
import org.woof.woofjoybackend.domain.entity.*;
import org.woof.woofjoybackend.dto.UsuarioCriacaoDTO;
import org.woof.woofjoybackend.dto.UsuarioDTO;
import org.woof.woofjoybackend.dto.mapper.UsuarioMapper;
import org.woof.woofjoybackend.dto.mapper.UsuarioMapperJWT;
import org.woof.woofjoybackend.repository.ClienteRepository;
import org.woof.woofjoybackend.repository.DonoImagemRepository;
import org.woof.woofjoybackend.repository.ParceiroRepository;
import org.woof.woofjoybackend.repository.UsuarioRepository;
import org.woof.woofjoybackend.service.autenticacao.UsuarioLoginDto;
import org.woof.woofjoybackend.service.autenticacao.UsuarioTokenDto;
import org.woof.woofjoybackend.service.client.ServiceCEP;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServiceUser {
    private final UsuarioRepository usuarioRepository;
    private final ClienteRepository clienteRepository;
    private final DonoImagemRepository donoImagemRepository;
    private final ParceiroRepository parceiroRepository;
    private final PasswordEncoder passwordEncoder;
    private final GerenciadorTokenJwt gerenciadorTokenJwt;
    private final AuthenticationProvider authenticationProvider;
    private final ServiceCEP serviceCep;


    public UsuarioDTO postUsuario(UsuarioCriacaoDTO usuario, String tipo) {
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        if (!usuarioExiste(usuario.getEmail())) {
            String cep = usuario.getCep();
            Endereco enderecoCompleto = serviceCep.registerAdressInUser(cep);
            enderecoCompleto.setNumero(usuario.getNumero());
            Usuario usuarioEntity = UsuarioMapper.toEntity(usuario);
            usuarioEntity.setEndereco(enderecoCompleto);
            Usuario usuario1 = usuarioEntity; //
            usuarioRepository.save(usuarioEntity);
        }
        String email = usuario.getEmail();
        Usuario usuarioEncontrado = usuarioRepository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404)));


        if (tipo.equalsIgnoreCase("C")) {
            Cliente cliente = new Cliente(usuarioEncontrado);
            usuarioEncontrado.setCliente(cliente);
            clienteRepository.save(cliente);
        } else if (tipo.equalsIgnoreCase("P")) {
            Parceiro parceiro = new Parceiro(usuarioEncontrado);
            usuarioEncontrado.setParceiro(parceiro);
            parceiroRepository.save(parceiro);
        }
        return UsuarioMapper.toDto(usuarioRepository.save(usuarioEncontrado));
    }

    public Usuario putUsuario(UsuarioCriacaoDTO usuario, int id) {
        Usuario usuarioOriginal = usuarioRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404)));
        usuarioOriginal.setNome(usuario.getNome());
        usuarioOriginal.setSobrenome(usuario.getSobrenome());
        usuarioOriginal.setCpf(usuario.getCpf());
        usuarioOriginal.getEndereco().setCep(usuario.getCep());
        usuarioOriginal.setEmail(usuario.getEmail());
        usuarioOriginal.setSenha(usuario.getSenha());
        usuarioOriginal.setDataNasc(usuario.getDataNasc());
        usuarioOriginal.getEndereco().setNumero(usuario.getNumero());
        usuarioOriginal.getEndereco().setLogradouro(usuario.getRua());
        usuarioOriginal.getEndereco().setLocalidade(usuario.getCidade());
        usuarioOriginal.getEndereco().setUf(usuario.getEstado());

        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);

        return usuarioRepository.save(usuarioOriginal);
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
    }

    public List<Usuario> listaUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario listaUsuarioPorId(Integer id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404)));
    }


    public boolean existsById(Integer id) {
        return usuarioRepository.existsById(id);
    }

    public boolean usuarioPodeSerCadastrado(String email, String tipo) {
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findByEmail(email);
        //VERIFICA SE O USUARIO NO BANCO TEM UM CLIENTE OU PARCEIRO CADASTRADO
        if (usuarioEncontrado.isPresent()) {
            Optional<Cliente> cliente = usuarioEncontrado.get().getCliente();
            Optional<Parceiro> parceiro = usuarioEncontrado.get().getParceiro();
            if (tipo.equalsIgnoreCase("C") && cliente.isEmpty()) {
                return true;
            }
            if (tipo.equalsIgnoreCase("P") && parceiro.isEmpty()) {
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

        return UsuarioMapperJWT.of(usuarioAutenticado, token);
    }

    public Usuario findByClientId(Integer idCliente){
        return usuarioRepository.findByCliente_IdCliente(idCliente).orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404)));
    }
}