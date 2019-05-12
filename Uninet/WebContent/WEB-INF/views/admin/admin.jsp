<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/common/head.jsp" flush="false" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin.css">
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
        <c:forEach var="staff" items="${requestScope.staffList}">
          <tr>
            <td>${staff.staffName}</td>
            <td>${staff.staffId}</td>
            <td>${staff.email}</td>
            <td>${staff.phoneNumber}</td>
            <td>${staff.birthday}</td>
            <td>${staff.deptName}</td>
            <td><a href="updatePage?id=${staff.id}"><i class="far fa-edit hover-big"></i></a></td>
            <td><a href="delete?id=${staff.id}"><i class="far fa-trash-alt hover-big"></i></a></td>  
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
  <script src="<%=request.getContextPath()%>/js/admin.js"></script>
</body>
</html>