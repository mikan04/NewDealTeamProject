package com.studycafe.cs.service;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.studycafe.cs.dto.CsBoardDTO;
import com.studycafe.cs.entity.CsEntity;
import com.studycafe.cs.repository.CsRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CsServiceImpl implements CsService {

	@Autowired
	private CsRepository csRepository;

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
	public Long csBoardRegis(CsBoardDTO csBoardDTO) {

		log.info("고객센터 글 작성로직 실행");

		return csRepository.save(csBoardDTO.sendDataToEntity()).getIdx();
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

		CsBoardDTO csBoards = CsBoardDTO.builder()
				.idx(csEntity.getIdx())
				.csTitle(csEntity.getCsTitle())
				.csWriter(csEntity.getCsWriter())
				.csContent(csEntity.getCsContent())
				.createDate(csEntity.getCreateDate())
				.modifiedDate(csEntity.getModifiedDate())
				.build();

		return csBoards;
	}

	@Override
	@Transactional
	public boolean deleteCsBoard(long idx) {

		boolean isPresent = csRepository.findById(idx).isPresent();

		try {

			if (isPresent) {

				csRepository.deleteById(idx);

				return true;
			}
		} catch (Exception e) {

			throw new IllegalArgumentException("존재하지 않는 게시물입니다.");
		}

		return false;
	}

}
