package ru.nnmotors.eip.business.api.model.entity;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UserProfile {
	
    public UserProfile() {
		super();
	}

	public UserProfile(Long id) {
		super();
		this.id = id;
	}
	
	public static UserProfile getReference(Long id) {
		return new UserProfile(id);
	}

	@Id
    @GeneratedValue
    private Long id;
	
    @Column(unique = true, nullable = false)
    private String login;
    
    @ManyToOne(targetEntity = Attachment.class)
    @JoinColumn
    private Attachment avatar;
    
	@Column
    private Date createTime;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String middleName;

    @Column
    private String email;

    @Column
    private boolean admin = false;

    @Column
    private boolean active = true;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateTime() {
    	// FindBug: EI_EXPOSE_REP2
    	// java.util.Date is mutable object
		return new Date(createTime.getTime());
	}

	public void setCreateTime(Date createTime) {
    	// FindBug: EI_EXPOSE_REP2
    	// java.util.Date is mutable object
		this.createTime =  new Date(createTime.getTime());
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
    public Attachment getAvatar() {
		return avatar;
	}

	public void setAvatar(Attachment avatar) {
		this.avatar = avatar;
	}


}
