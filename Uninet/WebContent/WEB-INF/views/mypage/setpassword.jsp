<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/mypage.css">
<jsp:include page="/common/head.jsp" flush="false" />
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
		$('#currentPwd').keyup(function(event){
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
<jsp:include page="/common/top.jsp" flush="false" />
	<div class="container">
	<div class="aside">
	<div class="heading">
		<h3>내정보</h3>
	</div>
	<div class="sidebar">
	<ul>
		<li><a href="<%=request.getContextPath()%>/mypage">기본 정보</a></li>
		<li><a href="<%=request.getContextPath()%>/mypage/setinfo">개인
				정보 수정</a></li>
		<li><a href="<%=request.getContextPath()%>/mypage/setpassword">비밀번호
				변경</a></li>
		<li><a href="<%=request.getContextPath()%>/mypage/setpicture">프로필
				이미지 변경</a></li>
		<li><a href="<%=request.getContextPath()%>/mypage/admin">회원관리</a></li>
	</ul>
	</div>
	</div>
	<c:set var="staff" value="${staff }" />
	<div class="article">
	<form action="updatePwd" method="post">
	<div class="a-1">
	<h4 class="head">비밀번호 변경</h4>
	</div>
	<div class="a-2">
	<span>바꿀 비밀번호</span>&nbsp;&nbsp;&nbsp;
	 <input type="password" id="updatePwd" value="">
	</div>
	<div class="a-3">
	<span>다시 입력</span>&nbsp;&nbsp;&nbsp;
	<input type="password" id="updatePwd2" name="updatePwd" value="">
	<input type="text" id="check" class="check">
	</div>
	<div class="a-4">
	<span>현재 비밀번호</span>&nbsp;&nbsp;&nbsp;
	<input type="password" id="currentPwd" value="">
	<input type="text" id="checkPwd" class="check">
	<input type="hidden" id="pwd" value="${sessionScope.staff.password}">
	</div>
	<div class="a-3">
	<input type="submit" value="비밀번호 변경">
	</div>
	</form>
	</div>
	</div>
	  <jsp:include page="/common/bottom.jsp" flush="false" />
</body>
</html>