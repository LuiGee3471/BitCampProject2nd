<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/top-bottom.css">
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet"
  href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
</head>
<body>
  <jsp:include page="/common/top.jsp" flush="false" />
  <div class="container">
    <div class="myprofile">
      <div class="my-card card">
        <img class="profile" src="https://via.placeholder.com/60" alt="프로필 사진">
        <h3 class="name">이름</h3>
        <p class="dept">부서</p>
        <div class="buttons">
          <a href="mypage">내 정보</a> <a href="logout">로그아웃</a>
        </div>
      </div>
      <div class="mymenu card">
        <a href="mypost"><i class="fas fa-list-ul"></i>내가 쓴 글</a> <a
          href="mycomment" class="mycomment"><i class="far fa-comment"></i>댓글
          단 글</a>
      </div>
    </div>
    <div class="boards">
      <div class="card board">
        <h3><a href="notice">공지사항</a></h3>
        <a href="list" class="list"><span class="board-title">글 1</span></a>
        <a href="list" class="list"><span class="board-title">글 1</span></a>
        <a href="list" class="list"><span class="board-title">글 1</span></a>
        <a href="#" class="list"><span class="board-title">글 1</span></a>
      </div>
      <div class="card board">
        <h3><a href="board">자유게시판</a></h3>
        <a href="list" class="list"><span class="board-title">글 1</span></a>
        <a href="list" class="list"><span class="board-title">글 1</span></a>
        <a href="list" class="list"><span class="board-title">글 1</span></a>
        <a href="#" class="list"><span class="board-title">글 1</span></a>      </div>
      <div class="card board">
        <h3><a href="message">쪽지</a></h3>
        <a href="message" class="message">
          <h3 class="message-title">쪽지 제목</h3>
          <span class="content">쪽지 내용</span>
        </a>
        <a href="message" class="message">
          <h3 class="message-title">쪽지 제목</h3>
          <span class="content">쪽지 내용</span>
        </a>
      </div>
      <div class="card board">
        <h3><a href="#">인기 게시물</a></h3>
        <a href="list" class="list"><span class="board-title">글 1</span></a>
        <a href="list" class="list"><span class="board-title">글 1</span></a>
        <a href="list" class="list"><span class="board-title">글 1</span></a>
        <a href="#" class="list"><span class="board-title">글 1</span></a>
      </div>
    </div>
  </div>
  <jsp:include page="/common/bottom.jsp" flush="false" />
</body>
</html>
