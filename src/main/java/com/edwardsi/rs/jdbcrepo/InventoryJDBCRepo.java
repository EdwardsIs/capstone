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
import com.edwardsi.rs.persistence.entities.Inventory;

@Repository
public class InventoryJDBCRepo {
    @Autowired
    JdbcTemplate jdbcTemplate;
    class InventoryRowMapper implements RowMapper < Inventory > {
        @Override
        public Inventory mapRow(ResultSet rs, int rowNum) throws SQLException {
        	Inventory inventory = new Inventory();
        	inventory.setId(rs.getLong("id"));
        	inventory.setTime_created(rs.getTimestamp("time_created"));
        	inventory.setTime_updated(rs.getTimestamp("time_updated"));
        	inventory.setItem_name(rs.getString("item_name"));
        	inventory.setAmount_in_stock(rs.getLong("amount_in_stock"));
        	inventory.setSku(rs.getString("sku"));
        	inventory.setDescription(rs.getString("description"));
        	inventory.setPrice(rs.getDouble("price"));
            return inventory;
        }
    }
    public List < Inventory > findAll() {
        return jdbcTemplate.query("select * from INVENTORY", new InventoryRowMapper());
    }
    public Inventory findById(long id) {
        return jdbcTemplate.queryForObject("select * from INVENTORY where id=?", new Object[] {
                id
            },
            new InventoryRowMapper());
    }
    public int deleteById(long id) {
        return jdbcTemplate.update("delete from INVENTORY where id=?", new Object[] {
            id
        });
    }
    public int save(Inventory inventory) {
        return jdbcTemplate.update("insert into INVENTORY (item_name, amount_in_stock, sku, description, price, time_created, time_updated) " + "values(?,?,?,?,?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)",
            new Object[] {
            	inventory.getItem_name(), inventory.getAmount_in_stock(), inventory.getSku(), inventory.getDescription(), inventory.getPrice()
            });
    }
    public int update(Inventory inventory) {
        return jdbcTemplate.update("update INVENTORY " + " item_name = ?, amount_in_stock = ?, sku = ?, description = ?, price = ?, time_updated = CURRENT_TIMESTAMP" + " where id = ?",
            new Object[] {
            		inventory.getItem_name(), inventory.getAmount_in_stock(), inventory.getSku(), inventory.getDescription(), inventory.getPrice()
            });
    }
}