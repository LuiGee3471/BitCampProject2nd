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
<c:set var="staff" value="${sessionScope.staff}" />
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
      <div class="a-1">
        <h4 class="head">기본 정보</h4>
        <a class="logout" href="logout">로그아웃</a>
      </div>
      <div class="a-2">
       <span class="label">이름</span> 
       <span class="data">${staff.staffName}</span> 
      </div>
      <div class="a-3">
        <span class="label">사진</span>
        <img class="photo" alt="프로필 사진"
         src="<%=request.getContextPath()%>/images/${staff.image}">
      </div>
      <div class="a-4">
        <span class="label">아이디</span>
        <span class="data">${staff.staffId}</span>
      </div>
      <div class="a-5">
        <span class="label">이메일</span>
        <span class="data">${staff.email}</span>
      </div>
      <div class="a-6">
        <span class="label">생년월일</span> 
        <span class="data"><%=birthdayString%></span>
      </div>
      <div class="a-7">
        <span class="label">부서</span>
        <span class="data">${staff.deptName}</span>
      </div>
      <div class="a-8">
        <span class="label">연락처</span>
        <span class="data">${staff.phoneNumber}</span>
      </div>
      <div class="a-9">
        <span class="label">자기소개</span>
        <div class="self-introduce-text">${staff.selfIntroduce}</div>
      </div>
    </div>
  </div>
  <jsp:include page="/common/bottom.jsp" flush="false" />
</body>
</html>