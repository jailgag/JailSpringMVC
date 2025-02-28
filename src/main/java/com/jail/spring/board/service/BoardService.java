package com.jail.spring.board.service;

import java.util.List;

import com.jail.spring.board.controller.dto.BoardAddRequest;
import com.jail.spring.board.domain.BoardVO;

public interface BoardService {
	
	List<BoardVO> selectBoardList();
	//등록!
	int insertBoard(BoardAddRequest board);
	//1detail
	BoardVO selectOneByNo(int boardNo);
	//delete()
	int deleteBoard(int boardNo);
}
