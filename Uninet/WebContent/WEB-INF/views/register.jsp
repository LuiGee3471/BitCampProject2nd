<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="deptList" value="${requestScope.deptList}" />
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/register.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
  </head>
  <body>
    <form action="registerCheck" method="POST">
      <div id="first-div">
        <select name="dept" id="dept">
          <option value="">부서 선택</option>
          <c:forEach var="dept" items="${deptList}" varStatus="status">
            <option value="${status.count}">${dept}</option>
          </c:forEach>
        </select>
      </div>
      <div id="second-div" class="unseen">
        <input type="text" name="id" id="id" placeholder="아이디" />
        <input
          type="password"
          name="password"
          id="pwd"
          placeholder="비밀번호"
        />
        <input
          type="password"
          name="passwordcheck"
          id="pwdcheck"
          placeholder="비밀번호 확인"
        />
        <input type="text" name="email" id="email" placeholder="이메일" />
      </div>
      <div id="third-div" class="unseen">
        <input type="text" name="name" id="name" placeholder="이름(실명)" />
        <input type="date" name="birthday" id="birthday" placeholder="생일" />
        <input
          type="text"
          name="phoneNumber"
          id="phoneNumber"
          placeholder="전화번호"
          maxlength="11"
        />
      </div>

      <div id="fourth-div" class="unseen">
        <input type="checkbox" name="agree" id="agree" />
        <label for="agree">유니넷 이용 약관에 동의합니다.</label>
      </div>

      <input type="submit" value="회원가입" class="unseen" id="btn" />
    </form>
    <script src="<%=request.getContextPath()%>/js/register.js"></script>
  </body>
</html>
