package ru.nnmotors.eip.business.api.model.param;

import java.io.Serializable;

public class ListParam<T> implements Serializable {
	private static final long serialVersionUID = -4885029395197339313L;

	private int page;

	private int pageSize;

	private T filter;

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

	public T getFilter() {
		return filter;
	}

	public void setFilter(T filter) {
		this.filter = filter;
	}

	public ListParam() {
	}

	public ListParam(int page, int pageSize, T filter) {
		super();
		this.page = page;
		this.pageSize = pageSize;
		this.filter = filter;
	}
}
