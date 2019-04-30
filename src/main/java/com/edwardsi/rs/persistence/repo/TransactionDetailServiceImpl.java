package com.edwardsi.rs.persistence.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edwardsi.rs.jdbcrepo.TransactionDetailJDBCRepo;
import com.edwardsi.rs.persistence.entities.TransactionDetail;

@Service
public class TransactionDetailServiceImpl implements TransactionDetailService{
//	@Autowired
//	private TransactionDetailRepository transactionDetailRepository;
	
	@Autowired
	private TransactionDetailJDBCRepo transactionDetailRepository;

	public void setTransactionDetailRepository(TransactionDetailJDBCRepo transactionDetailRepository) {
		this.transactionDetailRepository = transactionDetailRepository;
	}

	public List<TransactionDetail> retrieveTransactionDetails() {
		List<TransactionDetail> items = transactionDetailRepository.findAll();
		return items;
	}

	public TransactionDetail getTransactionDetail(Long itemId) {
		TransactionDetail transactionDetail = transactionDetailRepository.findById(itemId);
		return transactionDetail;
	}

	public void saveTransactionDetail(TransactionDetail item) {
		transactionDetailRepository.save(item);
	}

	public void deleteTransactionDetail(Long itemId) {
		transactionDetailRepository.deleteById(itemId);
	}

	public void updateTransactionDetail(TransactionDetail item) {
		transactionDetailRepository.save(item);
	}

	@Override
	public List<TransactionDetail> getDetailsFor(Long transactionHeaderId) {
		List<TransactionDetail> transactionDetails = transactionDetailRepository.getDetailsFor(transactionHeaderId);
		return transactionDetails;
	}
}
