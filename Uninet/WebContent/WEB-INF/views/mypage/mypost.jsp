<%@page import="kr.co.groot.dto.Staff"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:set var="list" value="${requestScope.mypost}"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css">
<jsp:include page="/common/head.jsp" flush="false" />
<%
Staff staff = (Staff) session.getAttribute("staff");
%>

</head>

<body>
<jsp:include page="/common/top.jsp" flush="false" />
<div class="container">
<div class="titlebox">
      <h1>내가 쓴 글 </h1>
    </div>
 <div class="articles">
    <c:forEach var="post" items="${list}">  
    <a href="board/read?id=${post.id}">
      <div class="article">   
        <div class="article-main">
        <h4 class="article-title">${post.title}</h4>
        <p class="article-content">${post.content}</p>
        <p class="write-info">
          <span class="time">${post.timeFormat}</span>
       
          <span class="writer-id">${staff.staffId}</span>
        </p>
        </div>
        <div class="article-sub">
        <p class="article-info">
          <i class="far fa-eye">&nbsp;${post.count}</i>&nbsp;<i class="far fa-comment">&nbsp;${post.commentCount}</i>
        </p>
        </div>
      </div>
      </a>
    </c:forEach>
    </div>    
</div>
<jsp:include page="/common/bottom.jsp" flush="false" />
</body>
</html>