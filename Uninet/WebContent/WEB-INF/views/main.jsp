<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/top-bottom.css">
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
</head>
<body>
  <jsp:include page="/common/top.jsp" flush="false" />
  <div class="container">
    <div class="my-card card">
      <img class="profile" src="https://via.placeholder.com/60" alt="프로필 사진" >
      <h3 class="name">이름</h3>
      <p class="dept">부서</p>
      <div class="buttons">
        <a href="mypage">내 정보</a>
        <a href="logout">로그아웃</a>
      </div>
    </div>
    <div class="mymenu card">
      <a href="mypost"><i class="fas fa-list"></i>내가 쓴 글</a>
      <a href="mycomment" class="mycomment">댓글 단 글</a>
    </div>
  </div>
  <jsp:include page="/common/bottom.jsp" flush="false" />
</body>
</html>