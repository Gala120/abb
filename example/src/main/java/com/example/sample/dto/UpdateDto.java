package com.example.sample.dto;

import lombok.*;

@Data
@AllArgsConstructor
public class UpdateDto {
	private Integer bno;
	private String title;
	private String content;
	
	// 입력용 DTO
}
