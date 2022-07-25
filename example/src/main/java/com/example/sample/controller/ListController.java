package com.example.sample.controller;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import org.apache.commons.lang3.math.*;

import com.example.sample.dto.*;
import com.example.sample.service.*;
import com.fasterxml.jackson.databind.*;

@WebServlet("/board/all")
public class ListController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 출력형식은 json, 출력문자열은 utf-8로 지정
		response.setContentType("application/json;charset=utf-8");

		String str = request.getParameter("pageno");
		// NumberUtils.toInt를 이용해 pageno에 기본값을 지정
		int pageno = NumberUtils.toInt(str, 1);
		
		BoardService service = BoardService.getInstance();
		Page page = service.list(pageno);
		
		String json = new ObjectMapper().writeValueAsString(page);
		response.getWriter().print(json);
	}
}







