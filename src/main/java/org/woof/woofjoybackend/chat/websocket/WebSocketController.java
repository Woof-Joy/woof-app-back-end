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
    public ResponseEntity<MensagemResponseDto> sendMessage(@RequestBody MessageDto messageDto) {

        return ResponseEntity.status(201).body(MessageMapper.toResponseDto(webSocketService.sendMessage(messageDto)));
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

    @GetMapping("/topicos/{idUsuario}")
    public ResponseEntity<List<String>> getTopicosByUser(@PathVariable Integer idUsuario){
        List<String> lista = webSocketService.getTopicoByUser(idUsuario);

        if (lista.isEmpty()){
            return ResponseEntity.status(204).body(lista);
        }

        return ResponseEntity.ok(lista);
    }

}
