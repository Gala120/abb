package com.example.sample.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import org.apache.commons.lang3.math.*;

import com.example.sample.entity.*;
import com.example.sample.service.*;
import com.fasterxml.jackson.databind.*;

@MultipartConfig
@WebServlet("/board")
public class ReadController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str = request.getParameter("bno");
		// NumberUtils.toInt를 이용해 bno에 기본값을 지정
		// - Integer.parseInt를 사용할 경우 bno가 없다면 예외 발생
		// - NumberUtils로 예외발생을 막고 0번으로 글번호 지정 -> 검색에 실패해 35라인에서 409 출력
		int bno = NumberUtils.toInt(str, 0);
		
		BoardService service = BoardService.getInstance();
		Optional<Board> board = service.read(bno);
		
		// 작업 결과에 따라 적절한 상태코드와 MIME 타입을 지정해 출력
		// 만약 board가 null이라면 409 보내기
		// board가 있다면 200에 json보내기
		if(board.isEmpty()) {
			// 출력형식은 json, 출력문자열은 utf-8로 지정
			response.setContentType("text/plain;charset=utf-8");
			response.setStatus(HttpServletResponse.SC_CONFLICT);
			response.getWriter().print("글을 찾을 수 없습니다");
		} else {
			response.setContentType("application/json;charset=utf-8");
			String json = new ObjectMapper().writeValueAsString(board.get());
			response.getWriter().print(json);
		}
	}
}







