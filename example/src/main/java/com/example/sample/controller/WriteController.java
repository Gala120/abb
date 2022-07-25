package com.example.sample.controller;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import com.example.sample.dto.*;
import com.example.sample.entity.*;
import com.example.sample.service.*;
import com.fasterxml.jackson.databind.*;

@MultipartConfig
@WebServlet("/board/new")
public class WriteController extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		
		String writer = request.getParameter("writer");
		String titile = request.getParameter("title");
		String content = request.getParameter("content");
		Part part = request.getPart("attachment");
		WriteDto dto = new WriteDto(titile, content, writer, part);
		
		BoardService service = BoardService.getInstance();
		Board board = service.write(dto);
		
		String json = new ObjectMapper().writeValueAsString(board);
		response.getWriter().print(json);
	}
}







