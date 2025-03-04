package com.jail.spring.board.store.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.jail.spring.board.controller.dto.BoardAddRequest;
import com.jail.spring.board.controller.dto.BoardModifyRequest;
import com.jail.spring.board.domain.BoardVO;
import com.jail.spring.board.store.BoardStore;

@Repository
public class BoardStoreLogic implements BoardStore {

	
	@Override
	public List<BoardVO> selectBoardList(SqlSession session,int currentPage) {
		//아래 limit가10이면 10개 5면 5개?!
		int limit = 10;
		int offset = (currentPage-1)*limit;
		RowBounds rowBounds = new RowBounds(offset,limit);
		List<BoardVO> bList = session.selectList("BoardMapper.selectBoardList", null, rowBounds);
		return bList;
	}

	@Override
	public BoardVO slectOneByNo(SqlSession session, int boardNo) {
		BoardVO board =session.selectOne("BoardMapper.slectOneByNo",boardNo);
		return board;
	}

	@Override
	public int insertBoard(SqlSession session, BoardAddRequest board) {
		int result= session.insert("BoardMapper.insertBoard", board);
		return result;
	}

	//modify(수정)
	@Override
	public int updateBoard(SqlSession session, BoardModifyRequest board) {
		int result = session.update("BoardMapper.updateBoard",board);
		return result;
	}

	//삭제!
	@Override
	public int deleteBoard(SqlSession session, int boardNo) {
		int result = session.update("BoardMapper.deleteBoard",boardNo);
		return result;
	}
	//페이징!
	@Override
	public int getTotalCount(SqlSession session) {
		int totalCount = session.selectOne("BoardMapper.getTotalCount");
		return totalCount;
	}
	
}
