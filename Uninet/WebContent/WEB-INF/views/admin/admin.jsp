<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="/common/head.jsp" flush="false" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin.css">
</head>
<body>
	<jsp:include page="/common/top.jsp" flush="false" />
	<div class="container">
		<table class="lecturetable">
			<thead>
				<tr>
					<th>이름</th>
					<th>아이디</th>
					<th>email</th>
					<th>phone</th>
					<th>생일</th>
					<th>부서</th>
					<th colspan="2"></th>
				</tr>
			</thead>
			<tbody id="searchResult">
			</tbody>
		</table>
		<div class="button-between">
			<div class="button-group">
				<div class="searchInput">
					<select id="search" name="option" class="select">
						<option value="name">이름별 검색</option>
						<option value="deptName">부서별 검색</option>
					</select> 
					<input type="text" id="searchInput" name="searchInput"
						class="search" autocomplete="off"> 
					<select class="unseen">
						<option value="11"></option>
					</select>	
						<input type="image"
						id="btn" class="searchBtn"
						src="<%=request.getContextPath()%>/images/search.png">
				</div>
			</div>
		</div>
		<div class="board-bottom">
			<div class='page-btns'></div>
		</div>
	</div>
		<jsp:include page="/common/bottom.jsp" flush="false" />
	<script src="<%=request.getContextPath()%>/js/stafflist.js"></script>
</body>
</html>