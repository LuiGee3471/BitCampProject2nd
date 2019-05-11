<%@page import="java.time.temporal.Temporal"%>
<%@page import="java.time.temporal.ChronoUnit"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="kr.co.groot.dto.Message"%>
<%@page import="kr.co.groot.dao.MessageDao"%>
<%@page import="kr.co.groot.dto.Post"%>
<%@page import="java.util.List"%>
<%@page import="kr.co.groot.dao.PostDao"%>
<%@page import="kr.co.groot.dto.Staff"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%
    Staff staff = (Staff) session.getAttribute("staff");
    PostDao postDao = new PostDao();
    List<Post> recentNotice = postDao.selectRecentNotice();
    List<Post> recentPost = postDao.selectRecentPost();
    List<Post> popularList = postDao.selectByCountForMain();
    MessageDao messageDao = new MessageDao();
    List<Message> messageList = messageDao.selectRecentMessage(staff.getId());
  %>
<jsp:include page="/common/head.jsp" flush="false" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
</head>
<body>
  <jsp:include page="/common/top.jsp" flush="false" />
  <div class="container">
    <div class="myprofile">
      <div class="my-card card">
        <img class="profile" src="images/${staff.image}" alt="프로필 사진">
        <h3 class="name">${staff.staffName}</h3>
        <p class="dept">${staff.deptName}</p>
        <div class="buttons">
          <a href="mypage">내 정보</a><a href="logout">로그아웃</a>
        </div>
      </div>
      <div class="mymenu card">
        <a href="mypost"><i class="fas fa-list-ul"></i>내가 쓴 글</a> 
        <a href="mycomment" class="mycomment">
          <i class="far fa-comment"></i>댓글단 글
        </a>
      </div>
    </div>
    <div class="boards">
      <div class="card board">
        <h3><a href="board/list?page=1&option=default&boardtype=1">공지사항</a></h3>
        <c:forEach var="notice" items="<%=recentNotice%>">
          <a href="board/read?id=${notice.id}" class="list"><span class="board-title">${notice.title}</span>
          <c:choose>
            <c:when test="${notice.diff < 2}">
              <span class="time">방금</span></a>
            </c:when>
            <c:when test="${notice.diff < 60}">
              <span class="time">${notice.diff}분 전</span></a>
            </c:when>
            <c:otherwise>
              <span class="time">${notice.timeFormat}</span></a>
            </c:otherwise>
          </c:choose>
        </c:forEach>
      </div>
      <div class="card board">
        <h3><a href="board/list?page=1&option=default&boardtype=2">자유게시판</a></h3>     
        <c:forEach var="post" items="<%=recentPost%>">
        <a href="board/read?id=${post.id}" class="list"><span class="board-title">${post.title}</span>
          <c:choose>
            <c:when test="${post.diff < 2}">
              <span class="time">방금</span></a>
            </c:when>
            <c:when test="${post.diff < 60}">
              <span class="time">${post.diff}분 전</span></a>
            </c:when>
            <c:otherwise>
              <span class="time">${post.timeFormat}</span></a>
            </c:otherwise>
          </c:choose>
        </c:forEach>    
      </div>
      <div class="card board">
        <h3><a href="message">쪽지</a></h3>
        <c:forEach var="message" items="<%=messageList%>">
          <a href="message?fromMain=true&id=${message.id}" class="message">
            <h3 class="message-title">${message.senderName}</h3>
            <span class="content">${message.content}</span><br>
            <span class="time message-time">${message.timeFormat}</span>
          </a>
        </c:forEach>
      </div>
      <div class="card board">
        <h3><a href="#">인기 게시물</a></h3>
        <c:forEach var="post" items="<%=popularList%>">
          <c:choose>
            <c:when test="${post.boardType == 1}">
              <a href="notice/read?id=${post.id}" class="list"><span class="board-title">${post.title}</span>
            </c:when>
            <c:otherwise>
              <a href="board/read?id=${post.id}" class="list"><span class="board-title">${post.title}</span>
            </c:otherwise>
          </c:choose>
          <c:choose>
            <c:when test="${post.diff < 2}">
              <span class="time">방금</span></a>
            </c:when>
            <c:when test="${post.diff < 60}">
              <span class="time">${post.diff}분 전</span></a>
            </c:when>
            <c:otherwise>
              <span class="time">${post.timeFormat}</span></a>
            </c:otherwise>
          </c:choose>
        </c:forEach>  
      </div>
    </div>
  </div>
  <jsp:include page="/common/bottom.jsp" flush="false" />
</body>
</html>
