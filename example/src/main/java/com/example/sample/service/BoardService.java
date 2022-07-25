package com.example.sample.service;

import java.io.*;
import java.util.*;

import javax.servlet.http.*;

import com.example.sample.dto.*;
import com.example.sample.entity.*;

public class BoardService {
	private List<Board> list = new Vector<>();
	private int bno = 1;
	private final Integer PAGESIZE = 10;
	private final String UPLOAD_FOLDER = "c:/upload/";
	private final String UPLOAD_URL = "http://localhost:8081/images/";
	
	// 서비스에 싱글톤 패턴 적용
	private static BoardService service = new BoardService();
	public static BoardService getInstance() {
		return service;
	}
	
	public Board write(WriteDto dto) {
		Board board = dto.toEntity();
		Part a = dto.getAttachment();
		if(a!=null && !a.getSubmittedFileName().equals("")) {
			String name = System.currentTimeMillis() + "-" + a.getSubmittedFileName();
			try {
				a.write(UPLOAD_FOLDER +  name);
				board.init(UPLOAD_URL, name, bno++);
			} catch (IOException e) {
				e.printStackTrace();
				board.init(bno++);	
			} 
		} else
			board.init(bno++);
		list.add(0, board);
		return board;
	}
	
	// 게시판에 글을 123개 추가하는 생성자 : 이후 테스트를 위한 임시 데이터 추가
	private BoardService() {
		for(int i=1; i<123; i++)
			write(new WriteDto(i+"", i+"번째 글", "spring", null));
	}
	
	public Optional<Board> read(Integer bno) {
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getBno()==bno) {
				list.get(i).increaseReadCnt();
				return Optional.of(list.get(i));
			}
		}
		return Optional.ofNullable(null);
	}
	
	public Page list(Integer pageno) {
		// 글이 13 12 11 10 9 8 7 6 5 4 3 2 1 
		// pageno 1. list.get(0) ~ list.get(9)
		// pageno 2. list.get(10) ~ list.get(19)->(13-1)
		int start = (pageno-1)*PAGESIZE;
		int end = start + PAGESIZE -1;
		if(end>(list.size()-1))
			end = list.size()-1;
		List<Board> result = new ArrayList<>();
		
		for(int i=start; i<=end; i++) {
			result.add(list.get(i));
		}
		return new Page(pageno, PAGESIZE, list.size(), result);
	}
	
	public Optional<Board> update(UpdateDto dto) {
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).getBno()==dto.getBno()) {
				list.get(i).update(dto);
				return Optional.of(list.get(i));
			}
		}
		return Optional.ofNullable(null);
	}
	
	public boolean delete(Integer bno) {
		return list.removeIf(b->b.getBno()==bno);
	}
}
