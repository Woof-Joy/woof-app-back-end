package org.woof.woofjoybackend.service.autenticacao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioLoginDto {
    private String email;
    private String role;
    private String senha;
}
//    MUDO DENOVO