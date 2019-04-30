package com.edwardsi.rs.persistence.repo;

import java.util.List;

import com.edwardsi.rs.persistence.entities.TransactionHeader;

public interface TransactionHeaderService {
	public List<TransactionHeader> retrieveTransactionHeaders();

	public TransactionHeader getTransactionHeader(Long customerId);

	public TransactionHeader getLatestTransactionHeader();

	public void saveTransactionHeader(TransactionHeader customer);

	public void deleteTransactionHeader(Long customerId);

	public void updateTransactionHeader(TransactionHeader customer);

	public List<TransactionHeader> getTransactionHeadersFor(Long employeeId);
}
