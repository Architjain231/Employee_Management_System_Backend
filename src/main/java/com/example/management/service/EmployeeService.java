package com.example.management.service;

import java.util.List;

import com.example.management.model.Employee;
import org.springframework.http.ResponseEntity;


public interface EmployeeService {
	public Employee createEmployee(Employee employee);

	public List<Employee> getAllEmployees();

	public Boolean deleteEmployee(long id);

	public ResponseEntity<Employee> updateEmployee(Long id, Employee employee);

	public Employee getEmployeeById(Long id);

}
