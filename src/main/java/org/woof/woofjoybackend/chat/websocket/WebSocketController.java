package org.woof.woofjoybackend.chat.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.woof.woofjoybackend.chat.websocket.dto.ChatResponseDto;
import org.woof.woofjoybackend.chat.websocket.dto.Mapper.ChatMapper;
import org.woof.woofjoybackend.chat.websocket.dto.Mapper.MessageMapper;
import org.woof.woofjoybackend.chat.websocket.dto.MensagemResponseDto;
import org.woof.woofjoybackend.chat.websocket.dto.MessageDto;
import org.woof.woofjoybackend.domain.entity.Chat;
import org.woof.woofjoybackend.domain.entity.Mensagem;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/notification")
public class WebSocketController {
    @Autowired
    private WebSocketService webSocketService;
    @Async
    @PostMapping
    public CompletableFuture<ResponseEntity<MessageDto>> sendMessage(@RequestBody MessageDto messageDto) {
        MessageMapper.toResponseDto(webSocketService.sendMessage(messageDto));
        return CompletableFuture.completedFuture(
                ResponseEntity.status(201).body(messageDto)
        );
    }

    @GetMapping("/{idRemetente}/{idDestinatario}")
    public ResponseEntity<List<MensagemResponseDto>> getMensagemByChat(@PathVariable Integer idRemetente, @PathVariable Integer idDestinatario){
        List<Mensagem> lista = webSocketService.getMensagemByChat(idRemetente, idDestinatario);

        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(MessageMapper.toResponseDtoList(lista));
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<List<ChatResponseDto>> getChatsByUser(@PathVariable Integer idUsuario){
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
