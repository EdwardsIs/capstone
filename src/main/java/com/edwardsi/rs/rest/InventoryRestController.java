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
import com.edwardsi.rs.persistence.repo.InventoryService;

@RestController
public class InventoryRestController {
	@Autowired
	 private InventoryService inventoryService;
	  
	 public void setInventoryService(InventoryService inventoryService) {
		 this.inventoryService = inventoryService;
	 }
	 
	 @GetMapping("/api/inventory")
	 public List<Inventory> getInventorys() {
		 List<Inventory> inventorys = inventoryService.retrieveInventorys();
		 return inventorys;
	 }
	  
	 @GetMapping("/api/inventory/{inventoryId}")
	 public Inventory getInventory(@PathVariable(name="inventoryId")Long inventoryId) {
		 return inventoryService.getInventory(inventoryId);
	 }
	  
	 @PostMapping("/api/inventory")
	 public void saveInventory(Inventory inventory){
		 System.out.println("Adding inventory: "+inventory.getId()+" "+inventory.getItem_name());
		 inventoryService.saveInventory(inventory);
		 System.out.println("Inventory Saved Successfully");
	 }
	  
	 @DeleteMapping("/api/inventory/{inventoryId}")
	 public void deleteInventory(@PathVariable(name="inventoryId")Long inventoryId){
		 inventoryService.deleteInventory(inventoryId);
		 System.out.println("Inventory Deleted Successfully");
	 }
	  
	 @PutMapping("/api/inventory/{inventoryId}")
	 public void updateInventory(@RequestBody Inventory inventory,
		 @PathVariable(name="inventoryId")Long inventoryId){
		 Inventory cust = inventoryService.getInventory(inventoryId);
		 if(cust != null){
			 inventoryService.updateInventory(inventory);
		 }
	 }
}
