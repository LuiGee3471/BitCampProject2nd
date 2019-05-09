<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="list" value="${requestScope.list}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/top-bottom.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css">
<link rel="stylesheet"
  href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
  <jsp:include page="/common/top.jsp" flush="false" />
  <div class="container">
    <div class="titlebox">
      <h1>자유게시판</h1>
    </div>
    <div class="textbox" id="textbox">
      <span>새 글을 작성해주세요!</span>
    </div>
    <form action="write" id="postform" class="unseen" method="post" enctype="multipart/form-data">
      <input type="text" placeholder="글 제목" name="title" class="new-title">
      <textarea placeholder="글 내용" name="content" class="new-content"></textarea>
      <div class="new-option">
        <label for="file" class="file-btn"><img class="file-image" src="<%=request.getContextPath()%>/images/clip.png"></label>
        <input type="file" name="file" id="file" class="file-upload">
        <input
            class="new-btn" type="image"
            src="<%=request.getContextPath()%>/images/submit.png">
      </div>
      <input type="hidden" name="boardType" value="2">
    </form>
    <div class="articles">
    <c:forEach var="post" items="${list}">  
      <div class="article">
        <div class="article-main">
        <h4 class="article-title">${post.title}</h4>
        <p class="article-content">${post.content}</p>
        <p class="write-info">
        <c:choose>
          <c:when test="${post.diff < 2}"><span class="time">방금</span></c:when>
          <c:when test="${post.diff < 60}"><span class="time">${post.diff}분 전</span></c:when>
          <c:otherwise><span class="time">${post.timeFormat}</span></c:otherwise>
        </c:choose>
        &nbsp;<span class="writer-id">${post.staffId}</span>
        </p>
        </div>
        <div class="article-sub">
        <p class="article-info">
          <i class="far fa-eye">&nbsp;${post.count}</i>&nbsp;<i class="far fa-comment">&nbsp;${post.commentCount}</i>
        </p>
        </div>
      </div>
    </c:forEach>
    </div>
    <form class="searchInput">
    <select name="searchPost" id="postSelect" class="postSelect">
      <option value="titleSearch">제목</option>
      <option value="contentSearch">내용</option>
      <option value="allSearch">제목+내용</option>
      <option value="orderByCount">조회순</option>
    </select> 
    <input type="text" name="search" id="search" class="search" placeholder="검색어를 입력하세요." autocomplete="off"/> 
    <input type="image" class="searchBtn" src="<%=request.getContextPath()%>/images/search.png">
    </form>
  </div>
  <jsp:include page="/common/bottom.jsp" flush="false" />
  <script type="text/javascript">
    $("#textbox").click(function() {
      $(this).addClass("unseen");
      $("#postform").removeClass("unseen");
    });
  </script>
</body>
</html>