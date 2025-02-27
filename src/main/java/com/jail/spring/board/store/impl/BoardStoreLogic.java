package com.jail.spring.board.store.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.jail.spring.board.controller.dto.BoardAddRequest;
import com.jail.spring.board.domain.BoardVO;
import com.jail.spring.board.store.BoardStore;

@Repository
public class BoardStoreLogic implements BoardStore {

	
	@Override
	public List<BoardVO> selectBoardList(SqlSession session) {
		// TODO Auto-generated method stub
		List<BoardVO> bList = session.selectList("BoardMapper.selectBoardList");
		return bList;
	}

	@Override
	public int insertBoard(SqlSession session, BoardAddRequest board) {
		int result= session.insert("BoardMapper.insertBoard", board);
		return result;
	}

	@Override
	public BoardVO slectOneByNo(SqlSession session, int boardNo) {
		BoardVO board =session.selectOne("BoardMapper.slectOneByNo",boardNo);
		return board;
	}
	
}
