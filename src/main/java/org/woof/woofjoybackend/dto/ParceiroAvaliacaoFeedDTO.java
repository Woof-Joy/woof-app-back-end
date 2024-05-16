package org.woof.woofjoybackend.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.List;

@Data
public class ParceiroAvaliacaoFeedDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String sobrenome;
    private Double avaliacao;
    private UsuarioEnderecoFeedDTO endereco;
    private String descricao;
    private List<ParceiroFichaServicoDTO> servicos;
}