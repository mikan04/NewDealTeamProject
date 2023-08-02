package com.studycafe.utils.file.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface S3FileService {
	
	String saveFile(MultipartFile file , String identifier);
	
	byte[] downloadFile(String filename);
	
	void deleteFile(String fileKey);
	
	List<String> listAllFiles();
	
	String getKey(MultipartFile file ,String identifier);

}
