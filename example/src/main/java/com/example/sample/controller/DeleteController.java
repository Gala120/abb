package com.example.sample.controller;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import org.apache.commons.lang3.math.*;

import com.example.sample.service.*;

@WebServlet("/board/delete")
public class DeleteController extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str = request.getParameter("bno");
		int bno = NumberUtils.toInt(str, 0);
		
		BoardService service = BoardService.getInstance();
		boolean result = service.delete(bno);
		
		response.setContentType("text/plain;charset=utf-8");
		if(result==false) {
			response.setStatus(HttpServletResponse.SC_CONFLICT);
			response.getWriter().print("글을 찾을 수 없습니다");
		} else {
			response.getWriter().print(bno + "번 글을 삭제했습니다");
		}
	}
}







