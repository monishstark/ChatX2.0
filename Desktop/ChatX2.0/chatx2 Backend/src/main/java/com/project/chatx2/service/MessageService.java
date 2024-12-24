package com.project.chatx2.service;

import com.project.chatx2.model.Inboxuser;
import com.project.chatx2.model.Inbox;
import com.project.chatx2.model.Messages;
import com.project.chatx2.repo.InboxRepo;
import com.project.chatx2.repo.InboxuserRepo;
import com.project.chatx2.repo.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service
public class MessageService {

    @Autowired
    private InboxuserRepo inboxUserRepo;

    @Autowired
    private InboxRepo inboxRepo;

    @Autowired
    private MessageRepo messageRepo;

    public ResponseEntity<?> getMessage(String inboxId) {
        if ("-1".equals(inboxId)) {
            return ResponseEntity.status(404).body("Chat not found");
        }

        List<Messages> messages = messageRepo.findByInboxId(inboxId);
        return ResponseEntity.ok(messages);
    }

    public ResponseEntity<?> sendMessage(Map<String, Object> message) {
        String inboxId = message.get("inboxId").toString();
        int senderId = Integer.parseInt(message.get("senderId").toString());
        int receiverId = Integer.parseInt(message.get("receiverId").toString());
        String messageContent = message.get("message").toString();
        LocalDateTime currentTimestamp = LocalDateTime.now();
        System.out.println(messageContent);
        Map<String,Object> response =new HashMap<>();

        if ("-1".equals(inboxId)) {
            String generatedInboxId = generateInbox(senderId, receiverId);
            inboxUserRepo.save(new Inboxuser(senderId, generatedInboxId));
            inboxUserRepo.save(new Inboxuser(recei  verId, generatedInboxId));
            inboxRepo.save(new Inbox(generatedInboxId, messageContent, senderId));
            messageRepo.save(new Messages(generatedInboxId, messageContent, currentTimestamp, senderId));
            response.put("inboxId",generatedInboxId);
            response.put("message",messageContent);
            response.put("createdAt",currentTimestamp);
            response.put("userId",senderId);
        } else {
            Inbox inbox = inboxRepo.findByInboxId(inboxId);
            inbox.setLastMessage(messageContent);
            inbox.setLastSentUserId(senderId);
            inboxRepo.save(inbox);
            messageRepo.save(new Messages(inboxId, messageContent, currentTimestamp, senderId));
            response.put("inboxId",inboxId);
            response.put("message",messageContent);
            response.put("createdAt",currentTimestamp);
            response.put("userId",senderId);
        }
        return ResponseEntity.ok(response);
    }

    private String generateInbox(int senderId, int receiverId) {
        int first = Math.min(senderId, receiverId);
        int second = Math.max(senderId, receiverId);
        return first + "_" + second;
    }
}
