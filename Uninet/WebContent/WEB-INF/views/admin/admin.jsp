<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	int totalPages = (int) request.getAttribute("pages");
	int currentPage = (int) request.getAttribute("currentPage");
	int startPage = 1;
	if (currentPage >= 4) {
		if (currentPage % 3 == 1) {
			startPage = currentPage;
		} else if (currentPage % 3 == 2) {
			startPage = currentPage - 1;
		} else {
			startPage = currentPage - 2;
		}
	}

	int endPage = 0;
	if (totalPages <= 3) {
		endPage = totalPages;
	} else {
		if (totalPages - startPage >= 3) {
			endPage = startPage + 2;
		} else {
			endPage = totalPages;
		}
	}

	System.out.println(startPage + " / " + endPage);
	pageContext.setAttribute("startPage", startPage);
	pageContext.setAttribute("endPage", endPage);
%>
<jsp:include page="/common/head.jsp" flush="false" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/board.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/admin.css">
<script type="text/javascript">
</script>
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
					</select> <input type="text" id="searchInput" name="searchInput" class="search"
						autocomplete="off"> <input type="image" id="btn"
						class="searchBtn"
						src="<%=request.getContextPath()%>/images/search.png">
				</div>
			</div>
		</div >
		<div class="board-bottom">
			<div class='page-btns'></div>

		</div>
		<jsp:include page="/common/bottom.jsp" flush="false" />
		<script>
      const currentPage = <%=request.getAttribute("currentPage")%>;
      const option = "<%=request.getAttribute("option")%>";
    </script>
	</div>
	<script src="<%=request.getContextPath()%>/js/stafflist.js"></script>
</body>
</html>