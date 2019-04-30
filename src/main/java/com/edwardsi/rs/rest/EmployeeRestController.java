package com.edwardsi.rs.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.edwardsi.rs.persistence.entities.Employee;
import com.edwardsi.rs.persistence.repo.EmployeeService;

@RestController
public class EmployeeRestController {
	
	@Autowired
	 private EmployeeService employeeService;
	  
	 public void setEmployeeService(EmployeeService employeeService) {
		 this.employeeService = employeeService;
	 }
	 
	 @GetMapping("/api/employees")
	 public List<Employee> getEmployees() {
		 List<Employee> employees = employeeService.retrieveEmployees();
		 return employees;
	 }
	  
	 @GetMapping("/api/employees/{employeeId}")
	 public Employee getEmployee(@PathVariable(name="employeeId")Long employeeId) {
		 return employeeService.getEmployee(employeeId);
	 }
	  
	 @PostMapping("/api/employees")
	 public void saveEmployee(Employee employee){
		 System.out.println("Adding employee: "+employee.getId()+" "+employee.getEmployee_name());
		 employeeService.saveEmployee(employee);
		 System.out.println("Employee Saved Successfully");
	 }
	  
	 @DeleteMapping("/api/employees/{employeeId}")
	 public void deleteEmployee(@PathVariable(name="employeeId")Long employeeId){
		 employeeService.deleteEmployee(employeeId);
		 System.out.println("Employee Deleted Successfully");
	 }
	  
	 @PutMapping("/api/employees/{employeeId}")
	 public void updateEmployee(@RequestBody Employee employee,
		 @PathVariable(name="employeeId")Long employeeId){
		 Employee cust = employeeService.getEmployee(employeeId);
		 if(cust != null){
			 System.out.println("We are updating controller level");
			 employeeService.updateEmployee(employee);
		 }
	 }

}
