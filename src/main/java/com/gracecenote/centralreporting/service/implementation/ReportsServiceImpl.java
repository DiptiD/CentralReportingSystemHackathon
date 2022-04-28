package com.gracecenote.centralreporting.service.implementation;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gracecenote.centralreporting.DAO.interfaces.ReportsDAO;
import com.gracecenote.centralreporting.entity.RepresentativeRating;
import com.gracecenote.centralreporting.entity.CustomerRating;
import com.gracecenote.centralreporting.entity.NumberOfTokensPerOffice;
import com.gracecenote.centralreporting.service.interfaces.ReportsService;

@Service
public class ReportsServiceImpl implements ReportsService{
	
	@Autowired(required=true)
	private ReportsDAO reportDAO;
	
	@Transactional
	public List<NumberOfTokensPerOffice> buildPieChartForTokens() {
		return reportDAO.buildPieChartForTokens();
	}

	@Override
	public List<CustomerRating> buildBarChartForCustomerRating() {
		return reportDAO.buildBarChartForCustomerRating();
	}

	@Override
	public Map<String, BigDecimal> getTicketCounts() {
		return reportDAO.getTicketCounts();
	}

	@Override
	public List<RepresentativeRating> getSupportRepresentative() {
		return reportDAO.getSupportRepresentative();
	}

}
