package ru.nnmotors.eip.business.api.model.param;

import java.io.Serializable;

public class ListParam<F, O> implements Serializable {
	private static final long serialVersionUID = -4885029395197339313L;

	private int firstResult;

	private int maxResults;

	private F filter;
	
	private O order;

	public F getFilter() {
		return filter;
	}

	public void setFilter(F filter) {
		this.filter = filter;
	}

	public ListParam() {
		super();
	}
	
	public ListParam(int firstResult, int maxResults, F filter, O order) {
		super();
		this.firstResult = firstResult;
		this.maxResults = maxResults;
		this.filter = filter;
		this.order = order;
	}
	
	public ListParam(int firstResult, int maxResults, F filter) {
		super();
		this.firstResult = firstResult;
		this.maxResults = maxResults;
		this.filter = filter;
	}

	public O getOrder() {
		return order;
	}

	public void setOrder(O order) {
		this.order = order;
	}

	public int getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}

	public int getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}
}
