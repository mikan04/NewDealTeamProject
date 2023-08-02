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
	private String username;
	private String filePath;
	private String fileKey;
	private boolean secret;
	private Timestamp createDate;
	private LocalDateTime modifiedDate;

	// 단일 dto->entity
	public CsEntity sendDataToEntity(CsBoardDTO csDto) {

		CsEntity csEntityBuilder = CsEntity.builder()
				.idx(csDto.getIdx())
				.csTitle(csDto.getCsTitle())
				.csContent(csDto.getCsContent())
				.csWriter(csDto.getCsWriter())
				.filePath(csDto.getFilePath())
				.fileKey(csDto.getFileKey())
				.secret(csDto.isSecret())
				.username(csDto.getUsername())
				.createDate(csDto.getCreateDate())
				.modifiedDate(csDto.getModifiedDate())
				.build();

		return csEntityBuilder;
	}

	// 단일 entity->dto
	public static CsBoardDTO sendDataToDto(CsEntity csEntity) {

		CsBoardDTO csDtoBuilder = CsBoardDTO.builder()
				.idx(csEntity.getIdx())
				.csTitle(csEntity.getCsTitle())
				.csContent(csEntity.getCsContent())
				.csWriter(csEntity.getCsWriter())
				.filePath(csEntity.getFilePath())
				.fileKey(csEntity.getFileKey())
				.secret(csEntity.isSecret())
				.username(csEntity.getUsername())
				.createDate(csEntity.getCreateDate())
				.modifiedDate(csEntity.getModifiedDate())
				.build();

		return csDtoBuilder;
	}

	// 페이징 객체 DTO 변환작업
	public Page<CsBoardDTO> toDtoList(Page<CsEntity> boardList) {

		Page<CsBoardDTO> csDtoList = boardList.map(list -> CsBoardDTO.builder()
				.idx(list.getIdx())
				.csTitle(list.getCsTitle())
				.csContent(list.getCsContent())
				.csWriter(list.getCsWriter())
				.username(list.getUsername())
				.fileKey(list.getFileKey())
				.secret(list.isSecret())
				.createDate(list.getCreateDate())
				.build());

		return csDtoList;

	}

	@Builder
	public CsBoardDTO(
			long idx, String csTitle,
			String csContent, String csWriter,
			Timestamp createDate, LocalDateTime modifiedDate,
			String filePath, String fileKey,
			boolean secret, String username) {

		this.idx = idx;
		this.csTitle = csTitle;
		this.csContent = csContent;
		this.csWriter = csWriter;
		this.username = username;
		this.filePath = filePath;
		this.fileKey = fileKey;
		this.secret = secret;
		this.createDate = createDate;
		this.modifiedDate = modifiedDate;
	}

}
