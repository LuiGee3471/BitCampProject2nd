<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/common/head.jsp" flush="false" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin.css">
<script type="text/javascript">
  $(function() {
    $('#btn').on("click", function() {
      const inputVal = $("#input").val();
      const searchVal = $("#search option:selected").val();
      console.log(inputVal);
      console.log(searchVal);
      if (!$('#input').val()) {
        alert('값을 입력해주세요');
      } else {
        $.ajax({
          url : "inputText",
          dataType : "html",
          data : {
            searchText : inputVal,
            orderBy : searchVal
          },
          success : function(data) {
            console.log(data);
            $('#searchResult').html(data);
          }
        })
      }
    });
  });
</script>
</head>
<body>
  <jsp:include page="/common/top.jsp" flush="false" />
  <div class="container">
    <table class="lecturetable">
      <tr>
        <th>이름</th>
        <th>아이디</th>
        <th>email</th>
        <th>phone</th>
        <th>생일</th>
        <th>부서</th>
        <th colspan="2"></th>
      </tr>
      <tbody id="searchResult">
        <c:forEach var="staffList" items="${requestScope.staffList}">
          <tr>
            <td>${staffList.staffName}</td>
            <td>${staffList.staffId}</td>
            <td>${staffList.email}</td>
            <td>${staffList.phoneNumber}</td>
            <td>${staffList.birthday}</td>
            <td>${staffList.deptName}</td>
            <td>
              <a href="modify?id=${staffList.id}">
                <i class="fas fa-folder-plus"></i>
              </a>
            </td>
            <td>
              <a href="delete?id=${staffList.id}">
                <i class="far fa-trash-alt"></i>
              </a>
            </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    <div class="searchInput">
      <select id="search" name="orderBy" class="select">
        <option value="name">이름별 검색</option>
        <option value="deptName">부서별 검색</option>
      </select> 
      <input type="text" id="input" name="searchInput" class="search" autocomplete="off"> 
      <input type="image" class="searchBtn" src="<%=request.getContextPath()%>/images/search.png">
    </div>
  </div>
  <jsp:include page="/common/bottom.jsp" flush="false" />
</body>
</html>