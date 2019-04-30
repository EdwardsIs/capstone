package com.edwardsi.rs.persistence.repo;

import java.util.List;

import com.edwardsi.rs.persistence.entities.Vendor;

public interface VendorService {
	public List<Vendor> retrieveVendors();

	public Vendor getVendor(Long customerId);

	public void saveVendor(Vendor customer);

	public void deleteVendor(Long customerId);

	public void updateVendor(Vendor customer);
}
