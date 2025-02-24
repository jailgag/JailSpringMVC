package com.jail.spring.member.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jail.spring.member.domain.MemberVO;
import com.jail.spring.member.service.MemberService;
import com.jail.spring.member.store.MemberStore;
import com.jail.spring.member.store.impl.MemberStoreLogic;

@Service
public class MemberServiceImpl implements MemberService{

	//밑에 코드 잘 작성할것!!(기억하기!!)@Autowired
	@Autowired
	private MemberStore mStore;
	@Autowired
	private SqlSession session;
	
	@Override
	public int insertMember(MemberVO member) {
		// TODO Auto-generated method stub
		int result = mStore.insertMember(session, member);
		//아래코드는 바뀜!!
		//MemberStoreLogic mStore = new MemberStoreLogic();
		//다시 지워짐...
		//MemberStore mStore = new MemberStoreLogic();
		return result;
	}

	@Override
	public int updateMember(MemberVO member) {
		// TODO Auto-generated method stub
		int result = mStore.updateMember(session, member);
		return result;
	}

	@Override
	public int deleteMember(String memberId) {
		// TODO Auto-generated method stub
		int result = mStore.deleteMember(session, memberId);
		return result;
	}

	@Override
	public MemberVO selectOneByLogin(MemberVO member) {
		// TODO Auto-generated method stub
		//로그인꺼!!!!!!
		MemberVO result = mStore.selectOneByLogin(session,member);
		return result;
		
	}

	public MemberVO selectOneById(MemberVO member) {
		// TODO Auto-generated method stub
		//member에서 result로 바꿔줌!
		MemberVO result = mStore.selectOneByLogin(session, member);
		return result;
	}

	@Override
	public MemberVO selectOneById(String memberId) {
		// TODO Auto-generated method stub
		MemberVO result = mStore.selectOneById(session, memberId);
		return result;
	}

}
