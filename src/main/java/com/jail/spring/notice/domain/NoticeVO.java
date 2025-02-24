package com.jail.spring.notice.domain;

import java.sql.Timestamp;

//import java.security.Timestamp;

import org.springframework.web.multipart.MultipartFile;

public class NoticeVO {
	private int noticeNo;
	private String noticeSubject;
	private String noticeContent;
	private String noticeWriter;
	private String noticeFilename;
	private String noticeFileRename;
	private String noticeFilepath;
	private Timestamp noticeDate;
	private Timestamp updateDate;
	private int noticeCount;
	private String noticeYn;
	private MultipartFile UploardFile;
	
	
	public NoticeVO() {
		super();
	}
	
	
	//파일수정6개다시만듬!!밑에 주석처리한건 확인후 지워주기!!
	public NoticeVO(int noticeNo, String noticeSubject, String noticeContent, String noticeFilename,
			String noticeFileRename, String noticeFilepath) {
		super();
		this.noticeNo = noticeNo;
		this.noticeSubject = noticeSubject;
		this.noticeContent = noticeContent;
		this.noticeFilename = noticeFilename;
		this.noticeFileRename = noticeFileRename;
		this.noticeFilepath = noticeFilepath;
	}



	//6개
	//public NoticeVO(int noticeNo, String noticeSubject, String noticeContent, String noticeFileRename,
		//	String noticeFilepath, Timestamp noticeDate) {
		//super();
		//this.noticeNo = noticeNo;
		//this.noticeSubject = noticeSubject;
		//this.noticeContent = noticeContent;
		//this.noticeFileRename = noticeFileRename;
		//this.noticeFilepath = noticeFilepath;
		//this.noticeDate = noticeDate;
//	}




	//파일업로드관련추가5개!!
	public NoticeVO(String noticeSubject, String noticeContent, String noticeWriter, String noticeFilename,
			String noticeFileRename, String noticeFilepath) {
		super();
		this.noticeSubject = noticeSubject;
		this.noticeContent = noticeContent;
		this.noticeWriter = noticeWriter;
		this.noticeFilename = noticeFilename;
		this.noticeFileRename = noticeFileRename;
		this.noticeFilepath = noticeFilepath;
	}



	//공지사항 등록첫번째3개!!
	public NoticeVO(String noticeSubject, String noticeContent, String noticeWriter) {
		super();
		this.noticeSubject = noticeSubject;
		this.noticeContent = noticeContent;
		this.noticeWriter = noticeWriter;
	}



	public int getNoticeNo() {
		return noticeNo;
	}
	public String getNoticeSubject() {
		return noticeSubject;
	}
	public String getNoticeContent() {
		return noticeContent;
	}
	public String getNoticeWriter() {
		return noticeWriter;
	}
	public String getNoticeFilename() {
		return noticeFilename;
	}
	public String getNoticeFileRename() {
		return noticeFileRename;
	}
	public String getNoticeFilepath() {
		return noticeFilepath;
	}
	public Timestamp getNoticeDate() {
		return noticeDate;
	}
	public Timestamp getUpdateDate() {
		return updateDate;
	}
	public int getNoticeCount() {
		return noticeCount;
	}
	public String getNoticeYn() {
		return noticeYn;
	}
	public MultipartFile getUploardFile() {
		return UploardFile;
	}
	@Override
	public String toString() {
		return "NoticeVO [noticeNo=" + noticeNo + ", noticeSubject=" + noticeSubject + ", noticeContent="
				+ noticeContent + ", noticeWriter=" + noticeWriter + ", noticeFilename=" + noticeFilename
				+ ", noticeFileRename=" + noticeFileRename + ", noticeFilepath=" + noticeFilepath + ", noticeDate="
				+ noticeDate + ", updateDate=" + updateDate + ", noticeCount=" + noticeCount + ", noticeYn=" + noticeYn
				+ ", UploardFile=" + UploardFile + "]";
	}
	
	
	
}
