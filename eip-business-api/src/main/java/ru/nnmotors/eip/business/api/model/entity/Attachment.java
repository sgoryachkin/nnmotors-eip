package ru.nnmotors.eip.business.api.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Attachment {
	
	@Id
    @GeneratedValue
    private Long id;
	
	private String storageFileName;
	
	private String storageCategoryName;
	
	private String originalFileName;
	
	private String contentType;
	
    public Attachment() {
		super();
	}

	public Attachment(Long id) {
		super();
		this.id = id;
	}
	
	public static Attachment getReference(Long id) {
		return new Attachment(id);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public String getStorageCategoryName() {
		return storageCategoryName;
	}

	public void setStorageCategoryName(String storageCategoryName) {
		this.storageCategoryName = storageCategoryName;
	}

	public String getStorageFileName() {
		return storageFileName;
	}

	public void setStorageFileName(String storageFileName) {
		this.storageFileName = storageFileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

}
