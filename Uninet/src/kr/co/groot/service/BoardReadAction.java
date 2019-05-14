package kr.co.groot.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.CommentDao;
import kr.co.groot.dao.PostDao;
import kr.co.groot.dto.Comment;
import kr.co.groot.dto.Post;

public class BoardReadAction implements Action {

  @Override
  public ActionForward execute(HttpServletRequest request,
      HttpServletResponse response) {
    ActionForward forward = null;
    int id = Integer.parseInt(request.getParameter("id"));
    Post post = null;
    List<Comment> commentList = null;
    String boardName = "";

    PostDao postDao = new PostDao();
    CommentDao commentDao = new CommentDao();
    postDao.addCount(id);
    post = postDao.getPost(id);

    boardName = post.getBoardType() == 1 ? "공지사항" : "자유게시판";

    commentList = commentDao.getCommentList(id);

    forward = new ActionForward();
    forward.setRedirect(false);
    forward.setPath("/WEB-INF/views/board/boardcontent.jsp");
    request.setAttribute("boardName", boardName);
    request.setAttribute("post", post);
    request.setAttribute("comments", commentList);
    request.setAttribute("postId", id);

    return forward;
  }
}
