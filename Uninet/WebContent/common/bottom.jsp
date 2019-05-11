<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<div class="bottom">
  <ul class="bottom-nav">
    <li class="copyright"><a href="#">© 유니넷</a></li>
    <li><a href="<%=request.getContextPath()%>/board/list?page=1&option=default&boardtype=1">공지사항</a></li>
    <li><a href="<%=request.getContextPath()%>/board/list?page=1&option=default&boardtype=2">자유게시판</a></li>
    <li><a href="<%=request.getContextPath()%>/lecture/list">강의 관리</a></li>
    <li><a href="<%=request.getContextPath()%>/stat/chart">통계</a></li>
  </ul>
</div>