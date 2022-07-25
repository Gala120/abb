package com.example.sample.dto;

import javax.servlet.http.*;

import com.example.sample.entity.*;

import lombok.*;

// DTO : Data Transfer Object. 사용자와 입출력하는 클래스

@Data
@AllArgsConstructor
public class WriteDto {
	private String title;
	private String content;
	private String writer;
	private Part attachment;
	
	// 입력용 DTO. DTO에서 엔티티 변환 필요
	public Board toEntity() {
		return Board.builder().title(title).content(content).writer(writer).build();
	}
}



