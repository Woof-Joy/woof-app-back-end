package org.woof.woofjoybackend.chat.websocket.dto;

import lombok.Data;
import org.woof.woofjoybackend.entity.Chat;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChatResponseDto {
    private List<Chat> Doacao;
    private List<Chat> Servico;

    public ChatResponseDto() {
        Doacao = new ArrayList<>();
        Servico = new ArrayList<>();
    }
}
