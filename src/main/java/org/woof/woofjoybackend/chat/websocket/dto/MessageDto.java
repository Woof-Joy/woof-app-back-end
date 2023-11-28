package org.woof.woofjoybackend.chat.websocket.dto;


import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class MessageDto {
    private String message;
    private Integer idRemetente;
    private Integer idDestinatario;
}
