<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<nav>
  <div class="navbar">
    <a href="<%=request.getContextPath()%>/main" class="pageLogo"><img src="https://via.placeholder.com/40" alt="로고"></a>
    <div class="pageTitle">
      <h3 class="title">유니넷</h3>
      <h2 class="campus">비트캠퍼스</h2>
    </div>

    <div class="menu">
      <ul>
        <li><a href="<%=request.getContextPath()%>/notice">공지사항</a></li>
        <li><a href="<%=request.getContextPath()%>/board/list">자유게시판</a></li>
        <li><a href="<%=request.getContextPath()%>/lecture/list">강의 관리</a></li>
        <li><a href="<%=request.getContextPath()%>/stat">통계</a></li>
      </ul>
    </div>

    <div class="mypage">
      <a href="message"><img src="https://via.placeholder.com/40" alt="쪽지"></a>
      <a href="mypage"><img src="https://via.placeholder.com/40" alt="쪽지"></a>
    </div>
  </div>
</nav>
