<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% session.getAttribute("staff"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원관리</title>
</head>
<body>
	<h3>내정보</h3>
	<ul>
	  <li><h4>내정보</h4></li>
	  <li><a href="<%=request.getContextPath()%>/mypage">기본 정보</a></li>
	  <li><a href="<%=request.getContextPath()%>/mypage/setinfo">개인 정보 수정</a></li>
	  <li><a href="<%=request.getContextPath()%>/mypage/setpassword">비밀번호 변경</a></li>
	  <li><a href="<%=request.getContextPath()%>/mypage/setpicture">프로필 이미지 변경</a></li>
	  <li><a href="<%=request.getContextPath()%>/mypage/admin">회원관리</a></li>
	</ul>
	
	<table>
	<tr>
		<th>이름</th><th>아이디</th><th>email</th><th>phone</th><th>생일
	</tr>
	<c:forEach var ="staffList" items="${requestScope.staffList }"></c:forEach>
	<tr>
		<td>${staffList.staffId }</td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
	</tr>
	</table>
</body>
</html>