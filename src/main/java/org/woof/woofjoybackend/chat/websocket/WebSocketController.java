package org.woof.woofjoybackend.chat.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.woof.woofjoybackend.chat.websocket.dto.ChatResponseDto;
import org.woof.woofjoybackend.chat.websocket.dto.Mapper.ChatMapper;
import org.woof.woofjoybackend.chat.websocket.dto.Mapper.MessageMapper;
import org.woof.woofjoybackend.chat.websocket.dto.MensagemResponseDto;
import org.woof.woofjoybackend.chat.websocket.dto.MessageDto;
import org.woof.woofjoybackend.entity.Chat;
import org.woof.woofjoybackend.entity.Mensagem;

import java.util.List;

@RestController
@RequestMapping("/notification")
public class WebSocketController {
    @Autowired
    private WebSocketService webSocketService;

    @PostMapping
    @CrossOrigin
    public void sendMessage(@RequestBody MessageDto messageDto) {
        webSocketService.sendMessage(messageDto);
    }

    @GetMapping("/{tipo}/{idRemetente}/{idDestinatario}")
    public ResponseEntity<List<MensagemResponseDto>> getMensagemByChat(@PathVariable String tipo, @PathVariable Integer idRemetente, @PathVariable Integer idDestinatario){
        List<Mensagem> lista = webSocketService.getMensagemByChat(tipo, idRemetente, idDestinatario);

        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(MessageMapper.toResponseDtoList(lista));
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<ChatResponseDto> getChatsByUser(@PathVariable Integer idUsuario){
        List<Chat> lista = webSocketService.getChatsByUser(idUsuario);

        if (lista.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(ChatMapper.toResponseDtoList(lista));
    }


}
