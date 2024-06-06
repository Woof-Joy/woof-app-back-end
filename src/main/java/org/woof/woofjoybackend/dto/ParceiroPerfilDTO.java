package org.woof.woofjoybackend.dto;

import jakarta.validation.constraints.DecimalMin;
import jdk.jfr.BooleanFlag;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
public class ParceiroPerfilDTO {
    private Integer idUser;
    private String nome;
    private LocalDate dataEntrada;
    private List<FichaServicoParceiroDTO> fichas;
    private Integer maxDogs;
    private Boolean aceitaDogEspecial;
    private Boolean aceitaDogIdoso;
    private Boolean aceitaDogBravo;
    private Boolean aceitaDogGrande;
    private Boolean aceitaDogCio;
    private Boolean premium;
    private String imgParceiro;
    private String descricao;
}