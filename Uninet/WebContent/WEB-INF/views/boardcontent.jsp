<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:set var = "post" value="${requestScope.post}"/>
<span>${post.writerId}</span>
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
</body>
</html>