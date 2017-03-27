package ru.nnmotors.eip.web.ui.component.paging;

public class PagingElementData {
	
	public PagingElementData(int page, String name, boolean active) {
		super();
		this.page = page;
		this.name = name;
		this.active = active;
	}
	
	private int page;
	private String name;
	private boolean disable;
	
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