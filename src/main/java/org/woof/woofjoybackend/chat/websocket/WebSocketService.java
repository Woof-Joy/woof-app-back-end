package org.woof.woofjoybackend.chat.websocket;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.woof.woofjoybackend.chat.websocket.dto.Mapper.MessageMapper;
import org.woof.woofjoybackend.chat.websocket.dto.MessageDto;
import org.woof.woofjoybackend.entity.Chat;
import org.woof.woofjoybackend.entity.Mensagem;
import org.woof.woofjoybackend.entity.Usuario;
import org.woof.woofjoybackend.repository.ChatRepository;
import org.woof.woofjoybackend.repository.MensagemRepository;
import org.woof.woofjoybackend.repository.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WebSocketService {

    private final SimpMessagingTemplate template;
    private final MensagemRepository mensagemRepository;
    private final ChatRepository chatRepository;
    private final UsuarioRepository usuarioRepository;

    public void sendMessage(MessageDto message) {
        template.convertAndSend(getTopico(message), message.getMessage());

        Usuario remetente = usuarioRepository.findById(message.getIdRemetente()).orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404)));
        Chat chat = chatRepository.findByArgs(message.getTipo(), message.getIdRemetente(), message.getIdDestinatario()).get();
        mensagemRepository.save(MessageMapper.toEntity(message, remetente, chat));
    }

    public String getTopico(MessageDto messageDto) {
        Optional<Chat> chat = chatRepository.findByArgs(messageDto.getTipo(), messageDto.getIdRemetente(), messageDto.getIdDestinatario());
        if (chat.isPresent()) {
            return chat.get().getTopico();
        }

        Usuario remetente = usuarioRepository.findById(messageDto.getIdRemetente()).orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404)));
        Usuario destinatario = usuarioRepository.findById(messageDto.getIdDestinatario()).orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404)));

        Chat novoChat = new Chat(messageDto.getTipo(), remetente, destinatario);
        return chatRepository.save(novoChat).getTopico();
    }

    public List<Mensagem> getMensagemByChat(String tipo, Integer idRemetente, Integer idDestinatario) {
        Chat chat = chatRepository.findByArgs(tipo, idRemetente, idDestinatario).orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404)));
        return mensagemRepository.findAllByFkChatOrderByDataHora(chat);
    }

    public List<Chat> getChatsByUser(Integer idUsuario){
        return chatRepository.findByUser(idUsuario);
    }

    public List<String> getTopicoByUser(Integer idUsuario){
        List<String> topicos = new ArrayList<>();
        List<Chat> chats = chatRepository.findByUser(idUsuario);

        for (Chat c : chats){
            topicos.add(c.getTopico());
        }

        return topicos;
    }
}
