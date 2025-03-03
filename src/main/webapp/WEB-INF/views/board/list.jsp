<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판-리스트</title>
		<style>
			table {
			border: 1px solid black;
			border-collapse: collapse;
			}
			th, td {
				border: 1px solid rgb(110, 102, 102);
			}
		</style>
</head>
<body>
		<h1>자유게시판</h1>
	<table>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일자</th>
			<th>첨부파일</th>
		</tr>
			
			<!-- <c:if test="${empty bList }">
				없어요
			</c:if>
			<c:if test="${not empty bList }">
				있어요
			</c:if> -->
			<c:choose>
				<c:when test="${empty bList }">
					<tr>
						<td colspan="5" align="center">데이터가 존재하지않습니다</td>
					</tr>
				</c:when>
			<c:otherwise>
				<c:forEach items="${bList }" var="board" varStatus="i">
					<tr>
						<td>${board.boardNo }</td>
						<td><a href="/board/detail/${board.boardNo}">${board.boardTitle }</a></td>
						<td>${board.boardWriter }</td>
						<td>${board.boardDate }</td>
						<td>${board.boardFilename }</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		<tr>
			<!-- colspand이랑 작성하면 가운데로! -->
			<td colspan="4" align="center">
				<a href="/board/list?page=1">1</a>
				<a href="/board/list?page=2">2</a>
				<a href="/board/list?page=3">3</a>
				<a href="/board/list?page=4">4</a>
				<a href="/board/list?page=5">5</a>
			</td>
			<td>
				<button type="button">글쓰기</button>
			</td>	
		</tr>
	</table>
</body>
</html>