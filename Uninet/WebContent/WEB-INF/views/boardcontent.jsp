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
</head>
<body>
<div class="container">
<jsp:include page="/common/top.jsp" flush="false" />
<c:set var = "post" value="${requestScope.post}"/>
<c:set var = "staff" value="${requestScope.staff}"/>
<span>${staff.staffId}</span>
<span>${post.time}</span>
<br>
<h1>${post.title}</h1>
<div>${post.content}</div>
<form action = "board/comment" method = "get">
<input type = "text" id="commentcontent">
<input type = "submit" value = "댓글 달기">
</form>
<c:forEach var = "comment" items = "${requestScope.commentlist}">
<table>
<tr>
${comment.writerId}
</tr>
<tr>
${comment.content}
</tr>
<tr>
${comment.time}
</tr>
</table>
</c:forEach>
</div>
<jsp:include page="/common/bottom.jsp" flush="false" />
</body>
</html>