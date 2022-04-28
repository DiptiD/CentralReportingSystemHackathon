package com.gracecenote.centralreporting.DAO.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gracecenote.centralreporting.DAO.interfaces.TokenDAO;
import com.gracecenote.centralreporting.entity.Token;

@Repository("tokenDAO")
public class TokenDAOImpl implements TokenDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	final String SELECT_TOKEN_DETAILS_QUERY = "select * from loginlogoutexample.token";
	final String INSERT_QUERY = "insert into loginlogoutexample.token (email_id, user_id) values (?, ?)";
	final String UPDATE_TOKEN_QUERY = "Update loginlogoutexample.token set authenticationToken = ? , secretKey = ? where email_id = ?";

	public boolean saveUserEmail(String email, int adminId) {
		boolean status = false;
		try {
			jdbcTemplate.update(INSERT_QUERY, email, adminId);
			status = true;
		} catch (Exception e) {
			System.out.println("Exception in saving UserEmail In Token Table :: " + e.getMessage());
		}
		return status;
	}

	public boolean updateToken(String email, String authenticationToken, String secretKey) {
		try {
			int result = jdbcTemplate.update(UPDATE_TOKEN_QUERY, authenticationToken, secretKey, email);

			if (result == 1) {
				return true;
			} else {
				return false;
			}
		} catch (Exception exception) {
			System.out.println("Error while updating token :: " + exception.getMessage());
			return false;
		}
	}

	public int getTokenDetail(Token t) {
		try {
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(SELECT_TOKEN_DETAILS_QUERY);
			List<Token> tokens = new ArrayList<>();

			for (Map<String, Object> row : rows) {
				Token token = new Token();
				token.setTokenID((int) row.get("token_id"));
				token.setAuthenticationToken((String) row.get("authenticationToken"));
				token.setEmailId((String) row.get("email_id"));
				token.setSecretKey((String) row.get("secretKey"));
				token.setUserID((int) row.get("user_id"));
				tokens.add(token);
			}

			for (Token token : tokens) {
				if (token.getEmailId().equals(t.getEmailId())) {
					return token.getTokenID();
				}
			}
		} catch (Exception exception) {
			System.out.println("Exception while getting token ID :: " + exception.getMessage());
		}

		return 0;

	}

	public int tokenAuthentication(String token, int emailId) {
		try {
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(SELECT_TOKEN_DETAILS_QUERY);
			List<Token> tokenss = new ArrayList<>();

			for (Map<String, Object> row : rows) {
				Token tokens = new Token();
				tokens.setTokenID((int) row.get("token_id"));
				tokens.setAuthenticationToken((String) row.get("authenticationToken"));
				tokens.setEmailId((String) row.get("email_id"));
				tokens.setSecretKey((String) row.get("secretKey"));
				tokens.setUserID((int) row.get("user_id"));
				tokenss.add(tokens);
			}

			for (Token tokens : tokenss) {
				if (token.equals(String.valueOf(tokens.getAuthenticationToken())) && emailId == tokens.getUserID()) {
					return tokens.getTokenID();
				}
			}
		} catch (Exception exception) {
			System.out.println("Exception while Authenticating token :: " + exception);
			return 0;
		} 
		return 0;

	}

}
