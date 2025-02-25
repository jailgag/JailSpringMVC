package com.jail.spring.notice.store.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.jail.spring.notice.controller.dto.NoticeAddRequest;
import com.jail.spring.notice.controller.dto.NoticeModifyRequest;
import com.jail.spring.notice.domain.NoticeVO;
import com.jail.spring.notice.store.NoticeStore;

@Repository
public class NoticeStoreLogic implements NoticeStore {

	
	@Override
	public int insertNotice(SqlSession session, NoticeAddRequest notice) {
		// TODO Auto-generated method stub
		int result = session.insert("NoticeMapper.insertNotice",notice);
		return result;
	}

	public List<NoticeVO> selectList(SqlSession session,int currentPage) {
		// TODO Auto-generated method stub
		int limit = 10;
		int offset= (currentPage-1)*limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<NoticeVO> nList = session.selectList("NoticeMapper.selectList",null,rowBounds);
		return nList;
	}

	@Override
	public int getTotalCount(SqlSession session) {
		// TODO Auto-generated method stub
		int totalCount = session.selectOne("NoticeMapper.getTotalCount");
		return totalCount;
	}

	@Override
	public int getTotalCount(SqlSession session, Map<String, String> paramMap) {
		int totalCount = session.selectOne("NoticeMapper.getTotalCountBySearch", paramMap);
		return totalCount;
	}

	//검색!!
	@Override
	public List<NoticeVO> searchListByKeyword(SqlSession session, Map<String, String> paramMap, int currentPage) {
		// TODO Auto-generated method stu
		int limit = 10;
		int offset =(currentPage-1)*limit;
		RowBounds rowBounds = new RowBounds(offset,limit);
		List<NoticeVO> searchList = session.selectList("NoticeMapper.searchListByKeyword",paramMap, rowBounds);
		return searchList;
	}

	@Override
	public NoticeVO selectOneByNo(SqlSession session, int noticeNo) {
		// TODO Auto-generated method stub
		NoticeVO notice = session.selectOne("NoticeMapper.selectOneByNo",noticeNo);
		return notice;
	}

	@Override
	public int updateNotice(SqlSession session, NoticeModifyRequest notice) {
		// TODO Auto-generated method stub
		int result = session.update("NoticeMapper.updateNotice",notice);
		return result;
	}

	@Override
	public int deleteNotice(SqlSession session, int noticeNo) {
		// TODO Auto-generated method stub
		int result = session.update("NoticeMapper.deleteNotice",noticeNo);
		return result;
	}

	
}
