<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="messageList" value="${requestScope.messageList}" />
<c:set var="userId" value="${sessionScope.staff.id}" />  
<jsp:include page="/common/head.jsp" flush="false" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/modal.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/message.css">
</head>
<body>
  <jsp:include page="/common/top.jsp" flush="false" />
  <div class="container">
    <div class="message-box">
      <h4 class="message-title">쪽지함</h4>
      <!-- 쪽지 있는 만큼 담기 -->
      <c:forEach var="message" items="${messageList}">
        <div class="message">
          <span class="hidden">${message.id}</span>
          <div class="message-sender">     
          <c:choose>
            <c:when test="${message.receiverId == userId}">
              <span class="hidden">${message.senderId}</span>
              <span class="sender">${message.senderName}</span>
            </c:when>
            <c:otherwise>
              <span class="sender">${message.receiverName}</span>
            </c:otherwise>
        </c:choose>
            <span class="time">${message.timeFormat}</span>
          </div>
          <p class="message-preview">${message.content}</p>  
        </div>
      </c:forEach>
    </div>
    <div class="message-paper"></div>
  </div>

  <div class="modal">
    <div class="modal-content">
      <div class="message-modal">
        <form action="<%=request.getContextPath()%>/message/send"
          class="message-form" method="post">
          <h3 class="message-title">쪽지 보내기</h3>
          <textarea class="message-textarea" name="text"
            placeholder="내용을 입력해주세요."></textarea>
          <input type="hidden" value="${postId}" name="postId">
          <input type="hidden" value="message" name="origin">
          <input type="submit" value="전송" class="message-submit">
        </form>
        <a class="close-btn">&times;</a>
      </div>
    </div>
  </div>
  <jsp:include page="/common/bottom.jsp" flush="false" />
  <script>
    const fromMain = ${requestScope.fromMain};
    const messageId = ${requestScope.id};
  </script>
  <script src="<%=request.getContextPath()%>/js/message.js"></script>
</body>
</html>