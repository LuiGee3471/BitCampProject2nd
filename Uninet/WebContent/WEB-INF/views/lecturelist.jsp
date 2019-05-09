<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/top-bottom.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
	  $("#closeBtn").on("click", function() {
	    // if ($("#searchInput").val()) {
        /* alert($("#searchInput").val() + " 검색합니다"); */
				const inputVal = $("#searchInput").val();
        const radioVal = $("input[name='searchradio']:checked").val();
       
        $.ajax( 
            {
          		url: "inputtext",
          		dataType : "html",
          		data :  {
          		  searchradio : radioVal,
          		  searchInput : inputVal
          		},
          		success : function(data) {
          		  $("#searchResult").html(data);
          		  console.log(data);
          		},
          		error : function(xhr) {
          		  console.log(xhr.status);
          		}
        		}
            );
      // } 
	  });
	});


</script>

</head>
<body>

<jsp:include page="/common/top.jsp" flush="false" />
<div class = "container">
  <table class = "lecturetable">
  <thead>
    <tr>
      <td>강의이름</td>
      <td>학점</td>
      <td>시간</td>
      <td>종별</td> 
      <td>학과</td>
      <td>교수</td>
    </tr>
   </thead>
   <tbody id = "searchResult">
    <c:forEach var = "lecture" items = "${requestScope.lecturelist}">
    <tr>
      <td>${lecture.lectureName}</td>
      <td>${lecture.credit}</td>
      <td>${lecture.time}</td>
      <td>${lecture.lectureType}</td>
      <td>${lecture.majorName}</td>
      <td>${lecture.profName }</td>
      <td><a href = "updatePage?id=${lecture.id}">수정</a></td>
      <td><a href = "delete?id=${lecture.id}">삭제</a></td>
      <td><a href = "write">추가</a></td>
        
    </tr>
    </c:forEach>
    </tbody>
  </table>
    
  
  
 </div>
 
  <!-- Button to Open the Modal -->
  <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
    검색하기
  </button>

  <!-- The Modal -->
  <div class="modal" id="myModal">
    <div class="modal-dialog">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title"></h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
          <label>과목명</label>
          <input type = "radio" value = "lecture" name = "searchradio">
          <br>
          <label>교수명</label>
          <input type = "radio" value = "prof" name = "searchradio">
          <br>
          <label>전공명</label>
          <input type = "radio" value = "major" name = "searchradio">
          
          <label for = "searchInput">Input</label>
          <input type = "text" name = "searchInput" id = "searchInput"  placeholder = "검색어">  
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" id = "closeBtn" class="btn btn-danger" data-dismiss="modal">검색 </button>
        </div>
        
      </div>
    </div>
  </div>
  
   <!-- Button to Open the Modal -->
  <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal2">
    정렬하기
  </button>

  <!-- The Modal -->
  <div class="modal" id="myModal2">
    <div class="modal-dialog">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title"></h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
          <label>기본</label>
          <input type = "radio" value = "" name = "sort" id = "sort">
          <br>
          <label>과목명</label>
          <input type = "radio" value = "" name = "sort" id = "sort">
          <br>
          <label>교수명</label>
          <input type = "radio" value = "" name = "sort" id = "sort">
          <br>
          <label>학점명</label>
          <input type = "radio" value = "" name = "sort" id = "sort">
          
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" id = "closeBtn" class="btn btn-danger" data-dismiss="modal">검색 </button>
        </div>
        
      </div>
    </div>
  </div>
  <jsp:include page="/common/bottom.jsp" flush="false" />


</body>
</html>