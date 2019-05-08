<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div class = "container">
  <table class = "lecturetable">
    <tr>
      <td>강의이름</td>
      <td>학점</td>
      <td>시간</td>
      <td>종별</td> 
      <td>학과</td>
      <td>교수</td>
    </tr>
    <c:forEach var = "lecture" items = "${requestScope.lecturelist}">
    <tr>
      <td>${lecture.lectureName}</td>
      <td>${lecture.credit}</td>
      <td>${lecture.time}</td>
      <td>${lecture.lectureType}</td>
      <td>${lecture.majorName}</td>
      <td>${lecture.profName }</td>
      <td><a href = "update?id=${lecture.id}">수정</a></td>
      <td><a href = "delete?id=${lecture.id}">삭제</a></td>
      <td><a href = "write">추가</a></td>
      <td></td>
    </tr>
    </c:forEach>
  
  
  </table>
  
 </div>



</body>
</html>