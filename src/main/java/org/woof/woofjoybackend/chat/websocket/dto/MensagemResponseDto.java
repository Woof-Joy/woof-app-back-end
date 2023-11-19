package org.woof.woofjoybackend.chat.websocket.dto;

import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.woof.woofjoybackend.entity.Chat;
import org.woof.woofjoybackend.entity.Usuario;

import java.time.LocalDate;

@Data
public class MensagemResponseDto {
    private Integer id;
    private String mensagem;
    private LocalDate dataHora;
    private Boolean visto;
    private Integer fkRemetente;
}
