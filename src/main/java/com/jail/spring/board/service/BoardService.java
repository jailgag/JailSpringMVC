package com.jail.spring.board.service;

import java.util.List;

import com.jail.spring.board.controller.dto.BoardAddRequest;
import com.jail.spring.board.controller.dto.BoardModifyRequest;
import com.jail.spring.board.domain.BoardVO;

public interface BoardService {
	
	//online으로 정리!!
	List<BoardVO> selectBoardList(int currentPage);
	//1detail
	BoardVO selectOneByNo(int boardNo);
	//등록!
	int insertBoard(BoardAddRequest board);
	//modify수정!
	int updateBoard(BoardModifyRequest board);
	//2.delete()
	int deleteBoard(int boardNo);
	//페이징
	int getTotalCount();
	
}
