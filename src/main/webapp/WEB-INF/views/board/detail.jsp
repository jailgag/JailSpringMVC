<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 - 상세</title>
</head>
<body>
	<h1>게시글 - 상세</h1>
	<table>
		<tr>
			<th>제목</th>
			<th>${board.boardTitle  }</th>
		</tr>
		<tr>
			<th>작성자</th>
			<th>${board.boardWriter }</th>
		</tr>
		<tr>
			<th>내용</th>
			<th>${board.boardContent }</th>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>
			<a href="../..${board.boardFilepath }" download>
			${board.boardFilename }
			</a>
			</td>
		</tr>
	</table>
</body>
</html>