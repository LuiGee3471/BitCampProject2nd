<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/common/head.jsp" flush="false" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/modal.css">
</head>
<body>
<jsp:include page="/common/top.jsp" flush="false" />
  <div class="container">
    <table class="lecturetable">
      <thead>
        <tr>
          <th>강의이름</th>
          <th>학점</th>
          <th>시간</th>
          <th>종별</th> 
          <th>학과</th>
          <th>교수</th>
          <th colspan="2"></th>
        </tr>
      </thead>
      <tbody id="searchResult">
        <c:forEach var="lecture" items="${requestScope.lecturelist}">
          <tr>
            <td>${lecture.lectureName}</td>
            <td>${lecture.credit}</td>
            <td>${lecture.time}</td>
            <td>${lecture.lectureType}</td>
            <td>${lecture.majorName}</td>
            <td>${lecture.profName }</td>
            <td><a href="updatePage?id=${lecture.id}"><i class="far fa-edit hover-big"></i></a></td>
            <td><a href="delete?id=${lecture.id}"><i class="far fa-trash-alt hover-big"></i></a></td>       
          </tr>
        </c:forEach>
      </tbody>
    </table>
    <div class = "button-between">
      <div class = "button-group">
        <p><button class="button" data-modal="modalOne">강의 검색</button></p>
        <p><button class="button" data-modal="modalTwo">정렬</button></p>
      </div>
      <a href="write"><button class="lecture-add"><i class="fas fa-folder-plus fa-2x hover"></i></button></a>
    </div>
  </div>

  <div id="modalOne" class="modal">
    <div class="modal-content">
      <div class="contact-form">
        <a class="close">&times;</a>
        <div class="label-container">
          <label for="lectureSelect">과목명</label>
          <input type="radio" value="lecture" name="searchradio" id="lectureSelect" checked>   
          <label for = profSelect>교수명</label>
          <input type="radio" value="prof" name="searchradio" id="profSelect">
          <label for="majorSelect">전공명</label>
          <input type="radio" value="major" name="searchradio" id="majorSelect">
        </div>
        <input type="text" name="searchInput" id="searchInput" placeholder="검색어 입력">
        <button type="button" class="searchBtn" id="closeBtn">검색</button>          
      </div>
    </div>
  </div>

  <div id="modalTwo" class="modal">
    <div class="modal-content">
      <div class="contact-form">
        <span class="close">&times;</span>
          <div class="select-sort">
            <input type="radio" value="basic" name="sort" id="basic" checked>
            <label for="basic">기본</label>
            <input type="radio" value="lecture" id="lecture" name="sort">
            <label for="lecture">강의</label>
            <input type="radio" value="credit" id="credit" name="sort">
            <label for="credit">학점</label>
            <input type="radio" value="prof" id="prof" name="sort">
            <label for="prof">교수</label>         
            <input type="radio" value="major" id="major" name="sort">
            <label for="major">전공</label>
          </div>   
          <button type="button" class="searchBtn2" id="closeBtn2">정렬</button>        
      </div>
    </div>
  </div>
  <jsp:include page="/common/bottom.jsp" flush="false" />
  <script src="<%=request.getContextPath()%>/js/lecturelist.js"></script>
</body>
</html>