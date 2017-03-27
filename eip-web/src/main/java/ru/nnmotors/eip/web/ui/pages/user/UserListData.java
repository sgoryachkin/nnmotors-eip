package ru.nnmotors.eip.web.ui.pages.user;

import java.util.List;

public class UserListData {
	
	private List<UserListElementData> items;
	
	private int count;

	public List<UserListElementData> getItems() {
		return items;
	}

	public void setItems(List<UserListElementData> items) {
		this.items = items;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
}
