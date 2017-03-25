package ru.nnmotors.eip.web.ui.pages.user;

import java.util.List;

public class UserListData {
	
	public static class PagingItem {
		
		public PagingItem(int page, String name, boolean active) {
			super();
			this.page = page;
			this.name = name;
			this.active = active;
		}
		
		private int page;
		private String name;
		private boolean disable = false;
		
		public int getPage() {
			return page;
		}
		public void setPage(int page) {
			this.page = page;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public boolean isActive() {
			return active;
		}
		public void setActive(boolean active) {
			this.active = active;
		}
		public boolean isDisable() {
			return disable;
		}
		public void setDisable(boolean disable) {
			this.disable = disable;
		}

		private boolean active;
	}
	
	private List<UserListElementData> users;
	
	private List<PagingItem> pages;

	public List<UserListElementData> getUsers() {
		return users;
	}

	public void setUsers(List<UserListElementData> users) {
		this.users = users;
	}

	public List<PagingItem> getPages() {
		return pages;
	}

	public void setPages(List<PagingItem> pages) {
		this.pages = pages;
	}
	
	

}
