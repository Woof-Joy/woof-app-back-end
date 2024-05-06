package org.woof.woofjoybackend.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ServicoFichaDTO {
    private LocalDateTime dataHoraFim;
    private LocalDateTime dataHoraInicio;
    private String cliente;
}