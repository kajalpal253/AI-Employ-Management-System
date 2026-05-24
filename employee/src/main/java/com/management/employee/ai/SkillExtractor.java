package com.management.employee.ai;

import java.util.ArrayList;
import java.util.List;

public class SkillExtractor {

    public List<String>extractSkill(String resumeText){

        resumeText= resumeText.toLowerCase();
        List<String>skills= new ArrayList<>();

        if(resumeText.contains("java")){
            skills.add("JAVA");
        }
        if(resumeText.contains("spring boot")){
            skills.add("SPRING BOOT");
        }
        if(resumeText.contains("sql")){
            skills.add("SQL");
        }
        if(resumeText.contains("jwt")){
            skills.add("JWT");
        }
        if(resumeText.contains("react")){
            skills.add("React");
        }
        if(resumeText.contains("javascript")){
            skills.add("JavaScipt");
        }

        if(resumeText.contains("python")){
            skills.add("Python");
        }
        return skills;
        
    }
    
}
