package com.edwardsi.rs.jdbcrepo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.edwardsi.rs.persistence.entities.TransactionDetail;
import com.edwardsi.rs.persistence.entities.TransactionHeader;
import com.edwardsi.rs.persistence.repo.InventoryService;
import com.edwardsi.rs.persistence.repo.TransactionHeaderService;
@Repository
public class TransactionDetailJDBCRepo {
	@Autowired
	TransactionHeaderService headerService;
	
	@Autowired
	InventoryService inventoryService;
	
    @Autowired
    JdbcTemplate jdbcTemplate;
    class TransactionDetailRowMapper implements RowMapper < TransactionDetail > {
        @Override
        public TransactionDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
        	TransactionDetail transactionDetail = new TransactionDetail();
        	transactionDetail.setId(rs.getLong("id"));
        	transactionDetail.setTransactionHeader(headerService.getTransactionHeader(rs.getLong("transaction_header_id")));
        	transactionDetail.setInventory(inventoryService.getInventory(rs.getLong("inventory_id")));
        	transactionDetail.setPrice(rs.getDouble("price"));
        	transactionDetail.setQuantity(rs.getLong("quantity"));
        	transactionDetail.setTime_created(rs.getTimestamp("time_created"));
            return transactionDetail;
        }
    }
    public List < TransactionDetail > findAll() {
        return jdbcTemplate.query("select * from TRANSACTION_DETAIL", new TransactionDetailRowMapper());
    }
    public TransactionDetail findById(long id) {
        return jdbcTemplate.queryForObject("select * from TRANSACTION_DETAIL where id=?", new Object[] {
                id
            },
            new TransactionDetailRowMapper());
    }
    public int deleteById(long id) {
        return jdbcTemplate.update("delete from TRANSACTION_DETAIL where id=?", new Object[] {
            id
        });
    }
    public int save(TransactionDetail transactionDetail) {
        return jdbcTemplate.update("insert into TRANSACTION_DETAIL (price, quantity, inventory_id, transaction_header_id, time_created) " + "values(?,?,?,?,CURRENT_TIMESTAMP)",
            new Object[] {
            	transactionDetail.getPrice(), transactionDetail.getQuantity(), transactionDetail.getInventory().getId(), transactionDetail.getTransactionHeader().getId()
            });
    }
    public int update(TransactionDetail transactionDetail) {
        return jdbcTemplate.update("update TRANSACTION_DETAIL " + " set price = ?, quantity = ?, inventory_id = ?, transaction_header_id = ? " + " where id = ?",
            new Object[] {
            		transactionDetail.getPrice(), transactionDetail.getQuantity(), transactionDetail.getInventory().getId(), transactionDetail.getTransactionHeader().getId()
            });
    }
	public List<TransactionDetail> getDetailsFor(Long transactionHeaderId) {
		return jdbcTemplate.query("select * from TRANSACTION_DETAIL WHERE TRANSACTION_HEADER_ID=?", new Object[] {
				transactionHeaderId
		}, new TransactionDetailRowMapper());
	}
}