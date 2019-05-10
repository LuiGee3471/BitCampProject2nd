<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/top-bottom.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css" integrity="sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay" crossorigin="anonymous">
<title>Insert title here</title>
<link rel="stylesheet" href="css/message.css">
</head>
<body>
  <jsp:include page="/common/top.jsp" flush="false" />
  <div class="container">
    <div class="message-box">
      <h4 class="message-title">쪽지함</h4>
      <!-- 쪽지 있는 만큼 담기 -->
      <div class="message">
        <div class="message-sender">
          <span class="sender">보낸 사람</span>
          <span class="time">05/06 14:55</span>
        </div>
        <p class="message-preview">아아 이것은 쪽지라는 것이다 바로 쪽지지 쪽지란 쪽지인 것이다 알겠는가 쪽지란 쪽지인 것이다 알겠느냐 쪽지다 쪽지란 말이다</p>
      </div>
    </div>
    <div class="message-paper">
      <div class="message-top">
        <h4 class="paper-title">
          보낸 사람
        </h4>
        <div class="message-menu">
          <!-- 답장, 새로고침, 삭제 -->
          <i class="far fa-paper-plane fa-lg"></i>
          <i class="fas fa-sync-alt fa-lg"></i>
          <i class="far fa-trash-alt fa-lg"></i>
        </div>
      </div>
      <div class="message-info">
        <!-- 받은 쪽지 or 보낸 쪽지, 날짜 -->
        <span class="receive-send">받은 쪽지</span>
        <span class="time">05/06 12:35</span>
      </div>
      <div class="message-text">
         아아 이것은 쪽지라는 것이다 바로 쪽지지 쪽지란 쪽지인 것이다 알겠는가 쪽지란 쪽지인 것이다 알겠느냐 쪽지다 쪽지란 말이다
      </div>
    </div>
  </div>
  <jsp:include page="/common/bottom.jsp" flush="false" />
</body>
</html>