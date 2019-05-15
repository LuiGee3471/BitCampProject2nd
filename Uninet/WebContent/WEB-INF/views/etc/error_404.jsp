<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% response.setStatus(HttpServletResponse.SC_OK); %>
<jsp:include page="/common/head.jsp" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/error.css">
</head>
<body>
<div class="error-form">
  <img src="<%=request.getContextPath()%>/images/logo.png" alt="로고" class="logo">
  <p class="message1">없는 페이지입니다</p>
  <p class="message2">3초 뒤 이전 페이지로 돌아갑니다</p>
  <button class="btn" id="btn">뒤로 가기</button>
</div>
<script>
  $("#btn").click(function() {
	  window.history.back();
  });
  
  setTimeout(function() {
	  window.history.back();
  }, 3000);
</script>
</body>
</html>