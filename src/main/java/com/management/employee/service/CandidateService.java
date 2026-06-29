package com.management.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.employee.model.Candidate;
import com.management.employee.repository.CandidateRepository;

@Service
public class CandidateService{

    @Autowired
    private CandidateRepository repository;

    @Autowired
    private ResumeScreeningService aiservice;

    public Candidate saveCandidate(Candidate candidate){

        String role = aiservice.recommendRole(candidate.getSkills());
        double score =aiservice.calculateScore(candidate.getSkills());

        candidate.setRecommendedrole(role);
        candidate.setScore(score);
        return repository.save(candidate);
    }

    public List<Candidate>getAllCandidate(){
        return repository.findAll();
    }
}
