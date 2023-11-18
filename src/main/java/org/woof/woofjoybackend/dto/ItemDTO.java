package org.woof.woofjoybackend.dto;

import lombok.Data;

@Data
public class ItemDTO {
    private String nomeDoador;
    private String categoria;
    private String titulo;
    private UsuarioEnderecoFeedDTO endereco;
    private String descricao;
    private String estado;

}
