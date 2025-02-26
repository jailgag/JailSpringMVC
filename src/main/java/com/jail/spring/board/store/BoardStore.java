package com.jail.spring.board.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jail.spring.board.controller.dto.BoardAddRequest;
import com.jail.spring.board.domain.BoardVO;

public interface BoardStore {
	
	//1.리스트
	List<BoardVO> selectBoardList(SqlSession session);
	//등록
	int insertBoard(SqlSession session, BoardAddRequest board);
	//detail
	BoardVO slectOneByNo(SqlSession session, int boardNo);

}
