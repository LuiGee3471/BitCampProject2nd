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
<link rel="stylesheet"
  href="<%=request.getContextPath()%>/css/mypage.css">
 <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-lite.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-lite.js"defer></script>
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
						<li>
              <a href="<%=request.getContextPath()%>/admin?page=1&option=default">회원 관리</a>
            </li>
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
				<div class="a-5">
					<span class="label">연락처</span><span class="data"><input
						type="text" class="info" id="staffPhone" name="staffPhone"
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
					<textarea name="selfIntroduce" id="summernote">${staff.selfIntroduce}</textarea>
				</div>
				<div class="a-8">
					<input type="submit" id="update" class="submit" value="개인정보 변경">
				</div>
			</form>
		</div>
	</div>
	<jsp:include page="/common/bottom.jsp" flush="false" />
	<script src="<%=request.getContextPath()%>/js/setInfo.js"></script>
</body>
</html>