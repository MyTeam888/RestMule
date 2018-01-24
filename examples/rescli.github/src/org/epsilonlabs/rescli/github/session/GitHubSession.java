package org.epsilonlabs.rescli.github.session;

import static org.epsilonlabs.rescli.core.util.PropertiesUtil.OAUTH2_AUTH_URL;
import static org.epsilonlabs.rescli.core.util.PropertiesUtil.OAUTH2_TOKEN_URL;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Nonnull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.epsilonlabs.rescli.core.session.AbstractSession;
import org.epsilonlabs.rescli.core.session.ISession;
import org.epsilonlabs.rescli.github.util.GitHubPropertiesUtil;

@SuppressWarnings("unused")
public class GitHubSession extends AbstractSession {

	private static final Logger LOG = LogManager.getLogger(GitHubSession.class);

	public static final String CLIENT = "github";
	private static final String AUTHORIZATION_URL = GitHubPropertiesUtil.get(OAUTH2_AUTH_URL);
	private static final String ACCESS_TOKEN_URL = GitHubPropertiesUtil.get(OAUTH2_TOKEN_URL);

	/** STATIC SESSION MANAGEMENT */
	
	private static HashMap<String, ISession> sessions;
	
	private static HashMap<String, ISession> getSessions(){
		if (sessions == null)
			sessions = new HashMap<String, ISession>();
		return sessions;
	}

	private static void addSession(GitHubSession session){
		GitHubSession.getSessions().put(session.id() , session);
	}
	
	public static ISession get(String id) {
		return GitHubSession.getSessions().get(id);
	}
	
	/** PREDEFINED SESSION TYPES */
	
	public static ISession createPublic(){
		GitHubSession session = new GitHubSession();
		GitHubSession.addSession(session);
		return session;
	}

	public static ISession createWithBasicAuth(String username, String password){
		GitHubSession session = new GitHubSession(username, password);
		GitHubSession.addSession(session);
		return session;
	}

	public static ISession createWithBasicAuth(String token){
		GitHubSession session = new GitHubSession(token);
		GitHubSession.addSession(session);
		return session;
	}

	public static ISession createWithOAuth(
			String clientId, 
			String clientSecret, 
			List<String> scopes, 
			String username) {
		GitHubSession session = new GitHubSession(clientId, clientSecret, scopes, username);
		GitHubSession.addSession(session);
		return session;
	}

	/** TYPE CONSTRUCTORS */
	
	private GitHubSession(){
		super(); 
	}

	private GitHubSession(String username, String password){
		super(username, password);
	}

	private GitHubSession(String token){
		super(token);
	}

	private GitHubSession(String clientId, 
			String clientSecret, 
			List<String> scopes,
			String username	) {
		super();
		setOAuthToken(ACCESS_TOKEN_URL, AUTHORIZATION_URL, clientId, clientSecret, scopes, username);
	}
	
	/** METHODS FROM ISESSION */

	@Override
	public void setRateLimit(@Nonnull String rateLimit){
		this.rateLimit = Integer.valueOf(rateLimit);
	}

	@Override
	public void setRateLimitRemaining(@Nonnull String rateLimitRemaining){
		this.rateLimitRemaining = new AtomicInteger(Integer.valueOf(rateLimitRemaining));
	}

	@Override
	public void setRateLimitReset(@Nonnull String rateLimitReset){
		this.rateLimitReset = new Date(Long.valueOf(rateLimitReset) * 1000);
	}
	
	/** METHODS FROM ABSTRACT */
	
	@Override
	protected void setOAuthToken(String clientId, String clientSecret, List<String> scopes, String username) {
		super.setOAuthToken(ACCESS_TOKEN_URL, AUTHORIZATION_URL, clientId, clientSecret, scopes, username);		
	}
	
	@Override 
	public String toString() {
		return CLIENT + super.toString();
	}

}