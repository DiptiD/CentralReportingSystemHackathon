package com.gracecenote.centralreporting.DAO.interfaces;

import com.gracecenote.centralreporting.entity.Token;

public interface TokenDAO {
	
	public boolean saveUserEmail(String email , int adminId);
	
	public boolean updateToken(String email , String authenticationToken , String secretKey);
	
	public int getTokenDetail(Token token);

	public int tokenAuthentication(String token , int emailId);

}
