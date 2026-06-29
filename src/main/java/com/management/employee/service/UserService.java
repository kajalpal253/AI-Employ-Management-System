package com.management.employee.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.management.employee.model.User;
import com.management.employee.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private EmailService emailService;
    
    @Autowired
    private OtpService otpService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public  User registerUser(User user){
         
        String otp = otpService.generateOtp();
        user.setOtp(otp);
        user.setOtpExpire(LocalDateTime.now().plusMinutes(5));
      emailService.sendOtpEmail(user.getEmail(),otp);
         user.setPassword(passwordEncoder.encode(user.getPassword()));

        return repository.save(user);
        
    }

    public User findByUsername(String username){

        return repository.findByUsername(username).orElseThrow(()-> new RuntimeException("User not found"));
    }

    public void saveOtp(String email,String otp){
         System.out.println(email);
         User user = repository.findByEmail(email).
         orElseThrow(()-> new RuntimeException("user not found"));

         user.setOtp(otp);
         user.setOtpExpire(LocalDateTime.now().plusMinutes(6));
         repository.save(user);

    }

    public boolean verifyOtp(String email,String otp){
        User user = repository.findByEmail(email).orElseThrow(()->new RuntimeException("user not found"));
        if(user.getOtp().equals(otp) && user.getOtpExpire().isAfter(LocalDateTime.now())){

            user.setVarified(true);
            repository.save(user);
            return true;
        }
        return false;
    }

    public  User findByEmail(String email){
        return repository.findByEmail(email).orElseThrow(()->new RuntimeException("User not found"));
    }
    
}
