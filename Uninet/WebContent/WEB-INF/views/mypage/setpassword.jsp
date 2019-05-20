<%@page import="kr.co.groot.dto.Staff"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
Staff staff = (Staff) session.getAttribute("staff");

%>
<jsp:include page="/common/head.jsp" flush="false" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/mypage.css">
</head>
<body>
  <jsp:include page="/common/top.jsp" flush="false" />
  <div class="container">
	<div class="aside">
	  <div class="mypage-title">
		<h3>내 정보</h3>
	  </div>
	  <div class="sidebar">
			<ul>
				<li><a href="<%=request.getContextPath()%>/mypage">기본 정보</a></li>
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
						<a href="<%=request.getContextPath()%>/admin?page=1&option=default">회원 관리</a>
					</li>
				</c:if>
			</ul>
	  </div>
	</div>
	<c:set var="staff" value="${staff}" />
	<div class="article">
	  <form action="updatePwd" method="post">
	    <div class="a-1">
	      <h4 class="head">비밀번호 변경</h4>
	    </div>
	    <div class="a-2">
	      <span class="label">바꿀 비밀번호</span>
	      <span class="data"><input type="password" class="info" id="updatePwd" value=""></span>
	      <input type="text" id="firstCheck" class="check" size="35" readonly>
	    </div>
	    <div class="a-3">
	      <span class="label">다시 입력</span>
	      <span class="data"><input type="password" id="updatePwd2" class="info" name="updatePwd" value=""></span>
	      <input type="text" id="check" class="check" size="35" readonly>
	    </div>
	    <div class="a-4">
	      <span class="label">현재 비밀번호</span>
	      <span class="data"><input type="password" class="info" id="currentPwd" value=""></span>
	      <input type="text" id="checkPwd" class="check" size="35" readonly>
	      <input type="hidden" id="pwd" value="${staff.password}">
	    </div>
	    <div class="a-3">
	       <input type="submit" id="update" value="비밀번호 변경" class="submit" >
	    </div>
	  </form>
	</div>
  </div>
  <jsp:include page="/common/bottom.jsp" flush="false" />
  <script src="<%=request.getContextPath()%>/js/setpassword.js"></script>
</body>
</html>