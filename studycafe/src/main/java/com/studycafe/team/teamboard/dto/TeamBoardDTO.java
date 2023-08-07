package com.studycafe.team.teamboard.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.domain.Page;

import com.studycafe.team.teamboard.entity.TeamBoardEntity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeamBoardDTO {
	// Data Transferation to TeamBoardEntity

	private long teamBoardNum;
	
	@NotBlank(message = "제목을 반드시 입력해주세요.")
	private String teamBoardTitle;
	
	@NotBlank
	@Size(min = 12, max = 1200 , message = "12자 이상 1200자 이하의 내용만 가능합니다.")
	private String teamBoardContent;
	private String teamBoardWriter;
	private Timestamp createDate;
	private LocalDateTime modifiedDate;

	// 단일 dto 리턴
	public TeamBoardEntity sendDataToEntity() {

		TeamBoardEntity teamBoardEntityBuilder = TeamBoardEntity.builder()
				.teamBoardNum(teamBoardNum)
				.teamBoardTitle(teamBoardTitle)
				.teamBoardContent(teamBoardContent)
				.teamBoardWriter(teamBoardWriter)
				.modifiedDate(modifiedDate)
				.build();

		return teamBoardEntityBuilder;
	}

	// 페이징 객체 DTO 변환작업
	public Page<TeamBoardDTO> toDtoList(Page<TeamBoardEntity> boardList) {

		Page<TeamBoardDTO> boardDtoList = boardList.map(list -> TeamBoardDTO.builder()
				.teamBoardNum(list.getTeamBoardNum())
				.teamBoardTitle(list.getTeamBoardTitle())
				.teamBoardWriter(list.getTeamBoardWriter())
				.teamBoardContent(list.getTeamBoardContent())
				.createDate(list.getCreateDate())
				.build());

		return boardDtoList;

	}

	@Builder
	public TeamBoardDTO(long teamBoardNum, String teamBoardTitle, String teamBoardContent, String teamBoardWriter,
			Timestamp createDate, LocalDateTime modifiedDate) {
		this.teamBoardNum = teamBoardNum;
		this.teamBoardTitle = teamBoardTitle;
		this.teamBoardContent = teamBoardContent;
		this.teamBoardWriter = teamBoardWriter;
		this.createDate = createDate;
		this.modifiedDate = modifiedDate;
	}

}
