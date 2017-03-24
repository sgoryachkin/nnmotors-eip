package ru.nnmotors.eip.business.api.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Department implements HasId {
	
	@Id
    @GeneratedValue
    private Long id;
	
	@Column
	private String name;
	
    @ManyToOne(targetEntity = UserProfile.class)
    @JoinColumn
	private UserProfile director;

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

	public UserProfile getDirector() {
		return director;
	}

	public void setDirector(UserProfile director) {
		this.director = director;
	}

}
