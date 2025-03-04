package com.jail.spring.board.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jail.spring.board.controller.dto.BoardAddRequest;
import com.jail.spring.board.controller.dto.BoardModifyRequest;
import com.jail.spring.board.domain.BoardVO;
import com.jail.spring.board.service.BoardService;
import com.jail.spring.board.store.BoardStore;

@Service
public class BoardServiceImpl implements BoardService{

	private BoardStore bStore;
	private SqlSession session;
	
	@Autowired
	public BoardServiceImpl(BoardStore bStore, SqlSession session) {
		this.bStore = bStore;
		this.session = session;
	}
	
	@Override
	public List<BoardVO> selectBoardList(int currentPage) {
		// TODO Auto-generated method stub
		List<BoardVO> bList = bStore.selectBoardList(session,currentPage);
		return bList;
	}

	@Override
	public int insertBoard(BoardAddRequest board) {
		int result= bStore.insertBoard(session, board);
		return result;
	}

	//1.detail
	@Override
	public BoardVO selectOneByNo(int boardNo) {
		BoardVO board = bStore.slectOneByNo(session, boardNo);
		return board;
	}
	//2삭제
	@Override
	public int deleteBoard(int boardNo) {
		int result = bStore.deleteBoard(session,boardNo);
		return result;
	}
	//modify (수정)
	@Override
	public int updateBoard(BoardModifyRequest board) {
		int result = bStore.updateBoard(session, board);
		return result;
	}

	//페이징!
	@Override
	public int getTotalCount() {
		int totalCount = bStore.getTotalCount(session);
		return totalCount;
	}

}
