package com.example.sample.entity;

import java.util.*;

import com.example.sample.dto.*;
import com.fasterxml.jackson.annotation.*;

import lombok.*;

@Getter
@EqualsAndHashCode
@ToString
@Builder
public class Board {
	private Integer bno;
	private String title;
	private String content;
	private String writer;
	private String attachment;
	
	// @Builder를 사용할 경우 인스턴스 초기화가 적용되지 않는다. @Builder.Default 필요
	@Builder.Default
	// Jackson에서 출력할 때 출력형식을 지정하는 어노테이션
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date writeTime = new Date();
	
	@Builder.Default
	private Integer readCnt = 0;

	public void init(String uploadUrl, String name, int bno) {
		this.attachment = uploadUrl + name;
		this.bno = bno;
	}
	
	public void init(int bno) {
		this.bno = bno;
	}
	
	public void increaseReadCnt() {
		this.readCnt++;
	}
	
	public void update(UpdateDto dto) {
		this.title = dto.getTitle();
		this.content = dto.getContent();
	}
}
