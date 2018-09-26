package br.com.app.api.uploader.storage.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.Storage.BlobTargetOption;
import com.google.cloud.storage.Storage.PredefinedAcl;
import com.google.cloud.storage.StorageOptions;

import br.com.app.api.exception.ValidationException;
import br.com.app.api.uploader.storage.MultipartUploadFile;
import br.com.app.api.uploader.storage.validator.StorageValidator;
import br.com.app.api.util.messages.ErrorCode;
import br.com.app.api.util.messages.Message;

@Service
public class StorageService {

	private static final Logger LOGGER = LoggerFactory.getLogger(StorageService.class);

	@Autowired
	private StorageValidator storageValidator;

	private static Storage storage = StorageOptions.getDefaultInstance().getService();

	public MultipartUploadFile saveMultipartFile(MultipartFile file, String storagePath) {
		// storageValidator.validateStoragePath(storagePath);
		storageValidator.validateMultipartFile(file);

		MultipartUploadFile fileDTO = new MultipartUploadFile();
		fileDTO.setFileId(UUID.randomUUID().toString());
		fileDTO.setFileName(getFilename(file.getOriginalFilename()));

	//	createPath(storagePath);

		String filePath = storagePath + fileDTO.getFileId();
		while (new File(filePath).exists()) {
			fileDTO.setFileId(UUID.randomUUID().toString());
			filePath = storagePath + fileDTO.getFileId();
		}

		try {

			BlobInfo blobInfo = storage.create(BlobInfo.newBuilder("fundeseg-attachments",
					fileDTO.getFileId()).setContentType(file.getContentType()).build(),
					file.getBytes(),
					BlobTargetOption.predefinedAcl(PredefinedAcl.PUBLIC_READ));

			fileDTO.setLink(blobInfo.getMediaLink());
			
			return fileDTO;
		} catch (Exception e) {
			List<Message> errors = new ArrayList<>();
			errors.add(
					ErrorCode.FILE_STORAGE_SAVE_FAILED.getfundesegError().withParameter("exception", e.getMessage()));

			LOGGER.error("Error ao salvar arquivo", e);
			throw new ValidationException(errors);
		}
	}

	private void createPath(String storagePath) {
		Path path = Paths.get(storagePath);

		// if directory exists?
		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				// fail to create directory
				e.printStackTrace();
			}
		}
	}

	private String getFilename(String filename) {
		try {
			return URLDecoder.decode(filename, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return filename;
		}
	}

	public boolean deleteFileById(String fileId, String storagePath) {
		storageValidator.validateStoragePath(storagePath);
		storageValidator.validateDeleteFileExists(fileId, storagePath);

		File file = new File(storagePath + fileId);
		try {
			file.delete();
			return true;
		} catch (Exception e) {
			List<Message> errors = new ArrayList<>();
			errors.add(
					ErrorCode.FILE_STORAGE_DELETE_FAILED.getfundesegError().withParameter("exception", e.getMessage()));
			throw new ValidationException(errors);
		}
	}

	public File findFile(String storagePath, String fileId) {
		return new File(storagePath + fileId);
	}

	public boolean fileExists(String fileId, String storagePath) {
		storageValidator.validateStoragePath(storagePath);

		File file = new File(storagePath + fileId);
		return file.exists();
	}

	public void copyFileToServletResponse(String storagePath, String fileId, String fileName,
			HttpServletResponse response) {
		storageValidator.validateStoragePath(storagePath);
		storageValidator.validateGetFileExists(fileId, storagePath);

		try {
			File f = new File(storagePath + fileId);
			InputStream is = new FileInputStream(f);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			IOUtils.copy(is, baos);

			is.close();

			response.setHeader("Expires", "0");
			response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Pragma", "public");

			response.setContentType("application/octet-stream");
			response.setHeader("Content-disposition", "inline; filename=" + fileName);
			response.setContentLength(baos.size());

			ServletOutputStream out = response.getOutputStream();

			baos.writeTo(out);

			out.flush();

		} catch (IOException ex) {
			throw new RuntimeException("IOError writing file to output stream");
		}
	}
}
