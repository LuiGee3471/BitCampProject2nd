<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<c:set var="profList" value="${requestScope.profList}"></c:set>
<c:set var="ltList" value="${requestScope.ltList}"></c:set>
<<c:set var="professorList" value="${requestScope.professorList }"/>
<jsp:include page="/common/head.jsp" flush="false" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/update.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
</script>
</head>
<body>
<jsp:include page="/common/top.jsp" flush="false" />
   <div class="container">
  <form action="writeOk" method = "GET">
   <!-- 과목 입력 -->
   <label for = "lectureName" class = "label">과목</label>
   <input type = "text" id = "lectureName" name = "lectureName" placeholder = "과목입력" class = "textBottom">
   <!-- 과목 입력 -->
    <br>
    
     <!-- 학점 선택  -->
   <span class="label">학점</span>
      <select id = "selectCredit" name="credit" class = "textBottom">
        <option value="">학점 선택</option>
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
      </select><br>
    <!-- 학점 선택  -->
    
     <!-- 시간 선택  -->
   <span class = "label">시간</span>
   <div class="day" id="timeFirst">
   <input type="radio" name="weekday" id="monday" value="월"><label for="monday" class="date" >월</label>
   <input type="radio" name="weekday" id="tuesday" value="화"><label for="tuesday" class="date">화</label>
   <input type="radio" name="weekday" id="wednesday" value="수"><label for="wednesday" class="date">수</label>
   <input type="radio" name="weekday" id="thursday" value="목"><label for="thursday" class="date">목</label>
   <input type="radio" name="weekday" id="friday" value="금"><label for="friday" class="date">금</label>
   </div>
      <select id = "changeTime" name = "lectureTime" class = "textBottom">
        
       <!--  <option value = "123">123</option>
        <option value = "456">456</option>
        <option value = "789">789</option> -->
      </select><br>
    <!-- 시간 선택  -->
    
     <!-- 종별 선택  -->
   <span class = "label">종별</span>
      <select name = "lectureTypeId" id="lectureTypeId" class = "textBottom">
        <option value="">종별 선택</option>
        <c:forEach var = "lect" items = "${ltList}">
        <option value = "${lect.id}">${lect.lectureType}</option>
        </c:forEach>
      </select><br>
    <!-- 종별 선택  -->
    
 
     <!-- 교수 선택  -->
   <span class = "label">교수</span>
      <select name = "professorId" id="professorId" class = "textBottom">
        <option value="">교수 선택</option>
        <c:forEach var = "professor" items = "${professorList}">
        <option value = "${professor.id}">${professor.profName}: ${professor.majorName }</option>
        </c:forEach>
      </select><br>
    <!-- 교수 선택  -->
    
    <input type = "submit" value = "추가하기" id="addBtn" class = "addBtn"><br>
    <a class = "cancle-back" href = "list">취소하고 돌아가기</a> 
    

    </form>
     </div>
      <jsp:include page="/common/bottom.jsp" flush="false" />
      <script src="<%=request.getContextPath()%>/js/lecturewrite.js"></script>

</body>
</html>