package org.woof.woofjoybackend.dto.mapper;

import org.woof.woofjoybackend.domain.entity.Usuario;
import org.woof.woofjoybackend.service.autenticacao.UsuarioTokenDto;

public class UsuarioMapperJWT {

    public static UsuarioTokenDto of(Usuario usuario, String token){
        UsuarioTokenDto usuarioTokenDto = new UsuarioTokenDto();

        usuarioTokenDto.setUserId(usuario.getId());
        usuarioTokenDto.setEmail(usuario.getEmail());
        usuarioTokenDto.setNome(usuario.getNome());
        usuarioTokenDto.setRole(usuario.getRole());
        usuarioTokenDto.setToken(token);

        return usuarioTokenDto;
    }
}
