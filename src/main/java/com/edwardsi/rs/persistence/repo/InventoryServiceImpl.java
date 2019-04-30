package com.edwardsi.rs.persistence.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edwardsi.rs.persistence.entities.Inventory;

@Service
public class InventoryServiceImpl implements InventoryService{
	@Autowired
	private InventoryRepository inventoryRepository;

	public void setInventoryRepository(InventoryRepository inventoryRepository) {
		this.inventoryRepository = inventoryRepository;
	}

	public List<Inventory> retrieveInventorys() {
		List<Inventory> items = inventoryRepository.findAll();
		return items;
	}

	public Inventory getInventory(Long itemId) {
		Optional<Inventory> optInventory = inventoryRepository.findById(itemId);
		return optInventory.get();
	}

	public void saveInventory(Inventory item) {
		inventoryRepository.save(item);
	}

	public void deleteInventory(Long itemId) {
		inventoryRepository.deleteById(itemId);
	}

	public void updateInventory(Inventory item) {
		inventoryRepository.save(item);
	}
}
