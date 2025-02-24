<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
		<title>공지사항 - 상세</title>
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
	<h1>공지사항 - 상세</h1>
	<table>
		<tr>
			<th>제목</th>
			<th>${notice.noticeSubject  }</th>
		</tr>
		<tr>
			<th>작성자</th>
			<th>${notice.noticeWriter }</th>
		</tr>
		<tr>
			<th>내용</th>
			<th>${notice.noticeContent }</th>
		</tr>
		<tr>
			<th>첨부파일</th>
			<th><a href="..${notice.noticeFilepath }" download>
			${notice.noticeFilename }
			</a>
			</th>
		</tr>
	</table>
	<br>
	<div>
		<button type="button" onclick="showModifyForm(${notice.noticeNo});">수정하기</button>
		<button type="button" onclick="deleteConfirm(${notice.noticeNo})">삭제하기</button>
		<button type="button" id="listBtn">목록으로</button>
		<button type="button" onclick="goBack();">뒤로가기</button>
	</div>
	<script>
	function deleteConfirm(noticeNo) {
		var check = confirm("정말로 삭제하시겠습니까?");
		if(check) {
			location.href="/notice/delete?noticeNo="+noticeNo;
		}
	}
	
	function showModifyForm(noticeNo) {
		location.href = "/notice/update?noticeNo="+noticeNo;
	}	
	
		document.querySelector("#listBtn")
		.addEventListener("click", function() {
			location.href ="/notice/list";
		});
		
		function goBack() {
			history.go(-1);
		}
	</script>
</body>
</html>