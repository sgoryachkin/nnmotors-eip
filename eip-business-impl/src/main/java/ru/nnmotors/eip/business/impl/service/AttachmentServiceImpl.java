package ru.nnmotors.eip.business.impl.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;

import ru.nnmotors.eip.business.api.model.entity.Attachment;
import ru.nnmotors.eip.business.api.service.AttachmentStorageService;

@Service
@Transactional
public class AttachmentServiceImpl implements AttachmentStorageService {

	@Autowired
	@Qualifier("imageStorage")
	private String imageStorageUrl;

	@PersistenceContext
	private EntityManager em;

	@Override
	public Long uploadProfileImageAttachment(InputStream is, String fileName) {
		String storageFileName = UUID.randomUUID().toString();

		try {
			// Open the connection and prepare to POST
			URL url = new URL("file://C:/" + storageFileName);
			URLConnection uc = url.openConnection();
			uc.setDoOutput(true);
			uc.setAllowUserInteraction(false);
		
			StreamUtils.copy(is, uc.getOutputStream());
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}

		return null;
	}

	@Override
	public Attachment getAttachment(Long id) {
		Attachment attachment = em.find(Attachment.class, id);
		if (attachment == null) {
			throw new IllegalStateException("Attachment not found: " + id);
		}
		return attachment;
	}

}
