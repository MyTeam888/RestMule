package org.epsilonlabs.rescli.core.callback;

import org.epsilonlabs.rescli.core.data.AbstractDataSet;
import org.epsilonlabs.rescli.core.data.IDataSet;
import org.epsilonlabs.rescli.core.page.IWrap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 
 * {@link AbstractCallback}
 * <p>
 * Copyright &copy; 2017 University of York.
 * @author Beatriz Sanchez
 * @version 1.0.0
 *
 */
public abstract class AbstractCallback<T,R extends IWrap<T>, D extends AbstractDataSet<T>> implements Callback<R> {

	protected D dataset;			

	// TODO try adding the client to constructor and enqueue the url if error
	protected AbstractCallback(D dataset) {
		this.dataset = dataset;
	}

	public IDataSet<T> getDataset() {
		return dataset;
	}

	@Override
	public void onResponse(Call<R> call, Response<R> response) {
		handleResponse(response);			
	}

	@Override
	public void onFailure(Call<R> call, Throwable t) {
		handleError(call, t);			
	}
	
	public abstract void handleResponse(Response<R> response);
	
	public abstract void handleError(Call<R> call, Throwable t);
	
	public abstract Integer totalIterations(Response<R> response);
	
	public abstract void handleTotal(Response<R> response);
	

}