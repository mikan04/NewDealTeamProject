package com.studycafe.team.teamboard.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.studycafe.team.teamboard.entity.TeamBoardEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class TeamBoardDTO {
	// Data Transferation to TeamBoardEntity
	
	private long teamBoardNum;
	private String teamBoardTitle;
	private String teamBoardContent;
	private String teamBoardWriter;
	private Timestamp createDate;
	private LocalDateTime modifiedDate;
	
	public TeamBoardEntity sendDataToEntity() {
		
		TeamBoardEntity teamBoardEntityBuilder = TeamBoardEntity.builder()
				.teamBoardNum(teamBoardNum)
				.teamBoardTitle(teamBoardTitle)
				.teamBoardContent(teamBoardContent)
				.teamBoardWriter(teamBoardWriter)
				.createDate(createDate)
				.modifiedDate(modifiedDate)
				.build();
		
		return teamBoardEntityBuilder;
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
