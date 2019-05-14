<%@page import="java.time.LocalDate"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="kr.co.groot.dto.Staff"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="staff" value="${requestScope.staff}" />
<c:set var="birthday" value="${requestScope.birthday}" />
<jsp:include page="/common/head.jsp" flush="false" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/mypage.css">
</head>
<body>
  <jsp:include page="/common/top.jsp" flush="false" />
  <div class="container">
    <div class="aside">
      <div class="mypage-title">
        <h3>회원 정보</h3>
      </div>
      <div class="sidebar">
        <ul>
          <li>
            <a href="#">회원 정보</a>
          </li>
        </ul>
      </div>
    </div>
    <div class="article">
      <div class="a-1">
        <h4 class="head">기본 정보</h4>
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
        <span class="data">${birthday}</span>
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