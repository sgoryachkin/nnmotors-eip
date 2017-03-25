package ru.nnmotors.eip.business.impl.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;

import ru.nnmotors.eip.business.api.model.entity.Attachment;
import ru.nnmotors.eip.business.api.service.AttachmentService;

@Service
@Transactional
public class AttachmentServiceImpl implements AttachmentService {

	public static final String PROFILE_IMAGE_CATEGORY = "webImage";

	private static final Logger LOGGER = LoggerFactory.getLogger(AttachmentServiceImpl.class);

	@Autowired
	@Qualifier("attachmentStorageUrl")
	private String attachmentStorageUrl;

	@PersistenceContext
	private EntityManager em;

	@Override
	public Long uploadWebImageAttachment(InputStream is, String originalFileName, String contentType) {
		String storageFileName = UUID.randomUUID().toString();

		File file = null;
		try {
			file = new File(
					attachmentStorageUrl + File.separator + PROFILE_IMAGE_CATEGORY + File.separator + storageFileName);
			File parent = file.getParentFile();
			if (!parent.exists() && !parent.mkdirs() && !file.createNewFile()) {
				throw new IOException("Couldn't create file: " + file);
			}
		} catch (IOException e) {
			throw new IllegalStateException(e.getLocalizedMessage(), e);
		}

		try (OutputStream os = new FileOutputStream(file)) {
			StreamUtils.copy(is, os);
		} catch (IOException e) {
			throw new IllegalStateException(e.getLocalizedMessage(), e);
		}

		try {
			Attachment attachment = new Attachment();
			attachment.setStorageCategoryName(PROFILE_IMAGE_CATEGORY);
			attachment.setStorageFileName(storageFileName);
			attachment.setOriginalFileName(originalFileName);
			attachment.setContentType(contentType);
			em.persist(attachment);
			return attachment.getId();
		} catch (Exception e) {
			if (!file.delete()) {
				throw e;
			}
			throw e;
		}

	}

	@Override
	public Attachment getAttachment(Long id) {
		LOGGER.debug("Get attachment" + id);
		Attachment attachment = em.find(Attachment.class, id);
		if (attachment == null) {
			throw new IllegalStateException("Attachment not found: " + id);
		}
		return attachment;
	}

	@Override
	public InputStream getAttachmentInputStream(Long id) {
		Attachment attachment = getAttachment(id);
		File file = new File(attachmentStorageUrl + File.separator + attachment.getStorageCategoryName()
				+ File.separator + attachment.getStorageFileName());
		try {
			return new FileInputStream(file);
		} catch (FileNotFoundException e) {
			throw new IllegalStateException(e.getLocalizedMessage(), e);
		}
	}

}
