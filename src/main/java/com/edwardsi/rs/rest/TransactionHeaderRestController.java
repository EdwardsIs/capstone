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
import com.edwardsi.rs.persistence.entities.Employee;
import com.edwardsi.rs.persistence.entities.TransactionHeader;
import com.edwardsi.rs.persistence.repo.TransactionHeaderService;
import com.google.gson.Gson;

@RestController
public class TransactionHeaderRestController {
	@Autowired
	 private TransactionHeaderService transactionHeaderService;
	  
	 public void setTransactionHeaderService(TransactionHeaderService transactionHeaderService) {
		 this.transactionHeaderService = transactionHeaderService;
	 }
	 
	 @GetMapping("/api/transactionHeaders")
	 public List<TransactionHeader> getTransactionHeaders() {
		 List<TransactionHeader> transactionHeaders = transactionHeaderService.retrieveTransactionHeaders();
		 return transactionHeaders;
	 }
	 
	 @GetMapping("/api/transactionHeaderFor/{employeeId}")
	 public List<TransactionHeader> getTransactionHeaderFor(@PathVariable(name="employeeId")Long employeeId) {
		 List<TransactionHeader> transactionHeaders = transactionHeaderService.getTransactionHeadersFor(employeeId);
		 return transactionHeaders;
	 }
	 
	 @GetMapping("/api/latestTransactionHeader")
	 public TransactionHeader getLatestTransactionHeader() {
		 TransactionHeader transactionHeader = transactionHeaderService.getLatestTransactionHeader();
		 return transactionHeader;
	 }
	  
	 @GetMapping("/api/transactionHeaders/{transactionHeaderId}")
	 public TransactionHeader getTransactionHeader(@PathVariable(name="transactionHeaderId")Long transactionHeaderId) {
		 return transactionHeaderService.getTransactionHeader(transactionHeaderId);
	 }
	  
	 @PostMapping("/api/transactionHeaders")
	 public void saveTransactionHeader(@RequestBody TransactionHeader transactionHeader){
		 System.out.println("Adding transactionHeader: "+transactionHeader.getTax_amount());
		 transactionHeaderService.saveTransactionHeader(transactionHeader);
		 System.out.println("TransactionHeader Saved Successfully");
	 }
	  
	 // Fake save for testing
	 @GetMapping("/api/fakeTransactionHeaders")
	 public void fakeSaveTransactionHeader(){
		 
	 }
	  
	 @DeleteMapping("/api/transactionHeaders/{transactionHeaderId}")
	 public void deleteTransactionHeader(@PathVariable(name="transactionHeaderId")Long transactionHeaderId){
		 transactionHeaderService.deleteTransactionHeader(transactionHeaderId);
		 System.out.println("TransactionHeader Deleted Successfully");
	 }
	  
	 @PutMapping("/api/transactionHeaders/{transactionHeaderId}")
	 public void updateTransactionHeader(@RequestBody TransactionHeader transactionHeader,
		 @PathVariable(name="transactionHeaderId")Long transactionHeaderId){
		 TransactionHeader cust = transactionHeaderService.getTransactionHeader(transactionHeaderId);
		 if(cust != null){
			 transactionHeaderService.updateTransactionHeader(transactionHeader);
		 }
	 }
}
