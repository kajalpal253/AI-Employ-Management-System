package com.management.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.management.employee.model.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate,Long>{

    
}