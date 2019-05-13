<%@page import="java.time.LocalDate"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="kr.co.groot.dto.Staff"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	Staff staff = (Staff) session.getAttribute("staff");
	Timestamp birthday = staff.getBirthday();
	LocalDate newBirthday = birthday.toLocalDateTime().toLocalDate();
	String birthdayString = newBirthday.toString();
%>
<jsp:include page="/common/head.jsp" flush="false" />
<script type="text/javascript">
    $(document).ready(function () {
      $('#currentPwd').keyup(function () {
        if ($('#currentPwd').val() != $('#pwd').val()) {
          $('#check').val('암호가 불일치 합니다.');
          $('#check').css('color', 'red');
        } else {
          $('#check').val('암호가 일치합니다.');
          $('#check').css('color', 'blue');
        }
        $('#update').click(function (event) {
          if ($('#currentPwd').val()== ""){
            event.preventDefault();
          }else if ($('#currentPwd').val() != $('#pwd').val()) {
            event.preventDefault();
          }
        });
      });
    });
  </script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/mypage.css">
<script
	src="https://cdn.ckeditor.com/ckeditor5/12.1.0/classic/ckeditor.js"></script>
</head>
<body>
	<c:set var="staff" value="${sessionScope.staff}" />
	<jsp:include page="/common/head.jsp" flush="false" />
	<jsp:include page="/common/top.jsp" flush="false" />
	<div class="container">
		<div class="aside">
			<div class="mypage-title">
				<h3>내 정보</h3>
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
					<c:if test="${staff.isAdmin == 'Y'}">
						<li><a
							href="<%=request.getContextPath()%>/mypage/admin?page=1&option=default">회원
								관리</a></li>
					</c:if>
				</ul>
			</div>
		</div>
		<div class="article">
			<form action="updateInfo" method="post">
				<input type="hidden" id="staff_Id" name="staff_Id"
					value="${sessionScope.staff.staffId}">
				<div class="a-1">
					<h4 class="head">개인정보 변경</h4>
				</div>
				<div class="a-2">
					<span class="label">이름(실명)</span><span class="data"><input
						type="text" name="staffName" class="info"
						value="${sessionScope.staff.staffName}"></span>
				</div>
				<div class="a-3">
					<span class="label">이메일</span><span class="data"><input
						type="text" class="info" name="staffEmail" value="${staff.email }"></span>
				</div>
				<div class="a-4">
					<span class="label">생년월일</span><span class="data"><input
						type="text" class="info" value="<%=birthdayString%>"></span>
				</div>
				<div class="a-5">
					<span class="label">연락처</span><span class="data"><input
						type="text" class="info" name="staffPhone"
						value="${staff.phoneNumber}"></span>
				</div>
				<div class="a-6">
					<span class="label">현재 비밀번호</span> <span class="data"><input
						type="password" id="currentPwd" name="currentPwd" class="info"
						value="" maxlength="20"></span> <input type="text" id="check"
						class="check"> <input type="hidden" id="pwd" name="pwd"
						value="${staff.password}">
				</div>
				<div class="a-7">
					<span class="label self">자기소개</span>
					<textarea name="selfIntroduce" id="editor">${staff.selfIntroduce}</textarea>
				</div>
				<div class="a-8">
					<input type="submit" id="update" class="submit" value="개인정보 변경">
				</div>
			</form>
		</div>
	</div>
	<jsp:include page="/common/bottom.jsp" flush="false" />
	<script>
    ClassicEditor
        .create(document.querySelector('#editor'), {
        	toolbar: ['bold', 'italic', 'link', 'bulletedList', 'numberedList']
        	}
        )
        .catch(error => {
            console.error( error );
        });
  </script>
</body>
</html>