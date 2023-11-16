package org.woof.woofjoybackend.chat.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.woof.woofjoybackend.chat.websocket.dto.MessageDto;

@RestController
@RequestMapping("/notification")
public class WebSocketController {
    @Autowired
    private WebSocketService webSocketService;

    @PostMapping
    @CrossOrigin
    public void sendMessage(@RequestBody MessageDto messageDto){
        webSocketService.sendMessage(messageDto);
    }

}
