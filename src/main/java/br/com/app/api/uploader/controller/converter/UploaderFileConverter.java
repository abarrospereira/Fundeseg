package br.com.app.api.uploader.controller.converter;

import org.springframework.stereotype.Service;

import br.com.app.api.uploader.dto.UploadFile;
import br.com.app.api.uploader.storage.MultipartUploadFile;

@Service
public class UploaderFileConverter {

	public UploadFile  convert(MultipartUploadFile file) {
		UploadFile uploadFile = new UploadFile();
		uploadFile.setFileId(file.getFileId());
		uploadFile.setFileName(file.getFileName());
		uploadFile.setLink(file.getLink());

		return uploadFile;
	}

}
