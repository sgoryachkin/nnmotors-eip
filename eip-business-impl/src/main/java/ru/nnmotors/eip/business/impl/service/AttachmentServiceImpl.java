package ru.nnmotors.eip.business.impl.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
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

	private static final Logger LOGGER = LoggerFactory.getLogger(AttachmentServiceImpl.class);

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
			URL url = new URL(imageStorageUrl + storageFileName);
			OutputStream os = null;
			if ("file".equals(url.getProtocol())) {
				File file = new File(url.getFile());
				
				File parent = file.getParentFile();
				if (!parent.exists() && !parent.mkdirs() && !file.createNewFile()) {
				    throw new IOException("Couldn't create dir: " + parent);
				}
				
				os = new FileOutputStream(file);
			} else {
				URLConnection uc = url.openConnection();
				uc.setDoOutput(true);
				uc.setAllowUserInteraction(false);
				os = uc.getOutputStream();
			}

			StreamUtils.copy(is, os);
			os.close();
			is.close();
			LOGGER.debug(url.toString());
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
		
		Attachment attachment = new Attachment();
		attachment.setRelativeUrl(storageFileName);
		attachment.setStarageId("imageStorage");
		attachment.setOriginalFileName(fileName);
		em.persist(attachment);

		return attachment.getId();
	}

	@Override
	public Attachment getAttachment(Long id) {
		Attachment attachment = em.find(Attachment.class, id);
		if (attachment == null) {
			throw new IllegalStateException("Attachment not found: " + id);
		}
		return attachment;
	}

	@Override
	public InputStream getAttachmentInputStream(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
