package org.woof.woofjoybackend.chat.websocket.dto;

import lombok.Data;

@Data
public class ChatResponseDto {
    private Integer id;
    private Integer idUsuario1;
    private Integer idUsuario2;
    private String topico;
}
