<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="message" value="${requestScope.message}" />
<c:set var="userId" value="${sessionScope.staff.id}" />
<div class="message-top">
  <span class="hidden" id="message-id">${message.id}</span>
  <span class="hidden" id="receiver-id">${message.senderName}</span>
  <c:choose>
    <c:when test="${message.receiverId == userId}">
      <h4 class="paper-title">${message.senderName}</h4>
    </c:when>
    <c:otherwise>
      <h4 class="paper-title">${message.receiverName}</h4>
    </c:otherwise>
  </c:choose>
  <div class="message-menu">
    <!-- 답장, 새로고침, 삭제 -->
    <c:if test="${message.receiverId == userId}">
      <span class="hidden">${message.senderId}</span>
      <i class="far fa-paper-plane fa-lg" id="reply"></i>
    </c:if>
    <i class="fas fa-sync-alt fa-lg" id="refresh"></i>
    <c:if test="${message.receiverId == userId}">
      <i class="far fa-trash-alt fa-lg" id="delete"></i>
    </c:if>
  </div>
</div>
<div class="message-info">
  <c:choose>
    <c:when test="${message.receiverId == userId}">
      <span class="receive-send">받은 쪽지</span>
    </c:when>
    <c:otherwise>
      <span class="receive-send">보낸 쪽지</span>
    </c:otherwise>
  </c:choose>
  <span class="time">${message.timeFormat}</span>
</div>
<div class="message-text">
  ${message.content}
</div>
