package com.gracecenote.centralreporting.DAO.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gracecenote.centralreporting.DAO.interfaces.AdminDAO;
import com.gracecenote.centralreporting.entity.AdminDetail;

@Repository("adminDAO")
public class AdminDAOImpl implements AdminDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	final String INSERT_QUERY = "insert into loginlogoutexample.admin_detail(email_id, name, password, role) values (?, ?, ?, ?)";
	final String SELECT_ADMIN_DETAILS_QUERY = "select * from loginlogoutexample.admin_detail";

	/*
	 * Register Admin Details.
	 */
	public int saveAdminDetail(AdminDetail adminDetail) {
		int status = 0;
		try {
			jdbcTemplate.update(INSERT_QUERY, adminDetail.getEmailId(), adminDetail.getName(), adminDetail.password,
					adminDetail.getRole());
			status = 1;
		} catch (Exception exception) {
			System.out.println("Excecption while saving admin Details : " + exception.getMessage());
			return status;
		}
		return status;
	}

	public int adminLogin(String emailId, String password) {

		int status = 0;
		try {
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(SELECT_ADMIN_DETAILS_QUERY);
			List<AdminDetail> adminDetails = new ArrayList<>();

			for (Map<String, Object> row : rows) {
				AdminDetail adminDetail = new AdminDetail();
				adminDetail.setAdminID((int) row.get("admin_id"));
				adminDetail.setEmailId((String) row.get("email_id"));
				adminDetail.setName((String) row.get("name"));
				adminDetail.setPassword((String) row.get("password"));
				adminDetail.setRole((String) row.get("role"));
				adminDetails.add(adminDetail);
			}

			for (AdminDetail adminDetail : adminDetails) {
				if (adminDetail.getEmailId().equals(emailId) && adminDetail.getPassword().equals(password)) {
					status = adminDetail.getAdminID();
					break;
				} else {
					status = -1;
				}
			}
		} catch (Exception exception) {
			System.out.println("Excecption while saving admin Details : " + exception.getMessage());
			status = 0;
		}
		return status;

	}

	public List<AdminDetail> getAdminData() {
		try {
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(SELECT_ADMIN_DETAILS_QUERY);
			List<AdminDetail> adminDetails = new ArrayList<>();

			for (Map<String, Object> row : rows) {
				AdminDetail adminDetail = new AdminDetail();
				adminDetail.setAdminID((int) row.get("admin_id"));
				adminDetail.setEmailId((String) row.get("email_id"));
				adminDetail.setName((String) row.get("name"));
				adminDetail.setPassword((String) row.get("password"));
				adminDetail.setRole((String) row.get("role"));
				adminDetails.add(adminDetail);
			}
			return adminDetails;
		} catch (Exception exception) {
			System.out.println("Excecption while saving admin Details : " + exception.getMessage());
			return null;
		}
	}


}
