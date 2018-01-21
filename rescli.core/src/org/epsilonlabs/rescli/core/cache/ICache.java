package org.epsilonlabs.rescli.core.cache;

import java.io.IOException;

import org.epsilonlabs.rescli.core.session.ISession;

import okhttp3.Request;
import okhttp3.Response;

/**
 * 
 * {@link ICache}
 * <p>
 * Copyright &copy; 2017 University of York.
 * @author Beatriz Sanchez
 * @version 1.0.0
 *
 */
public interface ICache {

	boolean exists(Request request, ISession session);
	boolean exists(Response response, ISession session);
	
	Response load(Response response, ISession session);
	Response load(Request request, ISession session);
	
	Response put(Response response, ISession session) throws IOException;
	
	void clear();
	
	
}