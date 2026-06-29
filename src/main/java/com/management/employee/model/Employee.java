package com.management.employee.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employees")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee{
 
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String name;
   private String department;
   private String Gender;
   private String Phone;
   private Double salary;
   private String email;

}