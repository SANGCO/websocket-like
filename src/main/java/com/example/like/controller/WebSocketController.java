package com.example.like.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSocketController {

    @MessageMapping("/like")
    @SendTo("/topic/message")
    public String broadcasting(String message) {
        return message;
    }

}
