package com.project.chatx2.service;

import com.project.chatx2.model.Inbox;
import com.project.chatx2.model.Inboxuser;
import com.project.chatx2.model.Users;
import com.project.chatx2.repo.InboxRepo;
import com.project.chatx2.repo.InboxuserRepo;
import com.project.chatx2.repo.UserRepo;
import com.project.chatx2.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChatService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private InboxuserRepo inboxUserRepo;

    @Autowired
    private InboxRepo inboxRepo;
    public ResponseEntity<?> getUsers(String name,int userId){
        List<Users> users = userRepo.startsWith(name);
        List<Map<String,Object>> reponse = new ArrayList<>();
        for (Users user : users) {
            int firstUserId = userId;
            int secondUserId = user.getUserid();
            String inboxId = (firstUserId < secondUserId) ? firstUserId+"_"+secondUserId : secondUserId+"_"+firstUserId;
            System.out.println(inboxId);
            Inboxuser inboxUser = inboxUserRepo.findByUserIdAndInboxId(secondUserId,inboxId);
            Map<String,Object> userMap = new HashMap<>();

            userMap.put("id",user.getId());
            userMap.put("name",user.getName());
            userMap.put("phone",user.getPhone());
            userMap.put("userid",user.getUserid());
            userMap.put("avatar",user.getAvatar());
            userMap.put("inboxid", (inboxUser != null) ? inboxUser.getInboxId() : -1);
            reponse.add(userMap);
        }
        return ResponseEntity.ok(reponse);

    }

    public ResponseEntity<?> getChats(String token){
        int userId = JwtUtil.getUserIdFromToken(token);
        List<Inboxuser> inboxUsers = inboxUserRepo.findByUserId(userId);

        List<Map<String,Object>> inboxes = new ArrayList<>();
        for (Inboxuser inboxUser : inboxUsers) {
            Map<String,Object> response = new HashMap<>();
            Inbox inbox = inboxRepo.findByInboxId(inboxUser.getInboxId());
            String inboxId =inbox.getInboxId();
            int reciverId= getReciverId(inboxId ,userId);
            Users user = userRepo.findByUserid(reciverId);
            response.put("inboxId",inbox.getInboxId());
            response.put("name",user.getName());
            response.put("avatar",user.getAvatar());
            response.put("lastMessage",inbox.getLastMessage());
            response.put("lastSentUserId",inbox.getLastSentUserId());
            response.put("reciverId",reciverId);
            inboxes.add(response);

        }

        return ResponseEntity.ok(inboxes);}

    public int getReciverId(String inboxId,int userId){
        String[] ids = inboxId.split("_");
        int firstUserId = Integer.parseInt(ids[0]);
        int secondUserId = Integer.parseInt(ids[1]);
        return (firstUserId == userId) ? secondUserId : firstUserId;
    }
}

//1 eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2OTk0NDkyMjEiLCJpYXQiOjE3MzE5NTY3MTQsImV4cCI6MTczMjA0MzExNH0.cypDRh_tBk-3f-2LPIk-gjWiqUotun6xh0bGir7aRy8
//2 eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2OTk0NDkyMjAiLCJpYXQiOjE3MzE5NTY3NTYsImV4cCI6MTczMjA0MzE1Nn0.FVctWlVj76yU7pxNT-vpHCTdOpdAMlXrao-WRI0g0AM
//3 eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2OTk0NDkyMTkiLCJpYXQiOjE3MzE5NTY4MDAsImV4cCI6MTczMjA0MzIwMH0.s4UWmkxi0Nu9OFpQ2oTgJop-TTMaKDV09aYnkqV2l9k