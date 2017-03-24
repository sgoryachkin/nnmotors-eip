package ru.nnmotors.eip.business.api.model.param;

import java.io.Serializable;

public class ListParam<F, O> implements Serializable {
	private static final long serialVersionUID = -4885029395197339313L;

	private int page;

	private int pageSize;

	private F filter;
	
	private O order;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public F getFilter() {
		return filter;
	}

	public void setFilter(F filter) {
		this.filter = filter;
	}

	public ListParam() {
	}

	public ListParam(int page, int pageSize, F filter) {
		super();
		this.page = page;
		this.pageSize = pageSize;
		this.filter = filter;
	}
	
	public ListParam(int page, int pageSize, F filter, O order) {
		super();
		this.page = page;
		this.pageSize = pageSize;
		this.filter = filter;
		this.order = order;
	}

	public O getOrder() {
		return order;
	}

	public void setOrder(O order) {
		this.order = order;
	}
}
