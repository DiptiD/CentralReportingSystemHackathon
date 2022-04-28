package com.gracecenote.centralreporting.service.interfaces;

import com.gracecenote.centralreporting.entity.Token;

public interface TokenService {
	
	public void saveUserEmail(String email , int adminId);
	
	public boolean updateToken(String email , String authenticationToken , String secretKey);
	
	public int getTokenDetail(Token token);

	public int tokenAuthentication(String token , int emailId);

}
