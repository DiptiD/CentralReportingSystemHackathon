package com.gracecenote.centralreporting.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class RepresentativeRating {

	private String RepresentativeName;
	private BigDecimal AverageResolutionTime;

	public String getRepresentativeName() {
		return RepresentativeName;
	}

	public void setRepresentativeName(String representativeName) {
		RepresentativeName = representativeName;
	}

	public BigDecimal getAverageResolutionTime() {
		return AverageResolutionTime;
	}

	public void setAverageResolutionTime(BigDecimal averageResolutionTime) {
		AverageResolutionTime = averageResolutionTime;
	}

	public RepresentativeRating() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RepresentativeRating(String representativeName, BigDecimal averageResolutionTime) {
		super();
		RepresentativeName = representativeName;
		AverageResolutionTime = averageResolutionTime;
	}
	
}
