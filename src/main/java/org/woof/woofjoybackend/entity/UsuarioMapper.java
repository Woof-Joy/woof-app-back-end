package org.woof.woofjoybackend.entity;

import org.woof.woofjoybackend.service.autenticacao.UsuarioTokenDto;

public class UsuarioMapper {

    public static UsuarioTokenDto of(Usuario usuario, String token){
        UsuarioTokenDto usuarioTokenDto = new UsuarioTokenDto();

        usuarioTokenDto.setEmail(usuario.getEmail());
        usuarioTokenDto.setNome(usuario.getNome());
        usuarioTokenDto.setRole(usuario.getRole());
        usuarioTokenDto.setToken(token);

        return usuarioTokenDto;
    }
}
