<%@page import="kr.co.groot.dto.Staff"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="list" value="${requestScope.list}" />
<c:set var="option" value="${requestScope.option}" />
<c:set var="word" value="${requestScope.word}" />
<c:set var="currentPage" value="${requestScope.currentPage}" />
<c:set var="pages" value="${requestScope.pages}" />
<c:set var="staff" value="${sessionScope.staff}"/>
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
</head>
<body>
  <jsp:include page="/common/top.jsp" flush="false" />
  <div class="container">
    <div class="titlebox">
      <h1>내가 쓴 글</h1>
    </div>
    <div class="articles">
      <c:forEach var="post" items="${list}">
        <a href="board/read?id=${post.id}">
          <div class="article">
            <div class="article-main">
              <div class="user">
                <img class="profile-photo"
                  src="<%=request.getContextPath()%>/images/${staff.image}"
                  alt="" />
                <p class="user-info">
                  <span class="writer-id">${staff.staffId}</span> <span
                    class="time">${post.timeFormat}</span>
                </p>
              </div>
              <h4 class="article-title">${post.title}</h4>
              <p class="article-content">${post.content}</p>
              <c:choose>
                <c:when test="${post.boardType == 1}">
                  <span class="board-type">from 공지사항</span>
                </c:when>
                <c:otherwise>
                  <span class="board-type">from 자유게시판</span>
                </c:otherwise>
              </c:choose>
            </div>

            <div class="article-sub">
              <p class="article-info">
                <i class="far fa-eye">&nbsp;${post.count}</i>&nbsp;<i
                  class="far fa-comment">&nbsp;${post.commentCount}</i>
              </p>
            </div>
          </div>
        </a>
      </c:forEach>
    </div>
    <div class="board-bottom">
    <c:choose>
    <c:when test="${currentPage == 1}">
      <form action="mypost" class="searchInput" method="post">
        <select name="option" id="postSelect" class="postSelect">
          <option value="title">제목</option>
          <option value="content">내용</option>
          <option value="all">제목+내용</option>
        </select> 
        <input type="text" name="word" id="search" class="search" placeholder="검색어를 입력하세요." autocomplete="off"/>
        <input type="hidden" name="page" value="1"> 
        <input type="image" class="searchBtn" src="<%=request.getContextPath()%>/images/search.png">
      </form>
    </c:when>
    <c:otherwise>
      <div class="btn prev-page">&lt;&nbsp;이전</div>
    </c:otherwise>
    </c:choose>
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
    const boardType = <%=request.getAttribute("boardType")%>;
    const word = "<%=request.getAttribute("word")%>";
  </script>
  <script src="<%=request.getContextPath()%>/js/mypost.js"></script>
</body>
</html>