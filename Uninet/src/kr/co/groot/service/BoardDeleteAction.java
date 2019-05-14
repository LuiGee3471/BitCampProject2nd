package kr.co.groot.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.PostDao;

public class BoardDeleteAction implements Action {
  @Override
  public ActionForward execute(HttpServletRequest request,
      HttpServletResponse response) {
    ActionForward forward = null;
    String msg = "";
    String url = "";

    PostDao dao = new PostDao();
    int id = Integer.parseInt(request.getParameter("postId"));
    int result = dao.deletePost(id);
    int boardType = Integer.parseInt(request.getParameter("boardType"));

    forward = new ActionForward();
    if (result > 0) {
      forward.setRedirect(true);
      forward.setPath("list?page=1&option=default&boardtype=" + boardType);
    } else {
      msg = "글 삭제를 실패하였습니다.";
      url = "board/read?id=" + id;
      request.setAttribute("msg", msg);
      request.setAttribute("url", url);
      forward.setRedirect(false);
      forward.setPath("/WEB-INF/views/etc/redirect.jsp");
    }

    return forward;
  }
}
