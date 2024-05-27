package org.woof.woofjoybackend.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ParceiroSP2DTO {
    private Integer idUser;
    private String nome;
    private String cidade;
    private String uf;
    private Double estrelas;
    private Integer qtdServicosPrestados;
    private String descricao;
    private LocalDate dataEntrada;

}
