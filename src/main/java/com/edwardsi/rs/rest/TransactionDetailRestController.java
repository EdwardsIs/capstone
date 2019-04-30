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

import com.edwardsi.rs.persistence.entities.Inventory;
import com.edwardsi.rs.persistence.entities.TransactionDetail;
import com.edwardsi.rs.persistence.entities.TransactionHeader;
import com.edwardsi.rs.persistence.repo.TransactionDetailService;

@RestController
public class TransactionDetailRestController {
	@Autowired
	 private TransactionDetailService transactionDetailService;
	  
	 public void setTransactionDetailService(TransactionDetailService transactionDetailService) {
		 this.transactionDetailService = transactionDetailService;
	 }
	 
	 @GetMapping("/api/transactionDetails")
	 public List<TransactionDetail> getTransactionDetails() {
		 List<TransactionDetail> transactionDetails = transactionDetailService.retrieveTransactionDetails();
		 return transactionDetails;
	 }
	  
	 @GetMapping("/api/transactionDetails/{transactionDetailId}")
	 public TransactionDetail getTransactionDetail(@PathVariable(name="transactionDetailId")Long transactionDetailId) {
		 return transactionDetailService.getTransactionDetail(transactionDetailId);
	 }
	 
	 @GetMapping("/api/transactionDetailsFor/{transactionHeaderId}")
	 public List<TransactionDetail> getDetailsFor(@PathVariable(name="transactionHeaderId")Long transactionHeaderId) {
		 System.out.println("Header ID: " + transactionHeaderId);
		 List<TransactionDetail> transactionDetails = transactionDetailService.getDetailsFor(transactionHeaderId);
		 return transactionDetails;
	 }
	  
	 @PostMapping("/api/transactionDetails")
	 public void saveTransactionDetail(@RequestBody TransactionDetail transactionDetail){
		 System.out.println("Adding transactionDetail: "+transactionDetail.getId());
		 transactionDetailService.saveTransactionDetail(transactionDetail);
		 System.out.println("TransactionDetail Saved Successfully");
	 }
	 
	 // Fake save for testing
	 @GetMapping("/api/fakeTransactionDetails")
	 public void fakeSaveTransactionDetail(){
		 TransactionDetail transactionDetail = new TransactionDetail();
		 TransactionHeader transactionHeader = new TransactionHeader();
		 transactionDetail.setTransactionHeader(transactionHeader);
		 transactionDetail.setInventory(new Inventory());
		 transactionDetail.setPrice(10.00);
		 transactionDetail.setQuantity(2);
		 System.out.println("Adding transactionDetail: "+transactionDetail.getId());
		 transactionDetailService.saveTransactionDetail(transactionDetail);
		 TransactionDetail transactionDetail2 = new TransactionDetail();
		 transactionDetail2.setInventory(new Inventory());
		 transactionDetail2.setPrice(17.00);
		 transactionDetail2.setQuantity(5);
		 transactionDetail2.setTransactionHeader(transactionHeader);
		 System.out.println("Adding transactionDetail: "+transactionDetail.getId());
		 transactionDetailService.saveTransactionDetail(transactionDetail2);
		 System.out.println("TransactionDetail Saved Successfully");
	 }
	  
	 @DeleteMapping("/api/transactionDetails/{transactionDetailId}")
	 public void deleteTransactionDetail(@PathVariable(name="transactionDetailId")Long transactionDetailId){
		 transactionDetailService.deleteTransactionDetail(transactionDetailId);
		 System.out.println("TransactionDetail Deleted Successfully");
	 }
	  
	 @PutMapping("/api/transactionDetails/{transactionDetailId}")
	 public void updateTransactionDetail(@RequestBody TransactionDetail transactionDetail,
		 @PathVariable(name="transactionDetailId")Long transactionDetailId){
		 TransactionDetail cust = transactionDetailService.getTransactionDetail(transactionDetailId);
		 if(cust != null){
			 transactionDetailService.updateTransactionDetail(transactionDetail);
		 }
	 }
}
