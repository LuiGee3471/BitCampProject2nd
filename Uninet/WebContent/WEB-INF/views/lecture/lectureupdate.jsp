<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="lectureList" value="${requestScope.lectureList}"></c:set>
<c:set var="profList" value="${requestScope.profList}"></c:set>
<c:set var="ltList" value="${requestScope.ltList}"></c:set>
<c:set var="majorList" value="${requestScope.majorList}"></c:set>
<c:set var="lecture2" value="${requestScope.lecture2}"></c:set>
<c:set var="professorList" value="${requestScope.professorList }"/>
<jsp:include page="/common/head.jsp" flush="false" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/update.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

</head>
<body>

<jsp:include page="/common/top.jsp" flush="false" />
   <div class="container">
    <form action="updateOk" method = "GET">
    <input type="hidden" value = "${requestScope.id}" name="id">
   
    <!-- 과목 입력 -->
    <label for = "lectureName" class = "label">과목</label>
    <input type = "text" id = "lectureName" name = "lectureName" value = "${lecture2.lectureName}" class = "textBottom">
    <!-- 과목 입력 -->
    <br>
    
    
     <!-- 학점 선택  -->
     <span class = "label">학점</span>
     <select id = "selectCredit" name = "credit" class = "textBottom">
<c:forEach var = "i" begin="1" end="3">
  <c:choose>
    <c:when test="${i==lecture2.credit}">
        <option value="${i}" selected>${i}</option>
    </c:when>
    <c:otherwise>
        <option value="${i}">${i}</option>
     </c:otherwise>
  </c:choose>
</c:forEach>
</select>
<br>
    <!-- 학점 선택  -->
    
        <!-- 시간 선택  -->
   <span class = "label">시간</span>
   <div class = "day-time">
   <div class="dayupdate" id="timeFirst">
   <input type="radio" name="weekday" id="monday" value="월"><label for="monday" class="date" id="mon-label">월</label>
   <input type="radio" name="weekday" id="tuesday" value="화"><label for="tuesday" class="date">화</label>
   <input type="radio" name="weekday" id="wednesday" value="수"><label for="wednesday" class="date">수</label>
   <input type="radio" name="weekday" id="thursday" value="목"><label for="thursday" class="date">목</label>
   <input type="radio" name="weekday" id="friday" value="금"><label for="friday" class="date" id="fri-label">금</label>
   </div>
      <select id = "changeTime" name = "lectureTime" class = "textBottom">
        
       <!--  <option value = "123">123</option>
        <option value = "456">456</option>
        <option value = "789">789</option> -->
      </select>
      </div>
    <!-- 시간 선택  -->
    
     <!-- 종별 선택  -->
   <span class = "label">종별</span>
 <select id = "lectureTypeId" name = "lecturetype" class = "textBottom">
   <c:forEach var = "lecttype" items = "${ltList}">
        <c:choose>
          <c:when test="${lecture2.lectureType == lecttype.lectureType}">
            <option value = "${lecture2.lectureTypeId}" selected>${lecttype.lectureType}</option>
          </c:when>
          <c:otherwise>
            <option value = "${lecttype.id}">${lecttype.lectureType}</option>
          </c:otherwise>
        </c:choose>
   </c:forEach>
   </select>
  <br>    
 
     <!-- 교수 선택  -->
     <span class = "label">교수</span>
      <select id = "professorId" name = "professor" class = "textBottom">
         <c:forEach var = "professor" items = "${professorList}">
           <c:choose>
             <c:when test="${professor.profName == lecture2.profName}">
              <option value = "${professor.id}" selected>${lecture2.profName}</option>
             </c:when>
             
             <c:otherwise>
             <option value = "${professor.id}">${professor.profName}: [${professor.majorName}]</option>
             </c:otherwise>
           </c:choose>     
         </c:forEach>
      </select>
     <br>
 
    <!-- 교수 선택  -->
    
    <input type = "submit" value = "수정하기" class = "updateBtn" id="updateBtn"><br>
    <a class="cancle-back" href="list">취소하고 돌아가기</a>
    </form>
    </div>
   <jsp:include page="/common/bottom.jsp" flush="false" />
   <script type="text/javascript">
   var totalTime = "${lecture2.time}";
   var day = totalTime.slice(0,1);
   var time = totalTime.slice(1);
 
   </script>
   <script src="<%=request.getContextPath()%>/js/lectureupdate.js"></script>
</body>
</html>