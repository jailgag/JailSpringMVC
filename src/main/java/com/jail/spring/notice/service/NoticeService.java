package com.jail.spring.notice.service;

import java.util.List;
import java.util.Map;

import com.jail.spring.notice.domain.NoticeVO;

public interface NoticeService {
	
	
	//콘트롤러에서 넘어옴!!1번째!
	int insertNotice(NoticeVO notice);
	//리스트!
	List<NoticeVO> selectList(int currentPage);
	
	//검색!2
	int getTotalCount(Map<String, String> paramMap);
	
	NoticeVO selectOneByNo(int noticeNo);
	//파일수정
	int updateNotice(NoticeVO notice);
	//파일삭제?
	
	//검색1.
	int deleteNotice(int noticeNo);
	List<NoticeVO> searchListByKeyword(Map<String, String> paramMap, int currentPage);
	
	//검색2.
	int getTotalCount();

}
