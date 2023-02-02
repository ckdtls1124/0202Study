package org.spring.websocket1.controller;

import lombok.RequiredArgsConstructor;
import org.spring.websocket1.webchat.WebSocketChat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final WebSocketChat webSocketChat;

    @GetMapping("/chat")
    public String chat(){
        return "chat";
    }
}
