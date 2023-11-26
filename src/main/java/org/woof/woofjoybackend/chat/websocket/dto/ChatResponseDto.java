package org.woof.woofjoybackend.chat.websocket.dto;

import lombok.Data;
import org.woof.woofjoybackend.dto.UsuarioChatDTO;

@Data
public class ChatResponseDto {
    private Integer id;
    private UsuarioChatDTO usuario1;
    private UsuarioChatDTO usuario2;
    private String topico;
}
