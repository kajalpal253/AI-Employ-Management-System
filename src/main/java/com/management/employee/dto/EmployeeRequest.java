package com.management.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequest {
    
    private String name;
    private String email;
    private String department;
    private double salary;
    private String gender;
    private String phone;
}
