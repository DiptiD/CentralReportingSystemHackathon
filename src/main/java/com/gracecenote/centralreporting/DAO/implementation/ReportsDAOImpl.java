package com.gracecenote.centralreporting.DAO.implementation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gracecenote.centralreporting.DAO.interfaces.ReportsDAO;
import com.gracecenote.centralreporting.entity.RepresentativeRating;
import com.gracecenote.centralreporting.entity.CustomerRating;
import com.gracecenote.centralreporting.entity.NumberOfTokensPerOffice;

@Repository("reportsDAO")
public class ReportsDAOImpl implements ReportsDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	final String INSERT_QUERY = "insert into loginlogoutexample.admin_detail(email_id, name, password, role) values (?, ?, ?, ?)";
	final String SELECT_ADMIN_DETAILS_QUERY = "select * from loginlogoutexample.admin_detail";

	@Override
	public List<NumberOfTokensPerOffice> buildPieChartForTokens() {
		String sql = "select loginlogoutexample.attribute_value.attribute_value_name,Tokencount\n" + "from \n"
				+ "(SELECT count(attribute_value_id) AS Tokencount, loginlogoutexample.provider_optimizer_config.attribute_value_id AS attribute_value_id \n"
				+ "FROM loginlogoutexample.provider_optimizer_config \n"
				+ "WHERE loginlogoutexample.provider_optimizer_config.attribute_type_id = 30 \n"
				+ "GROUP BY loginlogoutexample.provider_optimizer_config.attribute_value_id \n"
				+ "ORDER BY loginlogoutexample.provider_optimizer_config.attribute_value_id asc) as foo \n"
				+ "left join  loginlogoutexample.attribute_value\n"
				+ "on foo.attribute_value_id = loginlogoutexample.attribute_value.attribute_value_id;";

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		List<NumberOfTokensPerOffice> numberOfTokensPerOfficeList = new ArrayList<>();

		long nullTokenVal = 0;
		for (Map<String, Object> row : rows) {
			NumberOfTokensPerOffice numberOfTokensPerOffice = new NumberOfTokensPerOffice();
			numberOfTokensPerOffice
					.setAttribute_value_name(!StringUtils.isBlank((String) row.get("attribute_value_name"))
							? (String) row.get("attribute_value_name")
							: "Other");
			numberOfTokensPerOffice.setTokencount(
					!StringUtils.isBlank((String) row.get("attribute_value_name")) ? (long) row.get("Tokencount")
							: (nullTokenVal + ((long) row.get("Tokencount"))));
			numberOfTokensPerOfficeList.add(numberOfTokensPerOffice);
		}
		return numberOfTokensPerOfficeList;
	}

	@Override
	public List<CustomerRating> buildBarChartForCustomerRating() {
		String sql = "SELECT  \n" + 
				"	CustomerSupportAnalysis.CustomerName\n" + 
				"	,Round(Sum((CustomerSupportAnalysis.FeedbackRating)/NULLIF(( CASE WHEN CustomerSupportAnalysis.Status='Closed' THEN 1 ELSE 0 END ),0))/Count((CustomerSupportAnalysis.FeedbackRating)/NULLIF(( CASE WHEN CustomerSupportAnalysis.Status='Closed' THEN 1 ELSE 0 END ),0)),2) as Rating\n" + 
				"FROM \n" + 
				"	loginlogoutexample.CustomerSupportAnalysis Group by CustomerSupportAnalysis.CustomerName;";

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		List<CustomerRating> customeRatings = new ArrayList<>();

		for (Map<String, Object> row : rows) {
			CustomerRating customerRating = new CustomerRating();
			customerRating.setCustomerName((String) row.get("CustomerName"));
			Object count = row.get("Rating");
			customerRating.setRating(count != null ?
					(BigDecimal) row.get("Rating") : BigDecimal.ZERO);
			customeRatings.add(customerRating);
		}
		return customeRatings;
	}

	@Override
	public Map<String, BigDecimal> getTicketCounts() {
		String sql = "SELECT \n"
				+ " Sum(CASE WHEN CustomerSupportAnalysis.Status = 'Open' THEN 1 ELSE 0 END) as OpenTickets,\n"
				+ " Sum(CASE WHEN CustomerSupportAnalysis.Status = 'Closed' THEN 1 ELSE 0 END) as ClosedTickets,\n"
				+ " (Sum(CASE WHEN CustomerSupportAnalysis.Status = 'Open' THEN 1 ELSE 0 END)+Sum(CASE WHEN CustomerSupportAnalysis.Status = 'Closed' THEN 1 ELSE 0 END)) as TotalTickets\n"
				+ "FROM loginlogoutexample.CustomerSupportAnalysis;";

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		Map<String, BigDecimal> ticketCounts = new HashMap<>();

		for (Map<String, Object> row : rows) {
			Map<String, BigDecimal> finalMap = new HashMap<>();
			
			finalMap.put("OpenTickets", (BigDecimal) row.get("OpenTickets"));
			finalMap.put("ClosedTickets", (BigDecimal) row.get("ClosedTickets"));
			finalMap.put("TotalTickets", (BigDecimal) row.get("TotalTickets"));
			ticketCounts.putAll(finalMap);
		}
		return ticketCounts;
	}

	@Override
	public List<RepresentativeRating> getSupportRepresentative() {
		String sql = "SELECT CustomerSupportAnalysis.RepresentativeName \n"
				+ "	,Round(Sum((( CustomerSupportAnalysis.DurationTime )/NULLIF(( CASE WHEN  CustomerSupportAnalysis.Status ='Closed' THEN 1 ELSE 0 END ),0))*1.0)/Count(( CustomerSupportAnalysis.DurationTime )/NULLIF(( CASE WHEN  CustomerSupportAnalysis.Status ='Closed' THEN 1 ELSE 0 END ),0)),1) AS  AverageResolutionTime \n"
				+ "FROM loginlogoutexample.CustomerSupportAnalysis  Group by  CustomerSupportAnalysis.RepresentativeName ;";

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		List<RepresentativeRating> representativeRatings = new ArrayList<>();

		for (Map<String, Object> row : rows) {
			RepresentativeRating representativeRating = new RepresentativeRating();
			representativeRating.setRepresentativeName((String) row.get("RepresentativeName"));
			Object count = row.get("AverageResolutionTime");
			representativeRating.setAverageResolutionTime(count != null ?
					(BigDecimal) row.get("AverageResolutionTime") : BigDecimal.ZERO);
			representativeRatings.add(representativeRating);
		}
		return representativeRatings;
	}

}
