package com.management.employee.service;

import org.springframework.stereotype.Service;

@Service
public class OtpService{

    public String generateOtp(){
    int otp =(int)(Math.random()*90000)+100000;

    return String.valueOf(otp);

    }
}