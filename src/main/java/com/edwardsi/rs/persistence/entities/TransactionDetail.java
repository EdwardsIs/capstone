package com.edwardsi.rs.persistence.entities;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;


@Entity
public class TransactionDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@CreationTimestamp
	@Column(name = "time_created",updatable=false)
	private Timestamp time_created;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade= {CascadeType.ALL})
	private TransactionHeader transactionHeader;
	
	@OneToOne(fetch = FetchType.EAGER, cascade= {CascadeType.ALL})
	@JoinColumn(name = "inventory_id")
	private Inventory inventory;
	
	private long quantity;
	
	private double price;

	//Getters and Setters
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

	public TransactionHeader getTransactionHeader() {
		return transactionHeader;
	}

	public void setTransactionHeader(TransactionHeader transactionHeader) {
		this.transactionHeader = transactionHeader;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((inventory == null) ? 0 : inventory.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (int) (quantity ^ (quantity >>> 32));
		result = prime * result + ((time_created == null) ? 0 : time_created.hashCode());
		result = prime * result + ((transactionHeader == null) ? 0 : transactionHeader.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean isEqual = false;
		TransactionDetail other = (TransactionDetail) obj;
		if(other.id == this.id) {
			isEqual = true;
		}
		return isEqual;
	}
	
	
	
}