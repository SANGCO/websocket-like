package com.example.like.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequiredArgsConstructor
public class WebSocketController {

    private StringRedisTemplate redisTemplate;

    public WebSocketController(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @MessageMapping("/like")
    @SendTo("/topic/message")
    public String broadcasting(String message) {
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        values.setIfAbsent("like", "0");
        Long like = values.increment("like");
        String count = "Redis Error";
        if (like != null) {
            count = Long.toString(like);
        }

        return count;
    }

}
