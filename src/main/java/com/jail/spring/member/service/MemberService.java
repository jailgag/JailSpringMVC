package com.jail.spring.member.service;

import com.jail.spring.member.controller.dto.JoinRequest;
import com.jail.spring.member.controller.dto.LoginRequest;
import com.jail.spring.member.controller.dto.ModifyRequest;
import com.jail.spring.member.domain.MemberVO;

public interface MemberService {
	
	/**
	 * 회원정보 등록 Service
	 * @param member
	 * @return
	 */
	int insertMember(JoinRequest member);
	/**
	 * 회원정보수정 Service
	 * @param member
	 * @return
	 */
	int updateMember(ModifyRequest member);
	/**
	 * 회원정보삭제 Service
	 * @param member
	 * @return
	 */
	
	int deleteMember(String memberId);
	
	/**
	 * 회원로그인 Service
	 * @param member
	 * @return
	 */
	
	MemberVO selectOneByLogin(LoginRequest member);
	
	/**
	 * 아이디로 검색 Service
	 * @param memberId
	 * @return
	 */
	
	
	MemberVO selectOneById(String memberId);
}
