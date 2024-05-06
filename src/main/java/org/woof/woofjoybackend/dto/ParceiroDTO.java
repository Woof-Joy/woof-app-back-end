
package org.woof.woofjoybackend.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ParceiroDTO {
    private Integer idParceiro;
    private Integer idUser;
    @NotBlank
    @Size(max = 50)
    private String nome;
    @Size(max = 50)
    private String sobrenome;
    @NotBlank
    @Email
    private String email;
    @Past
    private LocalDate dataNasc;
    @Past
    private LocalDate dataEntrada;
    @Max(value = 5)
    @Min(value = 0)
    private Double estrelas;
    @Positive
    private Integer qtdServicosPrestados;
    private List<FichaServicoDTO> servicos;
    private Integer idUsuario;
    private String cidade;
    private String uf;
}