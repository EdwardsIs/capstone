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

import com.edwardsi.rs.persistence.entities.Vendor;
import com.edwardsi.rs.persistence.repo.VendorService;

@RestController
public class VendorRestController {
	@Autowired
	 private VendorService vendorService;
	  
	 public void setVendorService(VendorService vendorService) {
		 this.vendorService = vendorService;
	 }
	 
	 @GetMapping("/api/vendors")
	 public List<Vendor> getVendors() {
		 List<Vendor> vendors = vendorService.retrieveVendors();
		 return vendors;
	 }
	  
	 @GetMapping("/api/vendors/{vendorId}")
	 public Vendor getVendor(@PathVariable(name="vendorId")Long vendorId) {
		 return vendorService.getVendor(vendorId);
	 }
	  
	 @PostMapping("/api/vendors")
	 public void saveVendor(@RequestBody Vendor vendor){
		 System.out.println("Adding vendor: "+vendor.getId()+" "+vendor.getVendor_name());
		 vendorService.saveVendor(vendor);
		 System.out.println("Vendor Saved Successfully");
	 }
	  
	 @DeleteMapping("/api/vendors/{vendorId}")
	 public void deleteVendor(@PathVariable(name="vendorId")Long vendorId){
		 vendorService.deleteVendor(vendorId);
		 System.out.println("Vendor Deleted Successfully");
	 }
	  
	 @PutMapping("/api/vendors/{vendorId}")
	 public void updateVendor(@RequestBody Vendor vendor,
		 @PathVariable(name="vendorId")Long vendorId){
		 Vendor cust = vendorService.getVendor(vendorId);
		 if(cust != null){
			 vendorService.updateVendor(vendor);
		 }
	 }
}
