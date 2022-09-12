package com.example.management.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.management.entity.EmployeeEntity;
import com.example.management.model.Employee;
import com.example.management.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {


	private EmployeeRepository repo;
	
	
	public EmployeeServiceImpl(EmployeeRepository repo) {
		this.repo = repo;
	}
	
	@Override
	public Employee createEmployee(Employee employee) {
      
		EmployeeEntity empEntity=new EmployeeEntity();
		BeanUtils.copyProperties(employee, empEntity);
		repo.save(empEntity);
		return employee;
	}
	public List<Employee> getAllEmployees() {
		List<EmployeeEntity> empEntity=repo.findAll();
		List<Employee> employees=empEntity.stream().map(entity->{
       		return new Employee(entity.getId(),entity.getFirstName(),entity.getLastName(),entity.getEmailId());
		}).collect(Collectors.toList());
		return employees;
	}
	
	public Boolean deleteEmployee(long id) {
	   EmployeeEntity employee=repo.findById(id).get();
		 repo.delete(employee);
	    return true;
	}
	
	public ResponseEntity<Employee> updateEmployee(Long id,Employee employee)
	{
	     EmployeeEntity employeeEntity=repo.findById(id).get();
	     BeanUtils.copyProperties(employee, employeeEntity);
	     repo.save(employeeEntity);
	     return ResponseEntity.ok(employee);
	}

	@Override
	public Employee getEmployeeById(Long id) {
		EmployeeEntity employeeEntity=repo.findById(id).get();
		Employee employee=new Employee();
		BeanUtils.copyProperties(employeeEntity,employee);
		return employee;
	}
}
