package ru.nnmotors.eip.web.ui.pages.portal.model;

import java.io.Serializable;

public class HeaderData implements Serializable {

	private static final long serialVersionUID = -3625491993828773900L;

	
	private String currentUserId;

	public String getCurrentUserId() {
		return currentUserId;
	}

	public void setCurrentUserId(String currentUserId) {
		this.currentUserId = currentUserId;
	}

}
