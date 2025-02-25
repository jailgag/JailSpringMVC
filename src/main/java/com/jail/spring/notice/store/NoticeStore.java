package com.jail.spring.notice.store;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.jail.spring.notice.controller.dto.NoticeAddRequest;
import com.jail.spring.notice.controller.dto.NoticeModifyRequest;
import com.jail.spring.notice.domain.NoticeVO;

public interface NoticeStore {

	//서비스impl에서 넘어옴!!다시감!!
	int insertNotice(SqlSession session, NoticeAddRequest notice);
	//리스트
	
	List<NoticeVO> selectList(SqlSession session, int currentPage);
	
	
	//파일검색2
	int getTotalCount(SqlSession session, Map<String, String> paramMap);
	
	NoticeVO selectOneByNo(SqlSession session, int noticeNo);
	//파일수정
	int updateNotice(SqlSession session, NoticeModifyRequest notice);
	//파일삭제??
	int deleteNotice(SqlSession session, int noticeNo);
	//파일검색1!!
	List<NoticeVO> searchListByKeyword(SqlSession session, Map<String, String> paramMap, int currentPage);
	//검색2
	int getTotalCount(SqlSession session);
	

}
