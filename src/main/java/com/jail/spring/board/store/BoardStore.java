package com.jail.spring.board.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jail.spring.board.controller.dto.BoardAddRequest;
import com.jail.spring.board.controller.dto.BoardModifyRequest;
import com.jail.spring.board.domain.BoardVO;

public interface BoardStore {
	//여기도 온라인으로 정리!
	
	//1.리스트
	List<BoardVO> selectBoardList(SqlSession session,int currentPage);
	//detail
	BoardVO slectOneByNo(SqlSession session, int boardNo);
	//등록
	int insertBoard(SqlSession session, BoardAddRequest board);
	//modify(수정)
	int updateBoard(SqlSession session, BoardModifyRequest board);
	//delete(삭제)
	int deleteBoard(SqlSession session, int boardNo);

}
