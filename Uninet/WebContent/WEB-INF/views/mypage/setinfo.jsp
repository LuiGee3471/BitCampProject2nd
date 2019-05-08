<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="staff" value="${sessionScope.staff}" />
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
  <table class="stafftable" border="1">
    
    <tr>
      <th colspan="3"><span>개인정보 변경</span></th>
    </tr>
    <tr>
      <td><span>이름(실명)</span></td>
      <td><input type="text" value="${staff.staffName }"></td>
    </tr>
    <tr>
    <tr>
      <td><span>이메일</span></td>
      <td><input type="text" value="${staff.email }"></td>
    </tr>
    <tr>
      <td><span>생년월일</span></td>
      <td>${staff.birthday }</td>
    </tr>
    <tr>
      <td><span>연락처</span></td>
      <td><input type="text" value="${staff.phoneNumber }"></td>
    </tr>
    <tr>
      <td><span>현재 비밀번호</span></td>
      <td><input type="password" value=""></td>
    </tr>
    <tr>
      <td colspan="2"><input type="submit" value="개인정보 변경"></td>
    </tr>
  </table>
</body>
</html>