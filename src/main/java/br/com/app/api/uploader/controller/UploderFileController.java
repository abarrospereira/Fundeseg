package br.com.app.api.uploader.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.app.api.uploader.controller.converter.UploaderFileConverter;
import br.com.app.api.uploader.dto.UploadFile;
import br.com.app.api.uploader.enums.FileTypes;
import br.com.app.api.uploader.service.FileService;

@RestController
@RequestMapping("/rest/uploader")
public class UploderFileController {

	@Autowired
	private FileService serviceRequestUploadFileService;

	@Autowired
	private UploaderFileConverter uploaderFileConverter;

	@RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = "multipart/form-data")
	@PreAuthorize("hasRole('ROLE_USER')")
	public @ResponseBody UploadFile upload(@RequestParam("file") MultipartFile file) {
		return uploaderFileConverter.convert(serviceRequestUploadFileService.save(file, FileTypes.ATTACHMENT));
	}
	
	@RequestMapping(value = "/exists", method = RequestMethod.GET)
	public @ResponseBody boolean exists() {
		 System.out.println("teste");
		 return true;
	}

//	@RequestMapping(method = RequestMethod.DELETE)
//	public void delete(@RequestParam("fileId") String fileId, @RequestParam("storage") FileTypes storageFileType) {
//		serviceRequestUploadFileService.delete(fileId, storageFileType);
//	}
//
//	@RequestMapping(value = "/exists", method = RequestMethod.GET)
//	public @ResponseBody boolean exists(@RequestParam("fileId") String fileId, @RequestParam("storage") FileTypes storageFileType) {
//		return serviceRequestUploadFileService.exists(fileId, storageFileType);
//	}
//
//	@RequestMapping(value = "/{storage}/{fileId}", method = RequestMethod.GET)
//	public void getFile(@PathVariable("storage") FileTypes storageFileType, @PathVariable("fileId") String fileId, @RequestParam("fileName") String fileName, HttpServletResponse response) {
//		serviceRequestUploadFileService.addFileToResponse(storageFileType, fileId, fileName, response);
//	}
//
	@RequestMapping(value = "/{fileId}/download", method = RequestMethod.GET)
	public ResponseEntity<Resource> downloadFile(@PathVariable("fileId") String fileId) throws IOException {

		File file = new File(FileTypes.ATTACHMENT.getPath() + fileId);
		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
		HttpHeaders headers = new HttpHeaders();
		headers.set("Expires", "0");
		headers.set("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
		headers.set("Pragma", "public");

		headers.set("ContentType", "application/octet-stream");
		headers.set("Content-disposition", "inline; filename=" + file.getName());

		return ResponseEntity.ok()
							 .headers(headers)
							 .contentLength(file.length())
							 .contentType(MediaType.parseMediaType("application/octet-stream"))
							 .body(resource);
	}
}
