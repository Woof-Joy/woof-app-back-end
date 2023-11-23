package org.woof.woofjoybackend.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ParceiroDTO {
    @NotBlank
    @Size(max = 50)
    private String nome;
    @Size(max = 50)
    private String sobrenome;
    @Size(min = 8, max = 8)
    private String cep;
    @Size(max = 10)
    private String numero;
    @NotBlank
    @Email
    private String email;
    @Past
    private LocalDate dataEntrada;
    @Max(value = 5)
    @Min(value = 0)
    private Double estrelas;
    @Positive
    private Integer qtdServicosPrestados;
    private List<FichaServicoDTO> servicos;

}

