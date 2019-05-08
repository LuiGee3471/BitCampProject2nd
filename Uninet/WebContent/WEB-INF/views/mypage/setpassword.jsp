<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<h3>비밀번호 변경 페이지</h3>
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
		<table class="stafftable" border="1">
				<c:set var="staff" value="${staff }" />
				<tr>
						<th colspan="3"><span>비밀번호 변경</span></th>
				</tr>
				<tr>
						<td><span>바꿀 비밀번호</span></td>
						<td><input type="password" value=""></td>
				</tr>
				<tr>
				<tr>
						<td><span>다시 입력</span></td>
						<td><input type="password" value=""></td>
				</tr>
				<tr>
						<td><span>현재 비밀번호</span></td>
						<td><input type="password" value=""></td>
				</tr>
				<tr>
						<td colspan="2"><input type="submit" value="비밀번호 변경"></td>
				</tr>
		</table>
</body>
</html>