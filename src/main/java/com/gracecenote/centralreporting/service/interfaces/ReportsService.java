package com.gracecenote.centralreporting.service.interfaces;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.gracecenote.centralreporting.entity.RepresentativeRating;
import com.gracecenote.centralreporting.entity.CustomerRating;
import com.gracecenote.centralreporting.entity.NumberOfTokensPerOffice;

public interface ReportsService {
	
	public List<NumberOfTokensPerOffice> buildPieChartForTokens();

	public List<CustomerRating> buildBarChartForCustomerRating();

	public Map<String, BigDecimal> getTicketCounts();

	public List<RepresentativeRating> getSupportRepresentative();
}
