package ru.nnmotors.eip.business.api.service;

import java.io.InputStream;

import ru.nnmotors.eip.business.api.model.entity.Attachment;

public interface AttachmentService {
	

	Long uploadProfileImageAttachment(InputStream is, String fileName);
	
	Attachment getAttachment(Long id);
	
	InputStream getAttachmentInputStream(Long id);

}
