<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <link
      rel="stylesheet"
      href="<%=request.getContextPath()%>/css/top-bottom.css"
    />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/post.css" />
    <link
      rel="stylesheet"
      href="https://use.fontawesome.com/releases/v5.8.2/css/all.css"
    />
    <title>Insert title here</title>
  </head>
  <body>
    <jsp:include page="/common/top.jsp" flush="false" />
    <div class="container">
      <div class="boardtitle"><h1>자유게시판</h1></div>
      <div class="article">
        <div class="article-main">
          <div class="writer">
            <img
              class="profile-photo"
              src="https://via.placeholder.com/40"
              alt=""
            />
            <div class="writer-info">
              <span class="writer-id">글쓴이</span>
              <span class="time">05/05 06:18</span>
            </div>
          </div>
          <div class="article-content">
            <h3 class="article-title">제목</h3>
            <p class="content">내용</p>
          </div>
        </div>
        <div class="article-sub">
          <span class="comment-option">쪽지</span>
          <div class="article-stat">
            <i class="far fa-eye">10</i> 
            <i class="far fa-comment">5</i> 
          </div>
        </div>
      </div>
      <div class="comments">
        <div class="comment">
          <div class="comment-main">
            <div class="comment-writer">
              <img
                src="https://via.placeholder.com/20"
                alt=""
                class="comment-photo"
              />
              <span class="comment-id">댓글쓴이</span>
            </div>
            <p class="comment-content">댓글 내용</p>
            <span class="time">05/06 06:23</span>
          </div>
          <div class="comment-sub">
            <span class="comment-option">대댓글</span>
            <span class="comment-option">쪽지</span>
          </div>
        </div>
        <div class="recomment">
          <div class="comment-main">
            <div class="comment-writer">
              <img
                src="https://via.placeholder.com/20"
                alt=""
                class="comment-photo"
              />
              <span class="comment-id">댓글쓴이</span>
            </div>
            <p class="comment-content">댓글 내용</p>
            <span class="time">05/06 06:23</span>
          </div>
          <div class="comment-sub">
            <span class="comment-option">대댓글</span>
           <span class="comment-option">쪽지</span>
          </div>
        </div>
        <div class="comment">
          <div class="comment-main">
            <div class="comment-writer">
              <img
                src="https://via.placeholder.com/20"
                alt=""
                class="comment-photo"
              />
              <span class="comment-id">댓글쓴이</span>
            </div>
            <p class="comment-content">댓글 내용</p>
            <span class="time">05/06 06:23</span>
          </div>
          <div class="comment-sub">
            <span class="comment-option">대댓글</span>
            <span class="comment-option">쪽지</span>
          </div>
        </div>
        <div class="comment-input">
        <form action="comment" method="post" class="comment-form">
          <input class="comment-text" type="text" name="comment" maxlength="50" placeholder="댓글을 입력하세요"/>
          <input class="submit" type="image" src="<%=request.getContextPath()%>/images/submit.png">  
        </form>
        </div>
      </div>
    </div>
    <jsp:include page="/common/bottom.jsp" flush="false" />
  </body>
</html>
