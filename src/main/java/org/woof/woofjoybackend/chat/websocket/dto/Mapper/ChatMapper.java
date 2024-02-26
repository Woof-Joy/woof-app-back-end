package org.woof.woofjoybackend.chat.websocket.dto.Mapper;

import org.woof.woofjoybackend.chat.websocket.dto.ChatResponseDto;
import org.woof.woofjoybackend.dto.mapper.UsuarioMapper;
import org.woof.woofjoybackend.domain.entity.Chat;

import java.util.ArrayList;
import java.util.List;

public class ChatMapper {

    public static List<ChatResponseDto> toResponseDtoList(List<Chat> listaChat) {
        List<ChatResponseDto> listaDto = new ArrayList<>();

        for (Chat c : listaChat) {
            listaDto.add(toResponseDto(c));
        }

        return listaDto;
    }

    public static ChatResponseDto toResponseDto(Chat chat){
        ChatResponseDto dto = new ChatResponseDto();

        dto.setId(chat.getId());
        dto.setUsuario1(UsuarioMapper.toDtoChat(chat.getFkRemetente()));
        dto.setUsuario2(UsuarioMapper.toDtoChat(chat.getFkDestinatario()));
        dto.setTopico(chat.getTopico());

        return dto;
    }
}
