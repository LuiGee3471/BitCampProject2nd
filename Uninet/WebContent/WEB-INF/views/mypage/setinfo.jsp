<%@page import="java.time.LocalDate"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="kr.co.groot.dto.Staff"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="staff" value="${sessionScope.staff}" />
<% 
  Staff staff = (Staff) session.getAttribute("staff");
  Timestamp birthday = staff.getBirthday();
  LocalDate newBirthday = birthday.toLocalDateTime().toLocalDate();
  String birthdayString = newBirthday.toString();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <h3>개인정보 수정 페이지</h3>
  <ul>
    <li><h4>내정보</h4></li>
    <li><a href="<%=request.getContextPath()%>/mypage">기본 정보</a></li>
    <li><a href="<%=request.getContextPath()%>/mypage/setinfo">개인 정보 수정</a></li>
    <li>
      <a href="<%=request.getContextPath()%>/mypage/setpassword">비밀번호 변경</a>
    </li>
    <li>
      <a href="<%=request.getContextPath()%>/mypage/setpicture">프로필 이미지 변경</a>
    </li>
    <li><a href="<%=request.getContextPath()%>/mypage/admin">회원관리</a></li>
  </ul>
  <form action="updateInfo?staff_Id=${sessionScope.staff.staffId }" method="get">
  	<input type = "hidden" id = "staff_Id" name ="staff_Id" value = "${sessionScope.staff.staffId}">
      <span class="lable">개인정보 변경</span><br>
      ${sessionScope.staff.staffId }<br>
      <span class="lable">이름(실명)</span>
      <input type="text" id="staffName" name="staffName" value="${staff.staffName }"><br>
      <span class="lable">이메일</span>
      <input type="text" name="staffEmail" value="${staff.email }"><br>
      <span class="lable">생년월일</span>
      <%= birthdayString %><br>
      <span class="lable">연락처</span>
      <input type="text" id="staffPhone" name="staffPhone" value="${staff.phoneNumber }"><br>
      <span class="lable">현재 비밀번호</span>
      <input type="password" id="currentPwd" name="currentPwd" value=""><br>
      <input type="submit" value="개인정보 변경">
  </form>
</body>
</html>