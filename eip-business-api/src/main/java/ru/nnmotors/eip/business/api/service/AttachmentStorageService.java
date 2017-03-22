package ru.nnmotors.eip.business.api.service;

import java.io.InputStream;

import ru.nnmotors.eip.business.api.model.entity.Attachment;

public interface AttachmentStorageService {
	

	Long uploadProfileImageAttachment(InputStream is, String fileName);
	
	Attachment getAttachment(Long id);

}
