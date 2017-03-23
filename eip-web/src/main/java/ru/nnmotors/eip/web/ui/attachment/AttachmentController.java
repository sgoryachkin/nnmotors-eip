package ru.nnmotors.eip.web.ui.attachment;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.nnmotors.eip.business.api.model.entity.Attachment;
import ru.nnmotors.eip.business.api.service.AttachmentService;

@Controller
@Transactional
@RequestMapping("attachment")
public class AttachmentController {
	
	@Autowired
	private AttachmentService attachmentService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AttachmentController.class);
	
	@RequestMapping(value = "download/{id}/{name}", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> download(@PathVariable Long id, @PathVariable String name) {	
		LOGGER.debug("download attachment");

		Attachment attachment = attachmentService.getAttachment(id);
		InputStream attachmentInputSteram = attachmentService.getAttachmentInputStream(id);
		
	    HttpHeaders respHeaders = new HttpHeaders();
	    respHeaders.setContentType(MediaType.parseMediaType(attachment.getContentType()));
	    respHeaders.setCacheControl(CacheControl.maxAge(1, TimeUnit.HOURS).getHeaderValue());
	    //respHeaders.setContentLength(12345678);
	    respHeaders.setContentDispositionFormData("attachment", attachment.getOriginalFileName());

	    InputStreamResource isr = new InputStreamResource(new BufferedInputStream(attachmentInputSteram));
	    
	    return new ResponseEntity<InputStreamResource>(isr, respHeaders, HttpStatus.OK);
	}

}
