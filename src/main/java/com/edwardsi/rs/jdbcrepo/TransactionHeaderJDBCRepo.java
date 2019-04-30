package com.edwardsi.rs.jdbcrepo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.edwardsi.rs.persistence.entities.TransactionHeader;
import com.edwardsi.rs.persistence.repo.CustomerService;
import com.edwardsi.rs.persistence.repo.EmployeeService;
@Repository
public class TransactionHeaderJDBCRepo {
	@Autowired
	CustomerService customerService;
	
	@Autowired
	EmployeeService employeeService;
	
    @Autowired
    JdbcTemplate jdbcTemplate;
    class TransactionHeaderRowMapper implements RowMapper < TransactionHeader > {
        @Override
        public TransactionHeader mapRow(ResultSet rs, int rowNum) throws SQLException {
        	TransactionHeader transactionHeader = new TransactionHeader();
        	transactionHeader.setId(rs.getLong("id"));
        	System.out.println("Mapping customer id "+rs.getLong("customer_id"));
        	transactionHeader.setCustomer(customerService.getCustomer(rs.getLong("customer_id")));
        	transactionHeader.setEmployee(employeeService.getEmployee(rs.getLong("employee_id")));
        	transactionHeader.setSubtotal_amount(rs.getDouble("subtotal_amount"));
        	transactionHeader.setTax_amount(rs.getDouble("tax_amount"));
        	transactionHeader.setTime_created(rs.getTimestamp("time_created"));
            return transactionHeader;
        }
    }
    public List < TransactionHeader > findAll() {
        return jdbcTemplate.query("select * from TRANSACTION_HEADER", new TransactionHeaderRowMapper());
    }
    public TransactionHeader getMostRecent() {
        return jdbcTemplate.queryForObject("select top 1 * from TRANSACTION_HEADER ORDER BY TIME_CREATED DESC", new Object[] {
                
            },
            new TransactionHeaderRowMapper());
    }
    public TransactionHeader findById(long id) {
    	System.out.println("Looking for header with ID "+id);
        return jdbcTemplate.queryForObject("select * from TRANSACTION_HEADER where id=?", new Object[] {
                id
            },
            new TransactionHeaderRowMapper());
    }
    public int deleteById(long id) {
        return jdbcTemplate.update("delete from TRANSACTION_HEADER where id=?", new Object[] {
            id
        });
    }
    public int save(TransactionHeader transactionHeader) {
        return jdbcTemplate.update("insert into TRANSACTION_HEADER (subtotal_amount, tax_amount, customer_id, employee_id, time_created) " + "values(?,?,?,?,CURRENT_TIMESTAMP)",
            new Object[] {
            	transactionHeader.getSubtotal_amount(), transactionHeader.getTax_amount(),transactionHeader.getCustomer().getId(),transactionHeader.getEmployee().getId()
            });
    }
    public int update(TransactionHeader transactionHeader) {
        return jdbcTemplate.update("update TRANSACTION_HEADER " + " set subtotal_amount = ?, tax_amount = ?, customer_id = ?, employee_id = ? " + " where id = ?",
            new Object[] {
            	transactionHeader.getSubtotal_amount(), transactionHeader.getTax_amount(),transactionHeader.getCustomer().getId(),transactionHeader.getEmployee().getId(), transactionHeader.getId()
            });
    }
	public List<TransactionHeader> getHeaderFor(Long employeeId) {
		return jdbcTemplate.query("select * from TRANSACTION_HEADER WHERE employee_id=?", new Object[] {
			employeeId	
		},
		new TransactionHeaderRowMapper());
	}
}