package org.woof.woofjoybackend.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;
import org.woof.woofjoybackend.entity.FichaServico;

import java.time.LocalDate;
import java.util.List;

@Data
public class ParceiroAvaliacaoFeedDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    @Size(max = 50)
    private String nome;
    @Size(max = 50)
    private String sobrenome;
    private Double avaliacao;
    private ParceiroEnderecoFeedDTO endereco;
    @Size(max = 500)
    private String descricao;
    private List<ParceiroFichaServicoDTO> servicos;
}
