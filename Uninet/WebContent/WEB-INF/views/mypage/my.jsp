<%@page import="java.time.LocalDate"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="kr.co.groot.dto.Staff"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% 
  Staff staff = (Staff) session.getAttribute("staff");
  Timestamp birthday = staff.getBirthday();
  LocalDate newBirthday = birthday.toLocalDateTime().toLocalDate();
  String birthdayString = newBirthday.toString();
%>
<c:set var="staff" value="${sessionScope.staff}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
	<h3>MyPage</h3>
	<ul>
	  <li><h4>내정보</h4></li>
	  <li><a href="<%=request.getContextPath()%>/mypage">기본 정보</a></li>
	  <li><a href="<%=request.getContextPath()%>/mypage/setinfo">개인 정보 수정</a></li>
	  <li><a href="<%=request.getContextPath()%>/mypage/setpassword">비밀번호 변경</a></li>
	  <li><a href="<%=request.getContextPath()%>/mypage/setpicture">프로필 이미지 변경</a></li>
	  <li><a href="<%=request.getContextPath()%>/mypage/admin">회원관리</a></li>
	</ul>
	<table class="stafftable" border="1">
	 
	<tr>
	<th><span>기본 정보</span></th><th><span><a href="logout">로그아웃</a></span></th>
	</tr>
	<tr>
	<td><span>이름</span></td><td>${staff.staffName}</td>
	</tr>
	<tr>
	<tr>
	<td><span>사진</span></td><td><img alt="" src="<%=request.getContextPath()%>/images/${staff.image}"></td>
	</tr>
	<tr>
	<td><span>아이디</span></td><td>${staff.staffId}</td>
	</tr>
	<tr>
	<td><span>이메일</span></td><td>${staff.email}</td>
	</tr>
	<tr>
	<td><span>생년월일</span></td><td><%=birthdayString%></td>
	</tr>
	<tr>
	<td><span>연락처</span></td><td>${staff.phoneNumber}</td>
	</tr>
	
	</table>
</body>
</html>