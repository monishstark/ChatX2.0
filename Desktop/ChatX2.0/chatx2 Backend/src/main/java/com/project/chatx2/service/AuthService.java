package com.project.chatx2.service;

import com.project.chatx2.model.Otp;
import com.project.chatx2.model.Users;
import com.project.chatx2.repo.OtpRepo;
import com.project.chatx2.repo.UserRepo;
import com.project.chatx2.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class AuthService {

    @Autowired
    private OtpRepo otpRepo;

    @Autowired
    private UserRepo userRepo;

    public String  sendOtp(Otp phone) {
        // send otp to phone
        String response = "otp sent";
        try {
            String otp = String.valueOf((int) (Math.random() * 9000) + 1000);
            Otp phoneExist = otpRepo.findByPhone(phone.getPhone());
            if (phoneExist != null) {
                phoneExist.setOtpNO(otp);
                otpRepo.save(phoneExist);
                response = "otp resent";
            } else {
                phone.setOtpNO(otp);
                otpRepo.save(phone);
            }

        } catch (Exception e) {
            System.out.println(e);
            response = "otp not sent";
        }
        return response;
    }

    public ResponseEntity<?> verifyOtp(Otp body){
        try{
            Otp otpObj = otpRepo.findByPhone(body.getPhone());
            if(otpObj == null){
                return ResponseEntity.status(404).body("OTP not found");
            }
            if(true ){//otpObj.getOtpNO().equals(body.getOtpNO())
                Users user = userRepo.findByPhone(body.getPhone());
                if (user == null) {

                    return ResponseEntity.status(404).body("User not found");
                }

                String token = JwtUtil.generateToken(user.getUserid());
                Map<String,Object> response =new HashMap<>();
                response.put("token",token);
                response.put("userDetails",user);
                return ResponseEntity.ok().body(response);
            }
            return ResponseEntity.status(401).body("Invalid OTP");

        }
        catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(500).body("Internal Server Error");
        }

    }

    public ResponseEntity<?> register(Users body){
        try{
            Users user = userRepo.findByPhone(body.getPhone());
            if(user != null){
                return ResponseEntity.status(409).body("User already exists");
            }
            int userid=Math.abs(body.getPhone().hashCode());
            System.out.println(userid);
            body.setUserid(userid);
            userRepo.save(body);

            return ResponseEntity.ok().body("User registered successfully");
        }
        catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }
}
