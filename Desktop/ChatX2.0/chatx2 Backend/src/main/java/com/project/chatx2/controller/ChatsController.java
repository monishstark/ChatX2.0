package com.project.chatx2.controller;

import com.project.chatx2.service.ChatService;
import com.project.chatx2.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chats")
@CrossOrigin(origins = "http://localhost:5173")
public class ChatsController {

    @Autowired
    ChatService chatService;

    @GetMapping("/getChats")
    public ResponseEntity<?> getChats(@RequestHeader("Authorization") String token){

        return chatService.getChats(token);
    }

    @GetMapping("/getUsers")
    public ResponseEntity<?> getUsers(@RequestParam String name,@RequestHeader("Authorization") String token){
        System.out.println(name);
        int userId = JwtUtil.getUserIdFromToken(token);
        return chatService.getUsers(name,userId);
    }


}
