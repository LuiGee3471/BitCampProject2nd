<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="deptList" value="${requestScope.deptList}" />
<jsp:include page="/common/head.jsp" flush="false" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/register.css">
  </head>
  <body>
    <form action="registerCheck" method="POST" id="registerForm">
      <div id="first-div">
      <label for="dept" class="label">부서 선택</label>
        <select name="dept" id="dept">
          <option value="">부서 선택</option>
          <c:forEach var="dept" items="${deptList}" varStatus="status">
            <option value="${status.count}">${dept}</option>
          </c:forEach>
        </select>
      </div>
      
      
      <div id="second-div" class="unseen">
      <label for="id" class="label">로그인 정보 입력</label>
        <div class="id register-input">
          <input type="text" name="id" id="id" placeholder="아이디"/>
          <span class="tip">영문자, 숫자 6자 이상</span>
        </div>
        <div class="password register-input">
          <input
            type="password"
            name="password"
            id="pwd"
            placeholder="비밀번호"
          />
          <span class="tip">4자 이상</span>
        </div>
        <div class="password-check register-input">
          <input
            type="password"
            name="passwordcheck"
            id="pwdcheck"
            placeholder="비밀번호 확인"
          />
          <span class="tip"></span>
        </div>
        <div class="email register-input">
          <input type="text" name="email" id="email" placeholder="이메일" />
          <span class="tip"></span>
        </div>
      </div>
      
      
      <div id="third-div" class="unseen">
      <label for="name" class="label">개인 정보 입력</label>
        <div class="name register-input">
          <input type="text" name="name" id="name" placeholder="이름(실명)" />
          <span class="tip">최대 5자</span>
        </div>
        <div class="birthday register-input">
          <input type="date" name="birthday" id="birthday" placeholder="생일" value="2000-01-01" min="1958-01-01" max="2000-12-31"/>
        </div>
        <div class="phonenumber register-input">
          <input
            type="text"
            name="phoneNumber"
            id="phoneNumber"
            placeholder="전화번호"
            maxlength="11"
          />
          <span class="tip">'-' 없이 숫자만 입력</span>
        </div>
      </div>

      <div id="fourth-div" class="unseen">
        <input type="checkbox" name="agree" id="agree" />
        <label for="agree" class="check-label">유니넷 이용 약관에 동의합니다.</label>
      </div>

      <input type="submit" value="회원가입" class="unseen" id="btn" />
    </form>
    <script src="<%=request.getContextPath()%>/js/register.js"></script>
  </body>
</html>
