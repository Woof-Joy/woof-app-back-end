package org.woof.woofjoybackend.chat.websocket.dto.Mapper;

import org.woof.woofjoybackend.chat.websocket.dto.ChatResponseDto;
import org.woof.woofjoybackend.entity.Chat;

import java.util.List;

public class ChatMapper {

    public static ChatResponseDto toResponseDtoList(List<Chat> listaChat) {
        ChatResponseDto dto = new ChatResponseDto();

        for (Chat c : listaChat) {
            if (c.getTipo().equalsIgnoreCase("doacao")) {
                dto.getDoacao().add(c);
            } else {
                dto.getServico().add(c);
            }
        }

        return dto;
    }
}
