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

import com.edwardsi.rs.persistence.entities.Customer;
import com.edwardsi.rs.persistence.repo.CustomerService;

@RestController
public class CustomerRestController {
	 @Autowired
	 private CustomerService customerService;
	  
	 public void setCustomerService(CustomerService customerService) {
		 this.customerService = customerService;
	 }
	 
	 @GetMapping("/api/customers")
	 public List<Customer> getCustomers() {
		 List<Customer> customers = customerService.retrieveCustomers();
		 return customers;
	 }
	  
	 @GetMapping("/api/customers/{customerId}")
	 public Customer getCustomer(@PathVariable(name="customerId")Long customerId) {
		 return customerService.getCustomer(customerId);
	 }
	  
	 @PostMapping("/api/customers")
	 public void saveCustomer(Customer customer){
		 System.out.println("Adding customer: "+customer.getId()+" "+customer.getCustomer_name());
		 customerService.saveCustomer(customer);
		 System.out.println("Customer Saved Successfully");
	 }
	  
	 @DeleteMapping("/api/customers/{customerId}")
	 public void deleteCustomer(@PathVariable(name="customerId")Long customerId){
		 customerService.deleteCustomer(customerId);
		 System.out.println("Customer Deleted Successfully");
	 }
	  
	 @PutMapping("/api/customers/{customerId}")
	 public void updateCustomer(@RequestBody Customer customer,
		 @PathVariable(name="customerId")Long customerId){
		 Customer cust = customerService.getCustomer(customerId);
		 if(cust != null){
			 customerService.updateCustomer(customer);
		 }
	 }
}
