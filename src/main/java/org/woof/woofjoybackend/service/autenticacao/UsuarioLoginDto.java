package org.woof.woofjoybackend.service.autenticacao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioLoginDto {
    private String email;
    private String senha;
}
