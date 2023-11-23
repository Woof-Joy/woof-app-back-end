package org.woof.woofjoybackend.chat.websocket.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class MensagemResponseDto {
    private Integer id;
    private String mensagem;
    private LocalDateTime dataHora;
    private Boolean visto;
    private Integer fkRemetente;
}
