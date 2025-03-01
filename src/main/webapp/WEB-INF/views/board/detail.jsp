<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 - 상세</title>
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
	<br>
		<div>
			<button type="button" onclick="showModifyForm(${board.boardNo});">수정하기</button>
			<button type="button" onclick="deleteConfirm(${board.boardNo})">삭제하기</button>
			<button type="button" id="listBtn">목록으로</button>
			<button type="button" onclick="goBack();">뒤로가기</button>
		</div>
		<script>
 			function showModifyForm(boardNo) {
//				location.href="/board/modify/"+boardNo;	
					alert("modify");
 				}
			function deleteConfirm(boardNo) {
				var result = confirm("정말로 삭제하시겠습니까?");
				if(result) {
					location.href="/board/delete/"+boardNo;
				}
			}
			document.querySelector("#listBtn")
			.addEventListener("click",function)() {
			location.href="/board/list";	
			});
			function goBack() {
				history.go(-1);
			}
		</script>
	</body>
</html>