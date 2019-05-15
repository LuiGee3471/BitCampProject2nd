package kr.co.groot.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.CommentDao;

public class BoardDeleteCommentAction implements Action {
  @Override
  public ActionForward execute(HttpServletRequest request,
      HttpServletResponse response) {
    ActionForward forward = null;
    int result = 0;
    int id = Integer.parseInt(request.getParameter("deleteId"));
    int postId = Integer.parseInt(request.getParameter("postId"));

    String msg = "";
    String url = "";
    CommentDao dao = new CommentDao();

    result = dao.deleteComment(id);
    dao.countRecomment(id);

    forward = new ActionForward();
    if (result > 0) {
      forward.setRedirect(true);
      forward.setPath("read?id=" + postId);
    } else {
      msg = "실패하였습니다.";
      url = "board/read?id=" + postId;

      forward.setRedirect(false);
      forward.setPath("/WEB-INF/views/etc/redierct.jsp");
      request.setAttribute("msg", msg);
      request.setAttribute("url", url);
    }

    return forward;
  }
}
