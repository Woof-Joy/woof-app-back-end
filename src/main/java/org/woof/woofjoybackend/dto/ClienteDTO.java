package org.woof.woofjoybackend.dto;

import lombok.Data;

import java.util.List;

@Data
public class ClienteDTO {
    private Integer idCliente;
    private List<ClienteDogDTO> dogList;
}
