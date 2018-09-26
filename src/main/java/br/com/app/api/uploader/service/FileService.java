package br.com.app.api.uploader.service;

import java.io.File;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.app.api.uploader.enums.FileTypes;
import br.com.app.api.uploader.storage.MultipartUploadFile;
import br.com.app.api.uploader.storage.service.StorageService;

@Service
public class FileService {

	@Autowired
	private StorageService storageService;
	
	public MultipartUploadFile save(MultipartFile file, FileTypes storagePath) {
		return storageService.saveMultipartFile(file, storagePath.getPath());
	}

	public boolean delete(String fileId, FileTypes storagePath) {
		return storageService.deleteFileById(fileId, storagePath.getPath());
	}

	public boolean exists(String fileId, FileTypes storageFileType) {
		return storageService.fileExists(fileId, storageFileType.getPath());
	}

	public File findFile(FileTypes storageFileType, String fileId) {
		return storageService.findFile(storageFileType.getPath(), fileId);
	}

	public void addFileToResponse(FileTypes storageFileType, String fileId, String fileName, HttpServletResponse response) {
		storageService.copyFileToServletResponse(storageFileType.getPath(), fileId, fileName, response);
	}

}
