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
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
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
       
        return service.registerUser1(user);
    }

    @PostMapping("/login")
public String login(@RequestBody LoginRequest request) {

    
    User existUser = service.findByUsername(request.getUsername());
    System.out.println("Verified = " + existUser.isVarified());
    if(!existUser.isVarified()){
        return "Please verify your email first";
    }

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
        return "OTP Verified Successfully";
    }

    return "Invalid OTP";

        

    }
    
    @DeleteMapping("/delete/{id}")
public String deleteUser(@PathVariable Long id) {

    service.deleteUser(id);

    return "User Deleted Successfully";
}

}