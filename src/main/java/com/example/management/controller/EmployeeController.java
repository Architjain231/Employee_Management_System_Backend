package com.example.management.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.management.model.Employee;
import com.example.management.service.EmployeeService;

@CrossOrigin(value="http://localhost:3000")
@RequestMapping("/api")
@RestController
public class EmployeeController {
	
    EmployeeService employeeService;
    EmployeeController(EmployeeService employeeService)
    {
    	this.employeeService=employeeService;
    }
    
    @PostMapping("/employee")
    public Employee addEmployee(@RequestBody Employee employee)
    {
    	System.out.print("Reaching");
       Employee emp= employeeService.createEmployee(employee);
       return emp;
    }
    
    @GetMapping("/employee")
    public List<Employee> getAllEmployees()
    {
    	System.out.print("Fetching ALl");
       List<Employee> emps= employeeService.getAllEmployees();
       System.out.println(emps);

       return emps;
    }
    
    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable long id)
    {
    	boolean deleted=false;
        System.out.println("DELETED");
       deleted= employeeService.deleteEmployee(id);
       Map<String,Boolean> response=new HashMap<>();
       response.put("Deleted", deleted);
       return ResponseEntity.ok(response);
    }
    
    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody Employee employee)
    {
    	return employeeService.updateEmployee(id,employee);
          
    }
    
    @GetMapping("/employee/{id}")
    public Employee getEmployeeById(@PathVariable Long id)
    {
          return employeeService.getEmployeeById(id);	
    }
}
