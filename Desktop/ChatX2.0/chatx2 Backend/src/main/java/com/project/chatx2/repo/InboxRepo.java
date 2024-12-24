package com.project.chatx2.repo;

import com.project.chatx2.model.Inbox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InboxRepo  extends JpaRepository<Inbox,Integer> {
    Inbox findByInboxId(String inboxId);

}
