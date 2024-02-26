package org.woof.woofjoybackend.dto;

import jakarta.persistence.*;
import lombok.Data;

@Data
public class ServicoDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private AvaliacaoDTO avaliacao;
}
