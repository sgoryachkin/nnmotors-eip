package ru.nnmotors.eip.web.ui.pages;

import java.io.Serializable;

public class HeaderData implements Serializable {

	private static final long serialVersionUID = -3625491993828773900L;

	
	private String currentUserId;
	
	private String currentUserName;

	public String getCurrentUserId() {
		return currentUserId;
	}

	public void setCurrentUserId(String currentUserId) {
		this.currentUserId = currentUserId;
	}

	public String getCurrentUserName() {
		return currentUserName;
	}

	public void setCurrentUserName(String currentUserName) {
		this.currentUserName = currentUserName;
	}


}
