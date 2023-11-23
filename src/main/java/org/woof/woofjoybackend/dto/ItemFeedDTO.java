package org.woof.woofjoybackend.dto;

import lombok.Data;

@Data
public class ItemFeedDTO {
    private String nomeDoador;
    private String categoria;
    private String titulo;
    private UsuarioEnderecoFeedDTO endereco;
    private String descricao;
    private Boolean entrega;
    private Boolean marcaPontoEncontro;
    private Boolean enviaCorreio;
    private Boolean cobraTaxa;
    private Boolean necessarioRetirada;
}
