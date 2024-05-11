package org.woof.woofjoybackend.dto;

import lombok.Data;
import org.woof.woofjoybackend.domain.entity.Endereco;

@Data
public class ItemDTO {
    private Integer id;
    private String categoria;
    private String titulo;
    private Endereco endereco;
    private String descricao;
    private String imagem;
    private Integer idDonoItem;
}
