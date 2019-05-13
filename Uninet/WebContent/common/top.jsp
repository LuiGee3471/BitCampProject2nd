<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<nav>
  <div class="navbar">
    <a href="<%=request.getContextPath()%>/main" class="pageLogo">
      <img src="<%=request.getContextPath()%>/images/logo.png" alt="로고" class="pageLogo-image">
    </a>
    <div class="pageTitle">
      <h3 class="title">유니넷</h3>
      <h2 class="campus">비트캠퍼스</h2>
    </div>

    <div class="menu">
      <ul>
        <li><a href="<%=request.getContextPath()%>/board/list?page=1&option=default&boardtype=1">공지사항</a></li>
        <li><a href="<%=request.getContextPath()%>/board/list?page=1&option=default&boardtype=2">자유게시판</a></li>
        <li><a href="<%=request.getContextPath()%>/lecture/list?page=1">강의 관리</a></li>
        <li><a href="<%=request.getContextPath()%>/stat/chart">통계</a></li>
      </ul>
    </div>

    <div class="mypage">
      <a class="my-link" href="<%=request.getContextPath()%>/message"><img src="<%=request.getContextPath()%>/images/mail.png" alt="쪽지"></a>
      <a class="my-link" href="<%=request.getContextPath()%>/mypage"><img src="<%=request.getContextPath()%>/images/user.png" alt="마이페이지"></a>
    </div>
  </div>
</nav>

