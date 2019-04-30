package com.edwardsi.rs.persistence.repo;

import java.util.List;

import com.edwardsi.rs.persistence.entities.Customer;

public interface CustomerService {
	public List<Customer> retrieveCustomers();
	
	public Customer getCustomer(Long customerId);
	
	public void saveCustomer(Customer customer);
	  
	public void deleteCustomer(Long customerId);
	  
	public void updateCustomer(Customer customer);

}
