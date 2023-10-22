package org.woof.woofjoybackend.configuration.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.woof.woofjoybackend.service.AutenticacaoService;
import org.woof.woofjoybackend.service.autenticacao.UsuarioDetalhesDto;
import org.woof.woofjoybackend.service.autenticacao.UsuarioLoginDto;

@Component
@RequiredArgsConstructor
public class AuthenticationProvider implements org.springframework.security.authentication.AuthenticationProvider {
    private final AutenticacaoService usuarioAutorizacaoService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return null;
    }

    public Authentication authenticate(Authentication authentication, String role) throws AuthenticationException {
        final String username = authentication.getName();
        final String password = authentication.getCredentials().toString();

        UsuarioDetalhesDto userDetails = this.usuarioAutorizacaoService.loadUserByUsername(username);

        String senha = userDetails.getPassword();
        String cargo = userDetails.getRole();
        //	MUDO AQUI

        if (this.passwordEncoder.matches(password, senha)) {
            if ((role.equalsIgnoreCase(cargo) || cargo.equalsIgnoreCase("A"))) {
                return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            } else {
                role = role.equalsIgnoreCase("C") ? "cliente" : role.equalsIgnoreCase("P") ? "parceiro" : role;
                throw new BadCredentialsException(String.format("Usuário não cadastrado como %s", role));
            }
        } else {
            throw new BadCredentialsException("Usuario ou Senha inválidos");
        }
    }

    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
