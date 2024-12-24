package com.project.chatx2.repo;
import com.project.chatx2.model.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  OtpRepo extends JpaRepository<Otp,Integer> {
    Otp findByPhone(String phone);

}
