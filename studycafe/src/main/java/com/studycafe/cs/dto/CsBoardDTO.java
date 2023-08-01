package com.studycafe.cs.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import com.studycafe.cs.entity.CsEntity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class CsBoardDTO {
	// Data Transferation to TeamBoardEntity

	private long idx;
	private String csTitle;
	private String csContent;
	private String csWriter;
	private Timestamp createDate;
	private LocalDateTime modifiedDate;

	// 단일 dto 리턴
	public CsEntity sendDataToEntity() {

		CsEntity teamBoardEntityBuilder = CsEntity.builder()
				.idx(idx)
				.csTitle(csTitle)
				.csContent(csContent)
				.csWriter(csWriter)
				.createDate(createDate)
				.modifiedDate(modifiedDate)
				.build();

		return teamBoardEntityBuilder;
	}

	// 페이징 객체 DTO 변환작업
	public Page<CsBoardDTO> toDtoList(Page<CsEntity> boardList) {

		Page<CsBoardDTO> csDtoList = boardList.map(list -> CsBoardDTO.builder()
				.idx(list.getIdx())
				.csTitle(list.getCsTitle())
				.csContent(list.getCsContent())
				.csWriter(list.getCsWriter())
				.createDate(list.getCreateDate())
				.modifiedDate(list.getModifiedDate())
				.build());

		return csDtoList;

	}

	@Builder
	public CsBoardDTO(long idx, String csTitle, String csContent, String csWriter, Timestamp createDate, LocalDateTime modifiedDate) {

		this.idx = idx;
		this.csTitle = csTitle;
		this.csContent = csContent;
		this.csWriter = csWriter;
		this.createDate = createDate;
		this.modifiedDate = modifiedDate;
	}

}
