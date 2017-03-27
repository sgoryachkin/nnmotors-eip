package ru.nnmotors.eip.web.ui.pages.user;

public class SimpleTextFilter {
	
	public SimpleTextFilter() {
		super();
	}

	public SimpleTextFilter(String text) {
		super();
		this.text = text;
	}

	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
