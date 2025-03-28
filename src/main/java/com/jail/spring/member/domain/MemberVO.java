package com.jail.spring.member.domain;

import java.sql.Date;

public class MemberVO {
	private String memberId;
	private String memberPw;
	private String memberName;
	private int memberAge;
	private String memberGender;
	private String memberEmail;
	private String memberPhone;
	private String memberAddress;
	private Date memberDate;
	private Date modifyDate;
	private String memberYn;
	
	
	
	public MemberVO() {
		super();
		}
	
	
	
	
	public MemberVO(String memberId, String memberPw, String memberEmail, String memberPhone, String memberAddress) {
		super();
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberEmail = memberEmail;
		this.memberPhone = memberPhone;
		this.memberAddress = memberAddress;
	}




	//로그인!!
	public MemberVO(String memberId, String memberPw) {
		super();
		this.memberId = memberId;
		this.memberPw = memberPw;
	}



	//insert꺼!!
	public MemberVO(String memberId, String memberPw, String memberName, int memberAge, String memberGender,
			String memberEmail, String memberPhone, String memberAddress) {
		super();
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberName = memberName;
		this.memberAge = memberAge;
		this.memberGender = memberGender;
		this.memberEmail = memberEmail;
		this.memberPhone = memberPhone;
		this.memberAddress = memberAddress;
	}
	public String getMemberId() {
		return memberId;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public String getMemberName() {
		return memberName;
	}
	public int getMemberAge() {
		return memberAge;
	}
	public String getMemberGender() {
		return memberGender;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public String getMemberPhone() {
		return memberPhone;
	}
	public String getMemberAddress() {
		return memberAddress;
	}
	public Date getMemberDate() {
		return memberDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public String getMemberYn() {
		return memberYn;
	}
	@Override
	public String toString() {
		return "MemberVO [memberId=" + memberId + ", memberPw=" + memberPw + ", memberName=" + memberName
				+ ", memberAge=" + memberAge + ", memberGender=" + memberGender + ", memberEmail=" + memberEmail
				+ ", memberPhone=" + memberPhone + ", memberAddress=" + memberAddress + ", memberDate=" + memberDate
				+ ", modifyDate=" + modifyDate + ", memberYn=" + memberYn + "]";
	}
	
	
}
