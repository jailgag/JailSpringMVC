package com.jail.spring.member.service;

import com.jail.spring.member.domain.MemberVO;

public interface MemberService {
	
	/**
	 * 회원정보 등록 Service
	 * @param member
	 * @return
	 */
	int insertMember(MemberVO member);
	/**
	 * 회원정보수정 Service
	 * @param member
	 * @return
	 */
	int updateMember(MemberVO member);
	/**
	 * 회원정보삭제 Service
	 * @param member
	 * @return
	 */
	
	int deleteMember(String memberId);
	
	/**
	 * 회원정로그인 Service
	 * @param member
	 * @return
	 */
	
	MemberVO selectOneByLogin(MemberVO member);
	
	/**
	 * 아이디로 검색 Service
	 * @param memberId
	 * @return
	 */
	
	
	MemberVO selectOneById(String memberId);
}
