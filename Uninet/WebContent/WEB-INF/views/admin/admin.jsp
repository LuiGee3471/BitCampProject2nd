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
		<input type="hidden" value="default" name="">
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
			</tbody>
		</table>
		<div class="searchInput">
			<select id="search" name="option" class="select">
				<option value="name">이름별 검색</option>
				<option value="deptName">부서별 검색</option>
			</select> <input type="text" id="input" name="searchInput" class="search"
				autocomplete="off"> <input type="image" id="btn"
				class="searchBtn"
				src="<%=request.getContextPath()%>/images/search.png">
		</div>
		<div class="board-bottom">
			<div class="btn prev-page">&lt;&nbsp;이전</div>
			<div class="page-btns">
				<c:if test="${pages > 3 && currentPage > 3}">
					<div class="btn prv-btn">&lt;</div>
				</c:if>
				<c:forEach var="page" begin="${startPage}" end="${endPage}">
					<c:choose>
						<c:when test="${page == currentPage}">
							<div class="btn page-btn current-btn">${page}</div>
						</c:when>
						<c:otherwise>
							<div class="btn page-btn">${page}</div>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${pages - endPage > 0}">
					<div class="btn next-btn">&gt;</div>
				</c:if>
			</div>
			<c:choose>
				<c:when test="${currentPage < pages}">
					<div class="btn next-page">다음&nbsp;&gt;</div>
				</c:when>
				<c:otherwise>
					<div class="btn next-page invisible">다음&nbsp;&gt;</div>
				</c:otherwise>
			</c:choose>
		</div>
		<jsp:include page="/common/bottom.jsp" flush="false" />
		<script>
      const currentPage = <%=request.getAttribute("currentPage")%>;
      const option = "<%=request.getAttribute("option")%>";
      const word = "<%=request.getAttribute("word")%>";
    </script>
	</div>
	<script src="<%=request.getContextPath()%>/js/stafflist.js"></script>
</body>
</html>