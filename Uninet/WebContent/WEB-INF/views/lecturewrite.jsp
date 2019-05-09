<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<c:set var="profList" value="${requestScope.profList}"></c:set>
<c:set var="ltList" value="${requestScope.ltList}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

  <form action="writeOk" method = "GET">
   <div class="container">
   <!-- 과목 입력 -->
   <label for = "lectureName">과목</label>
   <input type = "text" id = "lectureName" name = "lectureName" placeholder = "과목입력">
   <!-- 과목 입력 -->
    <br>
    
     <!-- 학점 선택  -->
   <span class="lable">학점</span>
      <select name="credit">
        <option value="">학점 선택</option>
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
      </select><br>
    <!-- 학점 선택  -->
    
     <!-- 시간 선택  -->
   <span class = "lable">시간</span>
    <select name="weekday">
      <option value="월">월</option>
      <option value="화">화</option>
      <option value="수">수</option>
      <option value="목">목</option>
      <option value="금">금</option>
    </select>
      <select name = "lectureTime">
        <option value="">교시 선택</option>
        <option value = "1">1</option>
        <option value = "2">2</option>
        <option value = "3">3</option>
        <option value = "4">4</option>
        <option value = "5">5</option>
        <option value = "6">6</option>
        <option value = "7">7</option>
        <option value = "8">8</option>
        <option value = "9">9</option>
      </select><br>
    <!-- 시간 선택  -->
    
     <!-- 종별 선택  -->
   <span class = "lable">종별</span>
      <select name = "lectureTypeId">
        <option value="">종별 선택</option>
        <c:forEach var = "lect" items = "${ltList}">
        <option value = "${lect.id}">${lect.lectureType}</option>
        </c:forEach>
      </select><br>
    <!-- 종별 선택  -->
    
 
     <!-- 교수 선택  -->
   <span class = "lable">교수</span>
      <select name = "professorId">
        <option value="">교수 선택</option>
        <c:forEach var = "professor" items = "${profList}">
        <option value = "${professor.id}">${professor.profName}</option>
        </c:forEach>
      </select><br>
    <!-- 교수 선택  -->
    
    <input type = "submit" value = "추가하기" class = ""><br>
    <a class = "" href = "list">취소하고 돌아가기</a> 
    
  
  </div>
   
    </form>

</body>
</html>