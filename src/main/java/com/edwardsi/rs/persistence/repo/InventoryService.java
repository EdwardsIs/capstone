package com.edwardsi.rs.persistence.repo;

import java.util.List;

import com.edwardsi.rs.persistence.entities.Inventory;

public interface InventoryService {
	public List<Inventory> retrieveInventorys();

	public Inventory getInventory(Long customerId);

	public void saveInventory(Inventory customer);

	public void deleteInventory(Long customerId);

	public void updateInventory(Inventory customer);
}
