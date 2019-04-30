package com.edwardsi.rs.persistence.repo;

import java.util.List;

import com.edwardsi.rs.persistence.entities.Employee;

public interface EmployeeService {
public List<Employee> retrieveEmployees();
	
	public Employee getEmployee(Long customerId);
	
	public void saveEmployee(Employee customer);
	  
	public void deleteEmployee(Long customerId);
	  
	public void updateEmployee(Employee customer);
}
