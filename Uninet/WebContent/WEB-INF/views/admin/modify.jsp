<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 수정 페이지</title>
</head>
<c:set var="staff" value="${requestScope.staff }" />
<%
	request.getParameter("id");
%>
<jsp:include page="/common/head.jsp" flush="false" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/update.css">
<body>
	<jsp:include page="/common/top.jsp" flush="false" />
	<div class="container">
		<form action="modifyOk" method="post">
			<span class="label">아이디 </span><input type="text" id="id" name="id"
				value="${staff.staffId}" readOnly><br> <span>이름
			</span><input type="text" id="name" name="name" value="${staff.staffName}"
				readOnly><br> <span>이메일</span> <input type="email"
				id="email" name="email" value="${staff.email}" readOnly><br>
			<span>핸드폰 번호</span> <input type="text" id="phoneNumber"
				name="phoneNumber" value="${staff.phoneNumber}"><br>
			<c:if test="${staff.isAdmin } eq 'Y' "></c:if>
			<span>사이트 관리자 </span> <input type="text" id="isAdmin" name="isAdmin"
				value="${staff.isAdmin }"><br> <span>부서 관리자 </span>  <input
				type="text" id="isManager" name="isManager"
				value="${staff.isManager }"><br> 
			<span>부서이름</span>  <input type=text id="deptName" name="deptName"
				value="${staff.deptName }"><br> <input type="submit"
				value="수정" class="updateBtn"> 
				<a class="cancle-back" href="admin?page=1&option=default">취소하고 돌아가기</a> 	
		</form>
	</div>
	<jsp:include page="/common/bottom.jsp" flush="false" />
</body>
</html>