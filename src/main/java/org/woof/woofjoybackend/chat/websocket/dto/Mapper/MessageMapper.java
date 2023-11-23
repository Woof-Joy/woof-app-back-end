package org.woof.woofjoybackend.chat.websocket.dto.Mapper;

import org.woof.woofjoybackend.chat.websocket.dto.MensagemResponseDto;
import org.woof.woofjoybackend.chat.websocket.dto.MessageDto;
import org.woof.woofjoybackend.entity.Avaliacao;
import org.woof.woofjoybackend.entity.Chat;
import org.woof.woofjoybackend.entity.Mensagem;
import org.woof.woofjoybackend.entity.Usuario;

import java.util.ArrayList;
import java.util.List;

public class MessageMapper {

    public static Mensagem toEntity(MessageDto messageDto, Usuario remetente, Chat chat){

        Mensagem msg = new Mensagem();
        msg.setMensagem(messageDto.getMessage());
        msg.setFkRemetente(remetente);
        msg.setFkChat(chat);
        return msg;
    }

    public static List<MensagemResponseDto> toResponseDtoList(List<Mensagem> listaMensagem){
        List<MensagemResponseDto> listaDTO = new ArrayList<>();
        for (Mensagem m :
                listaMensagem) {
            listaDTO.add(toResponseDto(m));
        }
        return listaDTO;
    }

    public static MensagemResponseDto toResponseDto(Mensagem m){
        MensagemResponseDto dto = new MensagemResponseDto();
        dto.setId(m.getId());
        dto.setMensagem(m.getMensagem());
        dto.setVisto(m.getVisto());
        dto.setDataHora(m.getDataHora());
        dto.setFkRemetente(m.getFkRemetente().getId());

        return dto;
    }
}
