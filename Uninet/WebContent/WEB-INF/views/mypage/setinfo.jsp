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
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/mypage.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$('#currentPwd').keyup(function(){
    if($('#currentPwd').val() != $('#pwd').val()){
      $('#check').val('암호가 불일치 합니다.');
      $('#check').css('color','red');
    }else{
    	$('#check').val('암호가 일치합니다.');
    	$('#check').css('color','blue');
    }
    $('#update').click(function(event){
      if($('#currentPwd').val() != $('#pwd').val()){
          event.preventDefault();
      }
    });
  });
});
</script>
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
  <form action="updateInfo" method="post">
  	<input type = "hidden" id = "staff_Id" name ="staff_Id" value = "${sessionScope.staff.staffId}">
      <span class="lable">개인정보 변경</span><br>
      <span class="lable">이름(실명)</span>
      <input type="text" id="staffName" name="staffName" value="${sessionScope.staff.staffName }"><br>
      <span class="lable">이메일</span>
      <input type="text" name="staffEmail" value="${staff.email }"><br>
      <span class="lable">생년월일</span>
      <input type="text" id="birthday" name="birthday" value="<%= birthdayString %>"><br>
      <span class="lable">연락처</span>
      <input type="text" id="staffPhone" name="staffPhone" value="${staff.phoneNumber }"><br>
      <span class="lable">현재 비밀번호</span>
      <input type="password" id="currentPwd" name="currentPwd" value="">
      <input type="text" id="check" class="check">
      <input type="hidden" id="pwd" name="pwd" value="${sessionScope.staff.password}"><br>

      <input type="submit" id="update" value="개인정보 변경">
  </form>
</body>
</html>