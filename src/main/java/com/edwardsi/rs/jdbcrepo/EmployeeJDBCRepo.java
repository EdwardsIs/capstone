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
import com.edwardsi.rs.persistence.entities.Employee;

@Repository
public class EmployeeJDBCRepo {
    @Autowired
    JdbcTemplate jdbcTemplate;
    class EmployeeRowMapper implements RowMapper < Employee > {
        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        	Employee employee = new Employee();
        	employee.setId(rs.getLong("id"));
        	employee.setEmployee_name(rs.getString("employee_name"));
        	employee.setAddress(rs.getString("address"));
        	employee.setCity(rs.getString("city"));
        	employee.setState(rs.getString("state"));
        	employee.setZip("zip");
        	employee.setPhone(rs.getString("phone"));
        	employee.setEmail(rs.getString("email"));
            return employee;
        }
    }
    public List < Employee > findAll() {
        return jdbcTemplate.query("select * from EMPLOYEE", new EmployeeRowMapper());
    }
    public Employee findById(long id) {
        return jdbcTemplate.queryForObject("select * from EMPLOYEE where id=?", new Object[] {
                id
            },
            new EmployeeRowMapper());
    }
    public int deleteById(long id) {
        return jdbcTemplate.update("delete from EMPLOYEE where id=?", new Object[] {
            id
        });
    }
    public int save(Employee employee) {
        return jdbcTemplate.update("insert into EMPLOYEE (employee_name, address, city, state, zip, phone, email) " + "values(?,?,?,?,?,?,?)",
            new Object[] {
            	employee.getEmployee_name(), employee.getAddress(), employee.getCity(), employee.getState(), employee.getZip(), employee.getPhone(), employee.getEmail()
            });
    }
    public int update(Employee employee) {
    	System.out.println("We are updating");
        return jdbcTemplate.update("update EMPLOYEE " + " set employee_name = ?, address = ?, city = ?, state = ?, zip = ?, phone = ?, email = ? " + " where id = ?",
            new Object[] {
            		employee.getEmployee_name(), employee.getAddress(), employee.getCity(), employee.getState(), employee.getZip(), employee.getPhone(), employee.getEmail(), employee.getId()
            });
    }
}