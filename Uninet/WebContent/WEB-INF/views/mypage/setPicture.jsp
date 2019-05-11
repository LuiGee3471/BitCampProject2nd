<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/mypage.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<jsp:include page="/common/head.jsp" flush="false" />
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
<jsp:include page="/common/head.jsp" flush="false" />
	<div class="container">
	<div class="aside">
	<div class="heading">
	<h3>내정보</h3>
	</div>
	<div class="sidebar">
	<ul>
          <li>
            <a href="<%=request.getContextPath()%>/mypage">기본 정보</a>
          </li>
          <li>
            <a href="<%=request.getContextPath()%>/mypage/setinfo">개인 정보 수정</a>
          </li>
          <li>
            <a href="<%=request.getContextPath()%>/mypage/setpassword">비밀번호 변경</a>
          </li>
          <li>
            <a href="<%=request.getContextPath()%>/mypage/setpicture">프로필 이미지 변경</a>
          </li>
          <c:if test="${staff.isAdmin == 'Y'}">
            <li>
              <a href="<%=request.getContextPath()%>/mypage/admin?page=1&option=default">회원 관리</a>
            </li>
          </c:if>
        </ul>
	</div>
	</div>
	<div class="article">
	<form action="updatePicture" enctype="multipart/form-data" method="post">
		<c:set var="staff" value="${staff }" />
		<div class="a-1">
		
		  <h4 class="head">프로필 이미지 변경</h4>
		</div>
			<div class="a-2">
			<span class="lable">현재 사진</span>
			<img width="100px" height="100px" id="previewImage" src="<%=request.getContextPath()%>/images/${staff.image}"><br>
			</div>
			<div class="a-3">
			<span class="lable">변경할 사진</span>
			<input type="file" id="upload" value="" name="image"><br>
			</div>
			<div class="a-4">
			<input type="button" value="삭제" id="delbtn"><input type="submit" value="변경완료">
			</div>
	</form>
	</div>
	</div>
	  <jsp:include page="/common/bottom.jsp" flush="false" />
</body>
</html>