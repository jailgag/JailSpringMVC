package com.jail.spring.member.store;

//import javax.websocket.Session;

import org.apache.ibatis.session.SqlSession;

import com.jail.spring.member.domain.MemberVO;

public interface MemberStore {
	
	
	/**
	 * 회원 정보등록 Store
	 * @param session
	 * @param member
	 * @return
	 */
	int insertMember(SqlSession session ,MemberVO member);
	/**
	 * 회원 정보수정store
	 * @param session
	 * @param member
	 * @return
	 */
	int updateMember(SqlSession session ,MemberVO member);
	
	/**
	 * 회원 정보 삭제store
	 * @param session
	 * @param member
	 * @return
	 */
	
	int deleteMember(SqlSession session ,String memberId);
	
	/**
	 * 회원로그인 store
	 * @param session
	 * @param member
	 * @return
	 */
	
	MemberVO selectOneByLogin(SqlSession session ,MemberVO member);
	/**
	 * 회원정보 아이디로 조회store
	 * @param session
	 * @param memberId
	 * @return
	 */
	
	MemberVO selectOneById(SqlSession session ,String memberId);
}
