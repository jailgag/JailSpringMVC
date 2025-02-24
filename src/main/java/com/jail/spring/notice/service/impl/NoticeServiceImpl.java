package com.jail.spring.notice.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jail.spring.notice.domain.NoticeVO;
import com.jail.spring.notice.service.NoticeService;
import com.jail.spring.notice.store.NoticeStore;


@Service
public class NoticeServiceImpl implements NoticeService {
	
	//***여기코드 초기 작성 순서 복습!!
	//오버라이딩해준뒤밑에 코드작성
	private NoticeStore nStore;
	private SqlSession session;
	
	@Autowired
	public NoticeServiceImpl(NoticeStore nStore, SqlSession session) {
		this.nStore = nStore;
		this.session = session;
	}
	//1.
	@Override
	public int insertNotice(NoticeVO notice) {
		// TODO Auto-generated method stub
		int result = nStore.insertNotice(session, notice);
		return result;
	}
	
	public List<NoticeVO> selectList(int currentPage) {
		// TODO Auto-generated method stub
		List<NoticeVO> nList = nStore.selectList(session,currentPage);
		return nList;
	}
	@Override
	public int getTotalCount() {
		// TODO Auto-generated method stub
		int totalCount = nStore.getTotalCount(session);
		return totalCount;
	}
	@Override
	public int getTotalCount(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		int totalCount = nStore.getTotalCount(session, paramMap);
		return totalCount;
	}
	
	@Override
	public NoticeVO selectOneByNo(int noticeNo) {
		// TODO Auto-generated method stub
		NoticeVO notice = nStore.selectOneByNo(session, noticeNo);
		return notice;
	}
	
	@Override
	public int updateNotice(NoticeVO notice) {
		// TODO Auto-generated method stub
		int result = nStore.updateNotice(session, notice);
		return result;
	}
	@Override
	public int deleteNotice(int noticeNo) {
		// TODO Auto-generated method stub
		int result = nStore.deleteNotice(session, noticeNo);
		return result;
	}
	//검색!
	@Override
	public List<NoticeVO> searchListByKeyword(Map<String, String> paramMap , int currentPage) {
		// TODO Auto-generated metho
		List<NoticeVO> searchList= nStore.searchListByKeyword(session,paramMap ,currentPage);
		return searchList;
	}
	
}
