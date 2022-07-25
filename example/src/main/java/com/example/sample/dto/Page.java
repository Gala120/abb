package com.example.sample.dto;

import java.util.*;

import com.example.sample.entity.*;

import lombok.*;

@Getter
@AllArgsConstructor
public class Page {
	private Integer pageno;
	private Integer pagesize;
	private Integer totalcount;
	private List<Board> boardList;
	
	// 출력용 DTO. 엔티티에서 DTO로 변환
}
