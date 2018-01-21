package org.epsilonlabs.rescli.core.session;

import static com.google.common.net.HttpHeaders.AUTHORIZATION;
import static org.apache.http.client.config.AuthSchemes.BASIC;

import okhttp3.Headers;

/**
 * 
 * {@link ISession}
 * <p>
 * Copyright &copy; 2017 University of York.
 * @author Beatriz Sanchez
 * @version 1.0.0
 *
 */
public interface ISession extends IRateLimiter {
	
	void setRateLimit(String rateLimit);
	void setRateLimitRemaining(String rateLimitRemaining);
	void setRateLimitReset(String rateLimitReset);

	boolean isHeader();
	Headers getHeaders();
	
	Auth type();
	String hash();
	String id();
	
	public enum Auth {
		NO_AUTH	(""), 
		BASIC_AUTH (BASIC), 
		OAUTH ("Bearer");
		
		private String header;
		Auth(String header){
			this.header = header;
		}
		
		Headers header(String token){
			switch (this){
			case BASIC_AUTH:
			case OAUTH:
				return new Headers.Builder().add(AUTHORIZATION, header + " " + token).build();
			case NO_AUTH:
			default:
				return new Headers.Builder().build();
			}
		}
	}
}