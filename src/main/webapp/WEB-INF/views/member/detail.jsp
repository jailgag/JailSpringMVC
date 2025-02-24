<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>spring -마이페이지</title>
</head>
<body>
		<h1>마이페이지</h1>
			ID :<span>${member.memberId }</span><br>
			NAME :<span>${member.memberName }</span><br>
			AGE :<span>${member.memberAge }</span><br>
			GENDER :<span>${member.memberGender }</span><br>
			EMAIL :<span>${member.memberEmail }</span><br>
			PHONE :<span>${member.memberPhone }</span><br>
			ADDRESS :<span>${member.memberAddress }</span><br>
			DATE :<span>${member.memberDate }</span><br>
			<!-- a태그를 클릭했을때 아무 동작도 하지않도록하는방법,페이지이동이나 
			새로고침이 되지않음 기본 링크 동작을 막을때 사용함 -->
			<a href="/member/update">수정페이지로 이동</a> <a href="javascript:void(0);" onclick="deleteConfirm();">회원탈퇴</a> <br>
			<a href="/">메인으로이동</a>
			<script>
				function deleteConfirm() {
					var result = confirm("정말로 탈퇴하시겠습니까?");
					if(result) {
						//멤버정보 삭제
						//location.href="/member/delete";
						//아래코드는 페이지뒤로 가기 안되게 설정?어디서?
						
						location.replace("/member/delete");
						//아래코드는 띄우지말것!!그냥알고만있어라!
						//alert("탈퇴완료되었습니다");
					}
				}
			</script>
	</body>
</html>