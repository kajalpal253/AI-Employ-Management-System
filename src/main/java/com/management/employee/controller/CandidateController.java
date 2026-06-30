package com.management.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.employee.dto.CandidateRequest;
import com.management.employee.model.Candidate;
import com.management.employee.service.CandidateService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins= "http://localhost:3000")
@SecurityRequirement(name= "bearerAuth")
@RestController
@RequestMapping("/candidates")
public class CandidateController {

    @Autowired
    private CandidateService service;

    @PostMapping("/upload")
    public Candidate savCandidate(@RequestBody CandidateRequest request) {
        Candidate candidate= new Candidate();
        candidate.setName(request.getName());
        candidate.setEmail(request.getEmail());
        candidate.setSkills(request.getSkills());
        
        candidate.setGender(request.getGender());
        candidate.setPhone(request.getPhone());
        
        return service.saveCandidate(candidate);
    }

    @GetMapping
    public List<Candidate> getAllllCandidates(){
        return service.getAllCandidate();
    }
    
   @DeleteMapping("/{id}")
     public String deleteCandidate(@PathVariable("id") Long id){
         service.deleteCandidate(id);
         return "Candidate deleted successfully";
     }
    
}
