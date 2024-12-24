package com.project.chatx2.repo;

import com.project.chatx2.model.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepo extends JpaRepository<Messages,Integer> {
    List<Messages> findByInboxId(String inboxId);
}
