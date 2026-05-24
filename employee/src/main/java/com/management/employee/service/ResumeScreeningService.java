package com.management.employee.service;

import org.springframework.stereotype.Service;

@Service
public class ResumeScreeningService{

    public String recommendRole(String skills){
        skills = skills.toLowerCase();

        if(skills.contains("java") && skills.contains(skills)){
            return "Backend Developer";
        }
        if(skills.contains("react") || skills.contains("javascript")){
            return "Frontend Developer";
        }
        return "General Developer";
    }

    public double calculateScore(String skills){
        int score =0;

        skills = skills.toLowerCase();

        if(skills.contains("java")){
            score +=25;
        }
        if(skills.contains("spring")){
            score +=25;
        }
        if(skills.contains("sql")){
            score +=25;
        }
        if(skills.contains("jwt")){
            score +=25;
        }
        return score;

    }

}