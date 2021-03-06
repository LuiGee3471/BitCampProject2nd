package kr.co.groot.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.CommentDao;
import kr.co.groot.dto.Comment;
import kr.co.groot.dto.Staff;

public class BoardCommentAction implements Action {

  @Override
  public ActionForward execute(HttpServletRequest request,
      HttpServletResponse response) {
    ActionForward forward = null;
    int id = Integer.parseInt(request.getParameter("commentId"));
    Comment comment = new Comment();
    int result = 0;

    HttpSession session = request.getSession();
    Staff staff = (Staff) session.getAttribute("staff");
    CommentDao dao = new CommentDao();
    String url = "";
    String msg = "";

    comment.setContent(request.getParameter("comment"));
    comment.setWriterId(staff.getId());
    comment.setWriter(staff);
    comment.setRefer(id);
    result = dao.insertComment(comment);

    forward = new ActionForward();

    if (result > 0) {
      forward.setRedirect(true);
      forward.setPath("read?id=" + id);
    } else {
      msg = "실패하였습니다.";
      url = "board/read?id=" + id;
      forward.setRedirect(false);
      forward.setPath("/WEB-INF/views/etc/redirect.jsp");
      request.setAttribute("msg", msg);
      request.setAttribute("url", url);
    }

    return forward;
  }

}
