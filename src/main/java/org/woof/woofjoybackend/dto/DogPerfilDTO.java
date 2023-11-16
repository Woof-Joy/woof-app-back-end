package org.woof.woofjoybackend.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jdk.jfr.BooleanFlag;
import lombok.Data;


import java.time.LocalDate;
import java.util.List;

@Data
public class DogPerfilDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    @Size(max = 50)
    private String nome;
    @Past
    private LocalDate dtNasc;
    @Size(max = 200)
    private String imgCachorro;
    @BooleanFlag
    private Boolean rga;
    @NotNull
    @DecimalMax("30.0")
    private Double peso;
    @NotBlank
    @Size(max = 70)
    private String raca;
    @NotNull
    @Pattern(regexp = "^(pequeno|médio|grande)$", message = "O tamanho deve ser 'pequeno', 'médio' ou 'grande'")
    private String porte;
    @BooleanFlag
    private Boolean convenio;
    @Size(max = 50)
    private String carteirinha;
    @Pattern(regexp = "^[MF]$", message = "O gênero deve ser 'M' ou 'F'")
    private String genero;
    @Min(0)
    @Max(5)
    private Integer agressivo;
    @BooleanFlag
    private Boolean deficiencia;
    private Integer idDono;
    private List<ObservacaoDTO> observacaoList;
}
