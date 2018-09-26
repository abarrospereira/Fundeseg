package br.com.app.api.uploader.enums;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileTypesInitializer {

	@Value("${service.request.purchase.order.storage.path}")
	private String purchaseOrderStoragePath;

	@Value("${service.request.attachment.storage.path}")
	private String attachmentStoragePath;

	private static final Logger LOGGER = LoggerFactory.getLogger(FileTypesInitializer.class);

	@PostConstruct
	public void postConstruct() {
		LOGGER.info("Initializing ServiceRequestFileTypes");

		FileTypes.ATTACHMENT.setPath(attachmentStoragePath);
		FileTypes.PURCHASE_ORDER.setPath(purchaseOrderStoragePath);

	}

}