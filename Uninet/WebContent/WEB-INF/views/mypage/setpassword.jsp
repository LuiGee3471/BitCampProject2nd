<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/mypage.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#updatePwd2').keyup(function() {
			if ($('#updatePwd2').val() != $('#updatePwd').val()) {
				$('#check').val('변경할 비밀번호와 일치하지 않습니다.');
				$('#check').css('color', 'red');
			} else {
				$('#check').val('암호가 일치합니다.');
		    	$('#check').css('color','blue');
			}
		});
		$('#currentPwd').keyup(function(){
			if($('#pwd').val() != $('#currentPwd').val()){
				$('#checkPwd').val('현재 비밀번호와 일치하지 않습니다.');
				$('#checkPwd').css('color','red');
			}else{
				$('#checkPwd').val('현재 비밀번호와 일치합니다.');
				$('#checkPwd').css('color','blue');
			}
		});
	});
</script>
</head>
<body>
	<h3>비밀번호 변경 페이지</h3>
	<ul>
		<li><h4>내정보</h4></li>
		<li><a href="<%=request.getContextPath()%>/mypage">기본 정보</a></li>
		<li><a href="<%=request.getContextPath()%>/mypage/setinfo">개인
				정보 수정</a></li>
		<li><a href="<%=request.getContextPath()%>/mypage/setpassword">비밀번호
				변경</a></li>
		<li><a href="<%=request.getContextPath()%>/mypage/setpicture">프로필
				이미지 변경</a></li>
		<li><a href="<%=request.getContextPath()%>/mypage/admin">회원관리</a></li>
	</ul>
	<c:set var="staff" value="${staff }" />
	<form action="updatePwd" method="post">
	<span>비밀번호 변경</span>
	<span>바꿀 비밀번호</span>
	<input type="password" id="updatePwd" value="">
	<br>
	<span>다시 입력</span>
	<input type="password" id="updatePwd2" name="updatePwd" value="">
	<input type="text" id="check" class="check"><br>
	<span>현재 비밀번호</span>
	<input type="password" id="currentPwd" value="">
	<input type="text" id="checkPwd" class="check">
	<input type="hidden" id="pwd" value="${sessionScope.staff.password}"><br>
	<input type="submit" value="비밀번호 변경">
	<br>
	</form>
</body>
</html>