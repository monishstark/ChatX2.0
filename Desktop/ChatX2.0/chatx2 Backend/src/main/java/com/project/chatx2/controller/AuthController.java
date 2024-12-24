package com.project.chatx2.controller;


import com.project.chatx2.model.Otp;
import com.project.chatx2.model.Users;
import com.project.chatx2.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
public  class AuthController {
    public AuthController() {
    }
    @Autowired
    AuthService authService;

    @PostMapping("/sendOtp")
    public String sendOtp(@RequestBody Otp phone){

        System.out.println("sending otp");
        return authService.sendOtp(phone);
    }

    @PostMapping("/verifyOtp")
    public ResponseEntity<?> verifyOtp(@RequestBody Otp body){
        return authService.verifyOtp(body);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Users body){
        return authService.register(body);
    }


}