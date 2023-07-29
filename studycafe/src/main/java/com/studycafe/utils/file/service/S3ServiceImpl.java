package com.studycafe.utils.file.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.util.IOUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class S3ServiceImpl implements S3FileService {

	@Value("${cloud.aws.s3.bucket}")
	private String bucketName;

	@Value("${cloud.aws.s3.objurl}")
	private String s3ObjectUrl;

	private final AmazonS3 s3;

	public S3ServiceImpl(AmazonS3 s3) {
		this.s3 = s3;
	}

	// S3 파일 저장하기
	@Override
	public String saveFile(MultipartFile file, String filePath) {

		String originalFilename = file.getOriginalFilename();
		log.info("원본 파일 이름 : {}", originalFilename);

		try {
			File insertedFile = convertMultiPartToFile(file);

			// 업로드 된 url에 따라서 S3 경로 다르게, 파일이름 초기화.
			String insertedFileNameAndPath = "";

			log.info("변경된 파일 이름 : {}", insertedFile);

			// 들어온 값에 따라 S3 파일 경로 검증 => 리팩토링 어떻게 하면좋지?
			if ("team".equals(filePath)) {
				insertedFileNameAndPath = "teamregis/" + insertedFile.toString();
				log.info("teamregis 폴더 업로드 : {}", insertedFileNameAndPath);

			} else if ("studyregis".equals(filePath)) {
				insertedFileNameAndPath = "studyregis/" + insertedFile.toString();
				log.info("studyregis 폴더 업로드 : {}", insertedFileNameAndPath);

			} else if ("ck".equals(filePath)) {
				insertedFileNameAndPath = "ckupload/" + insertedFile.toString();
				log.info("ckupload 폴더 업로드 : {}", insertedFileNameAndPath);
			}

			// log.info("파일 경로 및 이름 : {}", insertedFileNameAndPath);
			s3.putObject(bucketName, insertedFileNameAndPath, insertedFile);

			// log.info("Base64-encoded MD5 hash 파일명 : {}" , putObjectResult.getContentMd5());

			String s3SourceUrl = s3ObjectUrl + insertedFileNameAndPath;

			log.info("최종 파일 객체 url : {}", s3SourceUrl);

			// 로컬에 저장되는 잔여 파일 삭제.
			insertedFile.delete();

			return s3SourceUrl;

		} catch (IOException e) {

			throw new RuntimeException(e);
		}

	}

	// S3->local 파일 다운로드
	@Override
	public byte[] downloadFile(String filename) {

		S3Object s3Object = s3.getObject(bucketName, filename);

		S3ObjectInputStream objectInputStream = s3Object.getObjectContent();

		try {
			return IOUtils.toByteArray(objectInputStream);
		} catch (IOException e) {

			throw new RuntimeException(e);
		}

	}

	// 파일 삭제
	@Override
	public String deleteFile(String filename) {
		s3.deleteObject(bucketName, filename);

		return "파일 삭제 완료";
	}

	// S3에 저장된 파일 리스트 가져오기
	@Override
	public List<String> listAllFiles() {

		ListObjectsV2Result listObjectsV2Result = s3.listObjectsV2(bucketName);

		return listObjectsV2Result.getObjectSummaries().stream().map(S3ObjectSummary::getKey)
				.collect(Collectors.toList());
	}

	// 파일 컨버팅
	private File convertMultiPartToFile(MultipartFile file) throws IOException {
		log.info("파일 컨버팅 시작");

		String uuid = UUID.randomUUID().toString();

		String convertedFileName = uuid + "_" + file.getOriginalFilename();

		File convertedFile = new File(convertedFileName);

		FileOutputStream fos = new FileOutputStream(convertedFile);

		fos.write(file.getBytes());

		fos.close();

		return convertedFile;

	}

}
