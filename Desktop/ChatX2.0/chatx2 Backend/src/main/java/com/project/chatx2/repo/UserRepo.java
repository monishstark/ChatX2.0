package com.project.chatx2.repo;

import com.project.chatx2.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<Users,Integer> {
    Users findByPhone(String phone);

    @Query("select u from Users u where u.name like %?1%")
    List<Users> startsWith(String name);

    Users findByUserid(int userId);
}
