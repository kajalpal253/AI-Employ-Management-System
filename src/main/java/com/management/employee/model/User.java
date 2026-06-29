package com.management.employee.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="usertable")

@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
     @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;
    private String password;
    private  String role;
    private boolean varified;
    private String otp;
    private LocalDateTime otpExpire;
}
