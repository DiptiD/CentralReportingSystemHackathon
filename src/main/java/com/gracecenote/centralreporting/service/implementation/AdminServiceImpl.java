package com.gracecenote.centralreporting.service.implementation;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gracecenote.centralreporting.DAO.interfaces.AdminDAO;
import com.gracecenote.centralreporting.entity.AdminDetail;
import com.gracecenote.centralreporting.service.interfaces.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired(required = true)
	private AdminDAO adminDAO;

	@Transactional
	public int saveAdminDetail(AdminDetail adminDetail) {
		return adminDAO.saveAdminDetail(adminDetail);
	}

	@Transactional
	public int adminLogin(String emailId, String password) {
		return adminDAO.adminLogin(emailId, password);
	}

	@Transactional
	public List<AdminDetail> getAdminData() {
		return adminDAO.getAdminData();
	}

}
