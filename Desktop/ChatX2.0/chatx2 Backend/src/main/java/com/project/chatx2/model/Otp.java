package com.project.chatx2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Otp {
    @Id
    private int id;

    private String phone;
    private String otpNO;

    public Otp() {
    }

    public Otp(String phone, String otpNO) {
        this.phone = phone;
        this.otpNO = otpNO;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOtpNO() {
        return otpNO;
    }

    public void setOtpNO(String otpNO) {
        this.otpNO = otpNO;
    }
}
