package com.gracecenote.centralreporting.service.implementation;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gracecenote.centralreporting.DAO.interfaces.TokenDAO;
import com.gracecenote.centralreporting.entity.Token;
import com.gracecenote.centralreporting.service.interfaces.TokenService;

@Service("tokenService")
public class TokenServiceImpl implements TokenService {

	@Autowired
	private TokenDAO tokenDAO;

	@Transactional
	public void saveUserEmail(String email, int adminId) {
		tokenDAO.saveUserEmail(email, adminId);
	}

	@Transactional
	public boolean updateToken(String email, String authenticationToken, String secretKey) {
		return tokenDAO.updateToken(email, authenticationToken, secretKey);
	}

	@Transactional
	public int getTokenDetail(Token token) {
		return tokenDAO.getTokenDetail(token);
	}

	@Transactional
	public int tokenAuthentication(String token, int emailId) {
		return tokenDAO.tokenAuthentication(token, emailId);
	}

}
