package com.project.chatx2.repo;

import com.project.chatx2.model.Inboxuser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InboxuserRepo extends JpaRepository<Inboxuser,Integer> {
    List<Inboxuser> findByUserId(int userId);

    Inboxuser findByUserIdAndInboxId(int userId, String inboxId);
}
