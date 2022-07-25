package com.example.sample.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.*;

import com.example.sample.dto.*;
import com.example.sample.entity.*;
import com.example.sample.service.*;

public class BoardServiceTest {
	BoardService service = BoardService.getInstance();
	
	//@Test
	public void writeTest() {
		WriteDto dto = new WriteDto("1번글", "1번글", "spring", null);
		Board board = service.write(dto);
		assertEquals(1, board.getBno());
	}
	
	//@Test
	public void readTest() {
		assertEquals(true, service.read(3).isPresent());
		assertEquals(true, service.read(1111).isEmpty());
	}
		
	@Test
	public void listTest() {
		assertEquals(122, service.list(2).getTotalcount());
	}
}





