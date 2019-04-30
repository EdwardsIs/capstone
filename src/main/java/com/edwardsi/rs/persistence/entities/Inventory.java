package com.edwardsi.rs.persistence.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Inventory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@CreationTimestamp
	@Column(name = "time_created",updatable=false)
	private Timestamp time_created;
	@UpdateTimestamp
	@Column(name = "time_updated")
	private Timestamp time_updated;
	private String item_name;
	private long amount_in_stock;
	private String sku;
	private String description;
	private Double price;
	
	//Getters/Setters
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Timestamp getTime_created() {
		return time_created;
	}
	public void setTime_created(Timestamp time_created) {
		this.time_created = time_created;
	}
	public Timestamp getTime_updated() {
		return time_updated;
	}
	public void setTime_updated(Timestamp time_updated) {
		this.time_updated = time_updated;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public long getAmount_in_stock() {
		return amount_in_stock;
	}
	public void setAmount_in_stock(long amount_in_stock) {
		this.amount_in_stock = amount_in_stock;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (amount_in_stock ^ (amount_in_stock >>> 32));
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((item_name == null) ? 0 : item_name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((sku == null) ? 0 : sku.hashCode());
		result = prime * result + ((time_created == null) ? 0 : time_created.hashCode());
		result = prime * result + ((time_updated == null) ? 0 : time_updated.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		boolean isEqual = false;
		Inventory other = (Inventory) obj;
		if(other.id == this.id) {
			isEqual = true;
		}
		return isEqual;
	}
	
	
}
