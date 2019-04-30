package com.edwardsi.rs.persistence.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edwardsi.rs.jdbcrepo.CustomerJDBCRepo;
import com.edwardsi.rs.persistence.entities.Customer;


@Service
public class CustomerServiceImpl implements CustomerService{
//	@Autowired
//	 private CustomerRepository customerRepository;
	@Autowired
	 private CustomerJDBCRepo customerRepository;
	 
	 public void setCustomerRepository(CustomerJDBCRepo customerRepository) {
	  this.customerRepository = customerRepository;
	 }
	  
	 public List<Customer> retrieveCustomers() {
	  List<Customer> items = customerRepository.findAll();
	  return items;
	 }
	  
	 public Customer getCustomer(Long itemId) {
		 Customer customer = customerRepository.findById(itemId);
		 return customer;
	 }
	  
	 public void saveCustomer(Customer item){
		 customerRepository.save(item);
	 }
	  
	 public void deleteCustomer(Long itemId){
		 customerRepository.deleteById(itemId);
	 }
	  
	 public void updateCustomer(Customer item) {
		 customerRepository.update(item);
	 }
}
