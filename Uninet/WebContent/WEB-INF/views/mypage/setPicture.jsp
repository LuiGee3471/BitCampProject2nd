<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
   $(function() {
      $("#upload").change(function(e) {
         event.preventDefault();

         var file = e.target.files[0];
         var url = URL.createObjectURL(file);
         $("#previewImage").attr("src", url);
      });
      $('#delbtn').click(function(){
			$('#previewImage').attr("src","<%=request.getContextPath()%>/images/default.png");
		});
   });
</script>
</head>
<body>
	<h3>프로필 이미지 변경 페이지</h3>
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
	<form action="updatePicture" enctype="multipart/form-data" method="post">
		<c:set var="staff" value="${staff }" />
			<span>프로필 이미지 변경</span><br>
			<span>현재 사진</span>
			<img width="100px" height="100px" id="previewImage" src="<%=request.getContextPath()%>/images/${staff.image}"><br>
			<span>변경할 사진</span>
			<input type="file" id="upload" value="" name="image"><br>
			<input type="button" value="삭제" id="delbtn"><input type="submit" value="변경완료">
	</form>
</body>
</html>