package com.studycafe.utils.config;

import java.util.Date;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

public class JdbcTokenRepositoryImpl extends JdbcDaoSupport implements PersistentTokenRepository {

	@Override
	public void createNewToken(PersistentRememberMeToken token) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateToken(String series, String tokenValue, Date lastUsed) {
		// TODO Auto-generated method stub

	}

	@Override
	public PersistentRememberMeToken getTokenForSeries(String seriesId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeUserTokens(String username) {
		// TODO Auto-generated method stub

	}

}
