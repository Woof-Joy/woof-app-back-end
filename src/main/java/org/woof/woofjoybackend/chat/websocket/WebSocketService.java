package org.woof.woofjoybackend.chat.websocket;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.woof.woofjoybackend.chat.websocket.dto.MessageDto;

@Service
public class WebSocketService {

    private final SimpMessagingTemplate template;

    public WebSocketService(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void sendMessage(MessageDto message) {
        template.convertAndSend("/topic/message", message.getMessage());
    }
}
