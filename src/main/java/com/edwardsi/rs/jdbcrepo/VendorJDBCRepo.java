package com.edwardsi.rs.jdbcrepo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.edwardsi.rs.persistence.entities.Vendor;

@Repository
public class VendorJDBCRepo {
    @Autowired
    JdbcTemplate jdbcTemplate;
    class VendorRowMapper implements RowMapper < Vendor > {
        @Override
        public Vendor mapRow(ResultSet rs, int rowNum) throws SQLException {
        	Vendor vendor = new Vendor();
        	vendor.setId(rs.getLong("id"));
        	vendor.setContact_person(rs.getString("contact_person"));
        	vendor.setVendor_name(rs.getString("vendor_name"));
        	vendor.setAddress(rs.getString("address"));
        	vendor.setCity(rs.getString("city"));
        	vendor.setState(rs.getString("state"));
        	vendor.setZip("zip");
        	vendor.setPhone(rs.getString("phone"));
        	vendor.setEmail(rs.getString("email"));
            return vendor;
        }
    }
    public List < Vendor > findAll() {
        return jdbcTemplate.query("select * from VENDOR", new VendorRowMapper());
    }
    public Vendor findById(long id) {
        return jdbcTemplate.queryForObject("select * from VENDOR where id=?", new Object[] {
                id
            },
            new VendorRowMapper());
    }
    public int deleteById(long id) {
        return jdbcTemplate.update("delete from VENDOR where id=?", new Object[] {
            id
        });
    }
    public int save(Vendor vendor) {
        return jdbcTemplate.update("insert into VENDOR (vendor_name, contact_person, address, city, state, zip, phone, email) " + "values(?,?,?,?,?,?,?,?)",
            new Object[] {
            		vendor.getVendor_name(), vendor.getContact_person(), vendor.getAddress(), vendor.getCity(), vendor.getState(), vendor.getZip(), vendor.getPhone(), vendor.getEmail()
            });
    }
    public int update(Vendor vendor) {
        return jdbcTemplate.update("update CUSTOMER " + " set vendor_name = ?, contact_person = ?, address = ?, city = ?, state = ?, zip = ?, phone = ?, email = ? " + " where id = ?",
            new Object[] {
            		vendor.getVendor_name(), vendor.getContact_person(), vendor.getAddress(), vendor.getCity(), vendor.getState(), vendor.getZip(), vendor.getPhone(), vendor.getEmail(), vendor.getId()
            });
    }
}