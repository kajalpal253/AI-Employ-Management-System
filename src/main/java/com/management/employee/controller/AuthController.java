package com.management.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.employee.dto.LoginRequest;
import com.management.employee.dto.OtpRequest;
import com.management.employee.dto.RegisterRequest;
import com.management.employee.model.User;
import com.management.employee.security.JwtUtil;
import com.management.employee.service.EmailService;
import com.management.employee.service.OtpService;
import com.management.employee.service.UserService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/auth")
public class AuthController{

    @Autowired
    private UserService service;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired 
    private PasswordEncoder passwordEncoder;
    @Autowired
    private OtpService otpservice;
    @Autowired
    private EmailService emailService;

    @PostMapping("/register")
    public User registUser(@RequestBody RegisterRequest request) {
        User user= new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());
       
        return service.registerUser(user);
    }

    @PostMapping("/login")
public String login(@RequestBody LoginRequest request) {

    System.out.println("USERNAME = " + request.getUsername());
    System.out.println("PASSWORD = " + request.getPassword());

    User existUser = service.findByUsername(request.getUsername());

    if(passwordEncoder.matches(request.getPassword(), existUser.getPassword())) {

        return jwtUtil.generateToken(request.getUsername());
    }

    return "Invalid Username or Password";
}
    
    @PostMapping("/send-otp")
public String sendOtpp(@RequestBody OtpRequest request){

    String email = request.getEmail();

    String otp = otpservice.generateOtp();

    service.saveOtp(email, otp);

    emailService.sendOtpEmail(email, otp);

    return "OTP Sent Successfully";
}

    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestBody OtpRequest request) {
        
    boolean verified =
            service.verifyOtp(
                    request.getEmail(),
                    request.getOtp()
            );

    if (verified) {
        User user = service.findByEmail(request.getEmail());
        return jwtUtil.generateToken(user.getUsername());
    }

    return "Invalid OTP";

        

    }
    
    

}