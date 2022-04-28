package com.gracecenote.centralreporting.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class CustomerRating {

	private String CustomerName;
	private BigDecimal Rating;

	public String getCustomerName() {
		return CustomerName;
	}

	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}

	public BigDecimal getRating() {
		return Rating;
	}

	public void setRating(BigDecimal rating) {
		Rating = rating;
	}

	public CustomerRating() {
		super();
	}

	public CustomerRating(String customerName, BigDecimal rating) {
		super();
		CustomerName = customerName;
		Rating = rating;
	}

}
