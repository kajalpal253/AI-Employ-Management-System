package com.management.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.employee.model.Employee;
import com.management.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {
 
    @Autowired
    private EmployeeRepository repository;
    
    //get  all employee
    public List<Employee> getAllEmployees(){
        return repository.findAll();
    }

    //Add employee
    public Employee addEmployee(Employee employee){
        return repository.save(employee);
    }

    public Employee getEmployeeById(Long id){

        return repository.findById(id).orElseThrow(()-> new RuntimeException("Employee Not Found"));
    }

    public Employee updateEmployee(Long id,Employee employeeDetails){
        Employee employee = getEmployeeById(id);
        employee.setName(employeeDetails.getName());
        employee.setEmail(employeeDetails.getEmail());
        employee.setDepartment(employeeDetails.getDepartment());
        employee.setSalary(employeeDetails.getSalary());
        
        return  repository.save(employee);
    }
    

    public String deleteEmployee(Long id){
        Employee employee =getEmployeeById(id);
        repository.delete(employee);
      return "Employee Deleted Successfully";
    }
}
