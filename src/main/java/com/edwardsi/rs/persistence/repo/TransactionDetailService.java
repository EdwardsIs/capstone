package com.edwardsi.rs.persistence.repo;

import java.util.List;

import com.edwardsi.rs.persistence.entities.TransactionDetail;

public interface TransactionDetailService {
	public List<TransactionDetail> retrieveTransactionDetails();

	public TransactionDetail getTransactionDetail(Long customerId);

	public void saveTransactionDetail(TransactionDetail customer);

	public void deleteTransactionDetail(Long customerId);

	public void updateTransactionDetail(TransactionDetail customer);

	public List<TransactionDetail> getDetailsFor(Long transactionHeaderId);
}
