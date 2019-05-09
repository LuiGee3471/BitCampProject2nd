<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/top-bottom.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
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
      <span>새 글을 작성하세요!</span>
    </div>
    <form action="write" id="postform" class="unseen" method="post">
      <input type="text" placeholder="글 제목" name="title">
      <textarea placeholder="글 내용" name="content"></textarea>
      <input type="submit" value="글쓰기">
      <input type="hidden" name="boardType" value="2">
    </form>
    <table class="content">
      <c:forEach var="post" items="${requestScope.list}">
        <tr class="posttitle">
          <td><a href="read?id=${post.id}">${post.title}</a></td>
        </tr>
        <tr class="detail">
          <td>${post.content}</td>
        </tr>
        <tr class="endline">
          <c:choose>
            <c:when test="${post.diff < 2}">
              <td>방금&nbsp;${post.staffId}</td>
            </c:when>
            <c:when test="${post.diff < 60}">
              <td>${post.diff}분 전&nbsp;${post.staffId}</td>
            </c:when>
            <c:otherwise>
              <td>${post.timeFormat}&nbsp;${post.staffId}</td>
            </c:otherwise>
          </c:choose>
        </tr>
      </c:forEach>
    </table>
    <br> <select name="searchPost" id="postSelect" class="postSelect">
      <option value="titleSearch">제목</option>
      <option value="contentSearch">내용</option>
      <option value="allSearch">제목+내용</option>
      <option value="orderByCount">조회순</option>
    </select> 
    <input type="text" name="search" id="search" class="search" /> 
    <input type="button" value="검색하기" id="btn" class="button" />
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