package com.gracecenote.centralreporting.entity;

import javax.persistence.Entity;

@Entity
public class NumberOfTokensPerOffice {

	private String attribute_value_name;
	private long tokencount;
	public String getAttribute_value_name() {
		return attribute_value_name;
	}
	public void setAttribute_value_name(String attribute_value_name) {
		this.attribute_value_name = attribute_value_name;
	}
	public long getTokencount() {
		return tokencount;
	}
	public void setTokencount(long tokencount) {
		this.tokencount = tokencount;
	}
	public NumberOfTokensPerOffice() {
		super();
	}
	public NumberOfTokensPerOffice(String attribute_value_name, long tokencount) {
		super();
		this.attribute_value_name = attribute_value_name;
		this.tokencount = tokencount;
	}

}
