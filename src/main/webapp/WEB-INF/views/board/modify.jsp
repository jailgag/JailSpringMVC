<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>자유게시판 - 수정</title>
	</head>
	<body>
		<h1>게시글 수정</h1>
		<fieldset>
			<legend>게시글수정</legend>
			<form action="/board/modify" method="post" enctype="application/x-www-form-urlencoded">
				<input type="hidden" name="boardNo" value="${board.boardNo }">
				<input type="hidden" name="boardWriter" value="${board.boardWriter }">
				제목: <input type="text" name="boardTitle" value="${board.boardTitle }"> <br>
				작성자: <span>${board.boardWriter }</span>
				내용:<textarea rows="4" cols="50" name="boardContent">${board.boardContent}</textarea> <br>
				첨부파일: <span><a href="../..${board.boardFilepath }">${board.boardFilename }</a></span> 
				<input type="file" name="reloadFile"><br>
				<button type="submit">수정</button>
			</form>
		</fieldset>
	</body>
</html>