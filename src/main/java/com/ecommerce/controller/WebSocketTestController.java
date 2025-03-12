package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class WebSocketTestController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @GetMapping("/send")
    public String sendTestMessage() {
        messagingTemplate.convertAndSend("/admin/notifications", "Test message from backend");
        return "Message Sent!";
    }
}

