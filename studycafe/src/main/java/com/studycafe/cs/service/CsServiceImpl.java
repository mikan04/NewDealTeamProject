package com.studycafe.cs.service;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.studycafe.cs.dto.CsBoardDTO;
import com.studycafe.cs.entity.CsEntity;
import com.studycafe.cs.repository.CsRepository;
import com.studycafe.utils.file.service.S3FileService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CsServiceImpl implements CsService {

	@Autowired
	private CsRepository csRepository;

	@Autowired
	private S3FileService s3Service;

	@Value("${cloud.aws.s3.objurl}")
	private String s3ObjectUrl;

	@Override
	@Transactional
	public Page<CsBoardDTO> getCsBoardList(Pageable pageable) {

		Page<CsEntity> boardList = csRepository.findAll(pageable);

		return new CsBoardDTO().toDtoList(boardList);
	}

	@Override
	@Transactional
	public List<CsEntity> getCsBoardListToIndex() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Long csBoardRegis(CsBoardDTO csBoardDTO, String identifier, MultipartFile file) {

		log.info("고객센터 글 작성 로직 실행");

		String fileKey = s3Service.saveFile(file, identifier);

		csBoardDTO.setFilePath(s3ObjectUrl + fileKey);
		csBoardDTO.setFileKey(fileKey);

		return csRepository.save(csBoardDTO.sendDataToEntity()).getIdx();

	}

	@Override
	@Transactional
	public CsEntity csBoardModify(CsBoardDTO csBoardDTO, String identifier, MultipartFile file) {

		log.info("고객센터 글 수정 로직");

		CsEntity pastEntity = csRepository.findById(csBoardDTO.getIdx()).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {

				return new IllegalArgumentException("해당하는 게시글을 찾을 수 없습니다.");
			}
		});

		String fileKey = s3Service.saveFile(file, identifier);

		s3Service.deleteFile(pastEntity.getFileKey());

		csBoardDTO.setFilePath(s3ObjectUrl + fileKey);
		csBoardDTO.setFileKey(fileKey);

		return csRepository.save(csBoardDTO.sendDataToEntity());

	}

	@Override
	@Transactional
	public CsBoardDTO getCsBoardPost(long idx) {

		CsEntity csEntity = csRepository.findById(idx)
				.orElseThrow(new Supplier<IllegalArgumentException>() {
					@Override
					public IllegalArgumentException get() {

						return new IllegalArgumentException("해당 게시글은 삭제되었거나 존재하지 않는 게시글입니다.");
					}
				});

		CsBoardDTO csBoards = CsBoardDTO.sendDataToDto(csEntity);

		return csBoards;
	}

	@Override
	@Transactional
	public boolean deleteCsBoard(long idx) {

		boolean isPresent = csRepository.findById(idx).isPresent();
		String s3FileKey = csRepository.findById(idx).get().getFileKey();

		try {

			if (isPresent) {

				csRepository.deleteById(idx);
				s3Service.deleteFile(s3FileKey);

				return true;
			}
		} catch (Exception e) {

			throw new IllegalArgumentException("존재하지 않는 게시물입니다.");
		}

		return false;
	}

}
