package com.edwardsi.rs.persistence.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edwardsi.rs.jdbcrepo.EmployeeJDBCRepo;
import com.edwardsi.rs.persistence.entities.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
//	@Autowired
//	private EmployeeRepository employeeRepository;
	@Autowired
	private EmployeeJDBCRepo employeeRepository;

	public void setEmployeeRepository(EmployeeJDBCRepo employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public List<Employee> retrieveEmployees() {
		List<Employee> items = employeeRepository.findAll();
		return items;
	}

	public Employee getEmployee(Long itemId) {
		Employee employee = employeeRepository.findById(itemId);
		return employee;
	}

	public void saveEmployee(Employee item) {
		employeeRepository.save(item);
	}

	public void deleteEmployee(Long itemId) {
		employeeRepository.deleteById(itemId);
	}

	public void updateEmployee(Employee item) {
		employeeRepository.update(item);
	}

}
