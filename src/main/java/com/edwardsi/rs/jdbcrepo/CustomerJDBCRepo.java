package com.edwardsi.rs.jdbcrepo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.edwardsi.rs.persistence.entities.Customer;

@Repository
public class CustomerJDBCRepo {
    @Autowired
    JdbcTemplate jdbcTemplate;
    class CustomerRowMapper implements RowMapper < Customer > {
        @Override
        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        	Customer customer = new Customer();
        	customer.setId(rs.getLong("id"));
        	customer.setCustomer_time(rs.getTimestamp("customer_time"));
        	customer.setCustomer_name(rs.getString("customer_name"));
        	customer.setAddress(rs.getString("address"));
        	customer.setCity(rs.getString("city"));
        	customer.setState(rs.getString("state"));
        	customer.setZip("zip");
        	customer.setPhone(rs.getString("phone"));
        	customer.setEmail(rs.getString("email"));
            return customer;
        }
    }
    public List < Customer > findAll() {
        return jdbcTemplate.query("select * from CUSTOMER", new CustomerRowMapper());
    }
    public Customer findById(long id) {
        return jdbcTemplate.queryForObject("select * from CUSTOMER where id=?", new Object[] {
                id
            },
            new CustomerRowMapper());
    }
    public int deleteById(long id) {
        return jdbcTemplate.update("delete from CUSTOMER where id=?", new Object[] {
            id
        });
    }
    public int save(Customer customer) {
        return jdbcTemplate.update("insert into CUSTOMER (customer_name, address, city, state, zip, phone, email, customer_time) " + "values(?,?,?,?,?,?,?,CURRENT_TIMESTAMP)",
            new Object[] {
            	customer.getCustomer_name(), customer.getAddress(), customer.getCity(), customer.getState(), customer.getZip(), customer.getPhone(), customer.getEmail()
            });
    }
    public int update(Customer customer) {
        return jdbcTemplate.update("update CUSTOMER " + " set customer_name = ?, address = ?, city = ?, state = ?, zip = ?, phone = ?, email = ? " + " where id = ?",
            new Object[] {
            		customer.getCustomer_name(), customer.getAddress(), customer.getCity(), customer.getState(), customer.getZip(), customer.getPhone(), customer.getEmail(), customer.getId()
            });
    }
}