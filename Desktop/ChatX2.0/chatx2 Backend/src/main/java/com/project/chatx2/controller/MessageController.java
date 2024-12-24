package com.project.chatx2.controller;

import com.project.chatx2.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/message")
@CrossOrigin(origins = "http://localhost:5173")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/getMessages")
    public ResponseEntity<?> getMessages(@RequestParam String inboxId) {
        return messageService.getMessage(inboxId);
    }

    @MessageMapping("/sendMessage/{inboxId}") // Maps to "/app/sendMessage"
    @SendTo("/topic/messages/{inboxId}") // Broadcast to all subscribers of "/topic/messages"
    public ResponseEntity<?> sendMessage(@DestinationVariable String inboxId, Map<String, Object> message) {
        System.out.println(inboxId);
        return messageService.sendMessage(message);

    }
}
