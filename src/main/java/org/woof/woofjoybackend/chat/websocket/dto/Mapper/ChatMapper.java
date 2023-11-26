package org.woof.woofjoybackend.chat.websocket.dto.Mapper;

import org.woof.woofjoybackend.chat.websocket.dto.ChatResponseDto;
import org.woof.woofjoybackend.entity.Chat;

import java.util.ArrayList;
import java.util.List;

public class ChatMapper {

    public static List<ChatResponseDto> toResponseDtoList(List<Chat> listaChat) {
        List<ChatResponseDto> listaDto = new ArrayList<>();

        for (Chat c : listaChat) {
            listaDto.add(ChatMapper.toResponseDto(c));
        }

        return listaDto;
    }

    public static ChatResponseDto toResponseDto(Chat chat){
        ChatResponseDto dto = new ChatResponseDto();

        dto.setId(chat.getId());
        dto.setIdUsuario1(chat.getFkRemetente().getId());
        dto.setIdUsuario2(chat.getFkDestinatario().getId());
        dto.setTopico(chat.getTopico());

        return dto;
    }
}
