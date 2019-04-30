package com.edwardsi.rs.persistence.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edwardsi.rs.persistence.entities.Vendor;

@Service
public class VendorServiceImpl implements VendorService{
	@Autowired
	private VendorRepository vendorRepository;

	public void setVendorRepository(VendorRepository vendorRepository) {
		this.vendorRepository = vendorRepository;
	}

	public List<Vendor> retrieveVendors() {
		List<Vendor> items = vendorRepository.findAll();
		return items;
	}

	public Vendor getVendor(Long itemId) {
		Optional<Vendor> optVendor = vendorRepository.findById(itemId);
		return optVendor.get();
	}

	public void saveVendor(Vendor item) {
		vendorRepository.save(item);
	}

	public void deleteVendor(Long itemId) {
		vendorRepository.deleteById(itemId);
	}

	public void updateVendor(Vendor item) {
		vendorRepository.save(item);
	}
}
