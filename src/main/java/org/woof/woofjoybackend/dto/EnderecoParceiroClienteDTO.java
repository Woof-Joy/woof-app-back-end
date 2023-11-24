package org.woof.woofjoybackend.dto;

import lombok.Data;

@Data
public class EnderecoParceiroClienteDTO {
    private String estado = "";
    private String cidade = "";
    private String rua = "";
}
