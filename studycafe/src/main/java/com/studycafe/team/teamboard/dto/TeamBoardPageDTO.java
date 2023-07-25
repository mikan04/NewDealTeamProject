package com.studycafe.team.teamboard.dto;

import org.springframework.data.domain.Page;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class TeamBoardPageDTO {

	private int number; // 현재 페이지
	private int numberOfElements; // 현재 페이지의 출력 게시글 갯수
	private int size; // 한 페이지당 설정된 출력갯수
	private long totalElements; // 전체 게시글 수
	private int totalPages; // 전체 페이지 수

	private boolean first; // 첫 페이지 여부
	private boolean last; // 마지막 페이지 여부
	private boolean previous; // true면 뒷화살표
	private boolean next; // true면 앞화살표
	
	// TeamBoardEntity -> TeamBoardDTO 객체를 받아 PageDTO로 변환
	public TeamBoardPageDTO convertPageDTO(Page<TeamBoardDTO> teamBoardDTOList) {
		
		TeamBoardPageDTO teamBoardPage = TeamBoardPageDTO.builder()
				.number(teamBoardDTOList.getNumber())
				.numberOfElements(teamBoardDTOList.getNumberOfElements())
				.size(teamBoardDTOList.getSize())
				.totalElements(teamBoardDTOList.getTotalElements())
				.totalPages(teamBoardDTOList.getTotalPages())
				.first(teamBoardDTOList.isFirst())
				.last(teamBoardDTOList.isLast())
				.previous(teamBoardDTOList.hasPrevious())
				.next(teamBoardDTOList.hasNext())
				.build();
		
		return teamBoardPage;
	}

	@Builder
	public TeamBoardPageDTO(int number, int numberOfElements, int size, long totalElements, int totalPages,
			boolean first, boolean last, boolean previous, boolean next) {
		
		this.number = number;
		this.numberOfElements = numberOfElements;
		this.size = size;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
		this.first = first;
		this.last = last;
		this.previous = previous;
		this.next = next;
	}

}
