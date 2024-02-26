package org.woof.woofjoybackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.woof.woofjoybackend.domain.entity.Usuario;
import org.woof.woofjoybackend.repository.UsuarioRepository;
import org.woof.woofjoybackend.service.autenticacao.UsuarioDetalhesDto;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AutenticacaoService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;

    @Override
    public UsuarioDetalhesDto loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(username);

        if (usuarioOpt.isEmpty()) {
            throw new UsernameNotFoundException(String.format("usuario: %s não encontrado", username));
        }

        return new UsuarioDetalhesDto(usuarioOpt.get());
    }
}
