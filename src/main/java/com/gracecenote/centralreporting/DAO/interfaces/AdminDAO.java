package com.gracecenote.centralreporting.DAO.interfaces;

import java.util.List;

import com.gracecenote.centralreporting.entity.AdminDetail;

public interface AdminDAO {

	public int saveAdminDetail(AdminDetail adminDetail);
	
	public int adminLogin(String emailId , String password);
	
	public List<AdminDetail> getAdminData();
}
