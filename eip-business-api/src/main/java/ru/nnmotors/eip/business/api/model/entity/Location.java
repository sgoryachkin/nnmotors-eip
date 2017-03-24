package ru.nnmotors.eip.business.api.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Location implements HasId {
	
	@Id
    @GeneratedValue
    private Long id;
	
	@Column
	private String name;
	
	@Column
	private String fullAddress;
	
    public Location() {
		super();
	}

	public Location(Long id) {
		super();
		this.id = id;
	}
	
	public static Location getReference(Long id) {
		return new Location(id);
	}
	
	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
