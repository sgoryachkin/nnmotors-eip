package ru.nnmotors.eip.business.api.model.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Attachment {
	
	@Id
    @GeneratedValue
    private Long id;
	
	private String starageId;
	
	private String relativeUrl;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRelativeUrl() {
		return relativeUrl;
	}

	public void setRelativeUrl(String relativeUrl) {
		this.relativeUrl = relativeUrl;
	}

	public String getStarageId() {
		return starageId;
	}

	public void setStarageId(String starageId) {
		this.starageId = starageId;
	}

}
