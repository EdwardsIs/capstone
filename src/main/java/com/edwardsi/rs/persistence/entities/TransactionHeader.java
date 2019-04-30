package com.edwardsi.rs.persistence.entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;


@Entity
public class TransactionHeader {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToMany(mappedBy="transactionHeader", cascade= {CascadeType.ALL})
	private final List<TransactionDetail> transactionDetails = new ArrayList<TransactionDetail>();
	
	@CreationTimestamp
	@Column(name = "time_created",updatable=false)
	private Timestamp time_created;
	
//	@@OneToOne(fetch=FetchType.EAGER, cascade= {CascadeType.ALL})
	@ManyToOne(optional=false)
	@JoinColumn(name="customer_id", referencedColumnName="id", insertable=false, updatable=false)
	private Customer customer;

	
//	@OneToOne(fetch=FetchType.EAGER, cascade= {CascadeType.ALL})
	@ManyToOne(optional=false)
	@JoinColumn(name="employee_id", referencedColumnName="id", insertable=false, updatable=false)
	private Employee employee;
	
	private double subtotal_amount;
	
	private double tax_amount;

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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public double getSubtotal_amount() {
		return subtotal_amount;
	}

	public void setSubtotal_amount(double subtotal_amount) {
		this.subtotal_amount = subtotal_amount;
	}

	public double getTax_amount() {
		return tax_amount;
	}

	public void setTax_amount(double tax_amount) {
		this.tax_amount = tax_amount;
	}

	public List<TransactionDetail> getTransactionDetails() {
		return transactionDetails;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		long temp;
		temp = Double.doubleToLongBits(subtotal_amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(tax_amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((time_created == null) ? 0 : time_created.hashCode());
		result = prime * result + ((transactionDetails == null) ? 0 : transactionDetails.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean isEqual = false;
		TransactionHeader other = (TransactionHeader) obj;
		if(other.id == this.id) {
			isEqual = true;
		}
		return isEqual;
	}

	
	
}
