package ru.nnmotors.eip.web.ui.component.paging;

import java.util.List;

public abstract class AbstractPagingListData<T> {
	
	private List<T> items;
	
	private List<PagingItem> pages;
	
	private int count;

	public List<PagingItem> getPages() {
		return pages;
	}

	public void setPages(List<PagingItem> pages) {
		this.pages = pages;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

}
