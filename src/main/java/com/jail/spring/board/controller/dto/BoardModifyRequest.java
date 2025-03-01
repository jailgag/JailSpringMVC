package com.jail.spring.board.controller.dto;

public class BoardModifyRequest {
	
	//boardReqeust랑 비슷하다!no만추가해줌!!
	//toString을 먼저 만들고 한칸띄운다음에
	//거기다가 geter랑seter를 만들어줌!!그러면깔끔하게
	//만들어진다!
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private String boardWriter;
	private String boardFilename;
	private String boardFileRename;
	private String boardFilepath;
	
	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getBoardWriter() {
		return boardWriter;
	}

	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}

	public String getBoardFilename() {
		return boardFilename;
	}

	public void setBoardFilename(String boardFilename) {
		this.boardFilename = boardFilename;
	}

	public String getBoardFileRename() {
		return boardFileRename;
	}

	public void setBoardFileRename(String boardFileRename) {
		this.boardFileRename = boardFileRename;
	}

	public String getBoardFilepath() {
		return boardFilepath;
	}

	public void setBoardFilepath(String boardFilepath) {
		this.boardFilepath = boardFilepath;
	}

	@Override
	public String toString() {
		return "BoardModifyRequest [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardContent="
				+ boardContent + ", boardWriter=" + boardWriter + ", boardFilename=" + boardFilename
				+ ", boardFileRename=" + boardFileRename + ", boardFilepath=" + boardFilepath + "]";
	}
}
