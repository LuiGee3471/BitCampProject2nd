<%@page import="kr.co.groot.dao.CommentDao"%>
<%@page import="kr.co.groot.dao.StaffDao"%>
<%@page import="kr.co.groot.dto.Staff"%>
<%@page import="kr.co.groot.dto.Comment"%>
<%@page import="java.util.List"%>
<%@page import="kr.co.groot.dto.Post"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% 
  Post post = (Post) request.getAttribute("post");
  List<Comment> comments = (List<Comment>) request.getAttribute("comments");
  int id = (int) request.getAttribute("postId");

  Staff staff = (Staff) session.getAttribute("staff");
  StaffDao staffDao = new StaffDao();
  Staff writer = staffDao.selectByUniqueId(post.getWriterId());
  System.out.println(writer.getStaffId());
  CommentDao commentDao = new CommentDao();
  int commentCount = commentDao.getCommentCount(id);
  pageContext.setAttribute("writer", writer);
  pageContext.setAttribute("commentCount", commentCount);
%>
<c:set var="post" value="${post}"/>
<c:set var="comments" value="${comments}"/>
<c:set var="id" value="${postId}"/> 
<c:set var="writer" value="${writer}"/>
<c:set var="curruser" value="${staff}"/>
<jsp:include page="/common/head.jsp" flush="false" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/modal.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/post.css" />
</head>
<body>
  <jsp:include page="/common/top.jsp" flush="false" />
  <div class="container">
    <div class="boardtitle">
      <h1>${requestScope.boardName}</h1>
    </div>
    <div class="article">
      <div class="article-main">
        <div class="writer">
          <img class="profile-photo"
            src="<%=request.getContextPath()%>/images/${writer.image}" alt="" />
          <div class="writer-info">
            <span class="unseen">${writer.id}</span>
            <c:choose>
              <c:when test="${writer.id eq sessionScope.staff.id}">
                <a href="<%=request.getContextPath()%>/mypage" class="writer-id">${writer.staffId}</a>
              </c:when>
              <c:otherwise>
                <a href="<%=request.getContextPath()%>/info?id=${writer.id}" class="writer-id">${writer.staffId}</a>
              </c:otherwise> 
            </c:choose>
            <span class="time">${post.timeFormat}</span>
          </div>
        </div>
        <div class="article-content">
          <h3 class="article-title">${post.title}</h3>
          <p class="content">${post.content}</p>
        </div>
      </div>
      <div class="article-sub">
        <div class ="user-menu">
        <c:if test="${writer.staffId ne curruser.staffId}">
          <span class="comment-option">쪽지</span>
        </c:if>
        <form action="delete" method ="post">
          <c:if test="${curruser.id == writer.id}">
            <button type="submit" class="delete-option" id="postDelete">삭제</button>
            <input type="hidden" value="${id}" name="postId">
            <input type="hidden" value="${post.boardType}" name="boardType">
          </c:if>
        </form>
        </div>
        <c:if test="${post.fileName ne null}">
          <div class="download">
            <i class="fas fa-paperclip"></i>
            <a href="<%=request.getContextPath()%>/file/${post.fileName}" download>${post.fileName}</a>
          </div>  
        </c:if>
        <div class="article-stat">
          <i class="far fa-eye">&nbsp;${post.count}</i>&nbsp;<i class="far fa-comment">&nbsp;${commentCount}</i>
        </div>
      </div>
    </div>
    <div class="comments">
      <c:forEach var="comment" items="${comments}">
        <c:choose>
          <c:when test="${comment.recomment == 'N'}">
           <c:if test="${comment.content!='삭제된 댓글입니다.'}">
          <div class="comment-recomment">
            <div class="comment">
               <input type="hidden" value="false" id="deleteCheck">
              <div class="comment-main">
                <div class="comment-writer">
                  <img 
                    src="<%=request.getContextPath()%>/images/${comment.writer.image}" 
                    alt="" 
                    class="comment-photo" /> 
                  <c:choose>
                    <c:when test="${writer.staffId == comment.writer.staffId}"><span class="comment-id comment-currentid">${writer.staffId}(글쓴이)</span></c:when>
                    <c:otherwise><span class="comment-id">${comment.writer.staffId}</span></c:otherwise>
                  </c:choose>
                  <span class="unseen comment-writer-id">${comment.writer.id}</span>
                </div>
                <p class="comment-content">${comment.content}</p>
                <span class="time">${comment.timeFormat}</span>
              </div>
              <div class="comment-sub">
                <span class="recomment-option" id="recomment">대댓글</span>
                <c:if test="${comment.writer.staffId ne curruser.staffId}">
                  <span class="comment-option">쪽지</span>
                </c:if>
                <form action="deleteComment" method ="post">
                <c:if test="${curruser.staffId == comment.writer.staffId}"><button type="submit" class="delete-option" id="commentDelete">삭제</button>
                <input type="hidden" value="${comment.id}" id="deleteId" name="deleteId">
                <input type="hidden" value="${id}" name="postId">
                </c:if>
                </form>
              </div>
            </div>
            <div id="recomment-input" class="recomment-input unseen">
               <div class="recomment-write">
                <form action="recomment" method="post" class="recomment-form" id="comment-form">
                  <input class="recomment-text" type="text" name="comment"
                   maxlength="50" placeholder="댓글을 입력하세요" /> 
                  <input class="submit recomment-submit" type="image"
                   src="<%=request.getContextPath()%>/images/submit.png">
                  <input type="hidden" value="${id}" name="commentId">
                  <input type="hidden" value="${comment.id}" name="referComment">
                </form>
               </div>
             </div>
           </div>
           </c:if>
           <div class="comment-recomment">
            <c:if test="${comment.content =='삭제된 댓글입니다.' and comment.recommentCount >= 1}">
            <div class="comment">
               <input type="hidden" value="false" id="deleteCheck">
              <div class="comment-main">
                <div class="comment-writer">
                  <img 
                    src="<%=request.getContextPath()%>/images/${comment.writer.image}" 
                    alt="" 
                    class="comment-photo" /> 
                    <span class="comment-id">(삭제)</span>
                </div>
                <p class="comment-content">${comment.content}</p>
              </div>  
            </div>
            </c:if>
            </div>
          </c:when>
           <c:when test="${comment.recomment == 'Y' and comment.content !='삭제된 댓글입니다.'}">
            <div class="recomment">
              <div class="comment-main">
                <div class="comment-writer">
                  <img 
                    src="<%=request.getContextPath()%>/images/${comment.writer.image}" 
                    alt=""
                    class="comment-photo" /> 
                 <c:choose>
                    <c:when test="${writer.staffId == comment.writer.staffId}"><span class="comment-currentid">${writer.staffId}(글쓴이)</span></c:when>
                    <c:otherwise><span class="comment-id">${comment.writer.staffId}</span></c:otherwise>
                  </c:choose>
                  <span class="unseen comment-writer-id">${comment.writer.id}</span>
                </div>
                <p class="comment-content">${comment.content}</p>
                <span class="time">${comment.timeFormat}</span>
              </div>
              
              <div class="comment-sub">
               <c:if test="${comment.writer.staffId ne curruser.staffId}">
                 <span class="comment-option">쪽지</span>
               </c:if>
               <form action="deleteComment" method ="post">
                <c:if test="${curruser.staffId == comment.writer.staffId}"><button type="submit" class="recomment-delete" id="commentDelete">삭제</button>
                <input type="hidden" value="${comment.id}" name="deleteId">
                <input type="hidden" value="${id}" name="postId">
                </c:if>
                </form>
              </div>
            </div>
          </c:when>
        </c:choose>
      </c:forEach>
      <div class="comment-input">
        <form action="comment" method="post" class="comment-form">
          <input class="comment-text" type="text" name="comment"
            maxlength="50" placeholder="댓글을 입력하세요" autocomplete="off" minlength="2"/> 
          <input
            class="submit comment-submit" type="image"
            src="<%=request.getContextPath()%>/images/submit.png">
            <input type="hidden" value="${id}" name="commentId">
        </form>
      </div>
      <div class="modal">
      <div class="modal-content">
        <div class="message-modal">
          <form action="<%=request.getContextPath()%>/message/send" class="message-form" method="post">
            <h3 class="message-title">쪽지 보내기</h3>
            <textarea class="message-textarea" name="text" placeholder="내용을 입력해주세요."></textarea>
            <input type="hidden" value="${postId}" name="postId">
            <input type="hidden" value="post" name="origin">
            <input type="submit" value="전송" class="message-submit">
          </form>
          <a class="close-btn">&times;</a>
        </div>
      </div>
    </div>
    </div>
  </div>
  <jsp:include page="/common/bottom.jsp" flush="false" />
  <script src="<%=request.getContextPath()%>/js/boardcontent.js"></script>
</body>
</html>
