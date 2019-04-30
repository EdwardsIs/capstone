package com.edwardsi.rs.persistence.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edwardsi.rs.jdbcrepo.TransactionHeaderJDBCRepo;
import com.edwardsi.rs.persistence.entities.TransactionHeader;

@Service
public class TransactionHeaderServiceImpl implements TransactionHeaderService{
//	@Autowired
//	private TransactionHeaderRepository transactionHeaderRepository;
	@Autowired
	private TransactionHeaderJDBCRepo transactionHeaderRepository;

	public void setTransactionHeaderRepository(TransactionHeaderJDBCRepo transactionHeaderRepository) {
		this.transactionHeaderRepository = transactionHeaderRepository;
	}

	public List<TransactionHeader> retrieveTransactionHeaders() {
		List<TransactionHeader> items = transactionHeaderRepository.findAll();
		return items;
	}
	
	public List<TransactionHeader> getTransactionHeadersFor(Long employeeId) {
		List<TransactionHeader> headers = transactionHeaderRepository.getHeaderFor(employeeId);
		return headers;
	}

	public TransactionHeader getTransactionHeader(Long itemId) {
		TransactionHeader transactionHeader = transactionHeaderRepository.findById(itemId);
		return transactionHeader;
	}

	public TransactionHeader getLatestTransactionHeader() {
		TransactionHeader transactionHeader = transactionHeaderRepository.getMostRecent();
		return transactionHeader;
	}

	public void saveTransactionHeader(TransactionHeader item) {
		transactionHeaderRepository.save(item);
	}

	public void deleteTransactionHeader(Long itemId) {
		transactionHeaderRepository.deleteById(itemId);
	}

	public void updateTransactionHeader(TransactionHeader item) {
		transactionHeaderRepository.update(item);
	}
}
