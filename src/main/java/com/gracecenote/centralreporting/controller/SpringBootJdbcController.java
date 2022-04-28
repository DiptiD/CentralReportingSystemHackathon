package com.gracecenote.centralreporting.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gracecenote.centralreporting.entity.RepresentativeRating;
import com.gracecenote.centralreporting.entity.CustomerRating;
import com.gracecenote.centralreporting.entity.NumberOfTokensPerOffice;
import com.gracecenote.centralreporting.service.interfaces.ReportsService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping(value = "/api")
public class SpringBootJdbcController {

	@Autowired
	ReportsService reportService;

	@GetMapping(path = "/getPieChart")
	public List<NumberOfTokensPerOffice> buildPieChartForTokens() {
		try {
			return reportService.buildPieChartForTokens();
		} catch (Exception e) {
			System.out.println("Excecption while fetching pie chart data : " + e.getMessage());
			return null;
		}
	}

	@GetMapping(path = "/getBarChartForCustomerRating")
	public List<CustomerRating> buildBarChartForCustomerRating() {
		try {
			return reportService.buildBarChartForCustomerRating();
		} catch (Exception e) {
			System.out.println("Excecption while fetching Bar chart data : " + e.getMessage());
			return null;
		}
	}

	@GetMapping(path = "/getTicketCounts")
	public Map<String, BigDecimal> getTicketCounts() {
		try {
			return reportService.getTicketCounts();
		} catch (Exception e) {
			System.out.println("Excecption while fetching Total Ticket counts data : " + e.getMessage());
			return null;
		}
	}

	@GetMapping(path = "/getSupportRepresentative")
	public List<RepresentativeRating> getSupportRepresentative() {
		try {
			return reportService.getSupportRepresentative();
		} catch (Exception e) {
			System.out.println("Excecption while fetching Total Ticket counts data : " + e.getMessage());
			return null;
		}
	}
}
