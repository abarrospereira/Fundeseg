package br.com.app.api.uploader.storage.validator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.app.api.exception.ValidationException;
import br.com.app.api.util.messages.ErrorCode;
import br.com.app.api.util.messages.Message;

@Service
public class StorageValidator {

	public void validateStoragePath(String storagePath) {
		if (StringUtils.isBlank(storagePath)) {
			List<Message> errors = new ArrayList<>();
			errors.add(ErrorCode.FILE_STORAGE_PATH_IS_EMPTY.getfundesegError());
			throw new ValidationException(errors);
		}

		File path = new File(storagePath);
		if (!path.exists()) {
			List<Message> errors = new ArrayList<>();
			errors.add(ErrorCode.FILE_STORAGE_PATH_NOT_EXISTS.getfundesegError().withParameter("path", storagePath));
			throw new ValidationException(errors);
		}
	}

	public void validateMultipartFile(MultipartFile file) {
		if (file.isEmpty()) {
			List<Message> errors = new ArrayList<>();
			errors.add(ErrorCode.FILE_STORAGE_EMPTY_FILE.getfundesegError());
			throw new ValidationException(errors);
		}
	}

	public void validateDeleteFileExists(String fileId, String storagePath) {
		File file = new File(storagePath + fileId);
		if (!file.exists()) {
			List<Message> errors = new ArrayList<>();
			errors.add(ErrorCode.FILE_STORAGE_DELETE_FILE_NOT_EXISTS.getfundesegError());
			throw new ValidationException(errors);
		}
	}

	public void validateGetFileExists(String fileId, String storagePath) {
		File file = new File(storagePath + fileId);
		if (!file.exists()) {
			List<Message> errors = new ArrayList<>();
			errors.add(ErrorCode.FILE_STORAGE_GET_FILE_NOT_EXISTS.getfundesegError());
			throw new ValidationException(errors);
		}
	}

}

