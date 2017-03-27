package ru.nnmotors.eip.business.api.model.entity.assotiation;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import ru.nnmotors.eip.business.api.model.entity.Department;
import ru.nnmotors.eip.business.api.model.entity.UserProfile;

@Entity
public class DepartmentAssociation {
	
	@EmbeddedId
	private DepartmentAssociationId id;
	
    
    @MapsId("userId")
    @ManyToOne
	private UserProfile user;
	
    
    @MapsId("departmentId")
    @ManyToOne
	private Department department;
	
    @Column
	private boolean director;

	public boolean isDirector() {
		return director;
	}

	public void setDirector(boolean director) {
		this.director = director;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public UserProfile getUser() {
		return user;
	}

	public void setUser(UserProfile user) {
		this.user = user;
	}

}
