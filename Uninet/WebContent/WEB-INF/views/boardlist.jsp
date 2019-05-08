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
<h1>Uninet 자유게시판</h1>
<table>
  <thead>
      <th>글번호</th>
      <th>제목</th>
      <th>내용</th>
      <th>작성자</th>
      <th>작성시간</th>
      <th>조회수</th>
    </thead>
  <tbody>
    <c:forEach var = "post" items="${requestScope.list}">
      <tr>
      
        <td>${post.id}</td>
        <td> <a href="read?id=${post.id}">${post.title}</a></td>
        <td>${post.content}</td>
        <td>${post.writerId}</td>
        <td>${post.time}</td>
        <td>${post.count}</td>
       </tr>

   </c:forEach>
  </tbody>
</table>
<br>
<select name="searchPost" id="postSelect" class = "postSelect">
    <option value="titleSearch">제목</option>
    <option value="contentSearch">내용</option>
    <option value="allSearch">제목+내용</option>
    <option value="orderByCount">조회순</option>
  </select>
  <input type="text" name="search" id="search" class = "search"/>
  
  <input type="button" value="검색하기" id="btn" class = "button" />
  
</body>
</html>