package kr.co.groot.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.LectureDao;

public class LectureDeleteAction implements Action {
  @Override
  public ActionForward execute(HttpServletRequest request,
      HttpServletResponse response) {

    ActionForward forward = null;

    int id = Integer.parseInt(request.getParameter("id"));
    int result = 0;

    LectureDao dao = new LectureDao();
    result = dao.deleteLecture(id);

    String msg = "";
    String url = "list";

    if (result > 0) {
      msg = "삭제되었습니다";

    } else {
      msg = "삭제되지 않았습니다";
    }

    request.setAttribute("msg", msg);
    request.setAttribute("url", url);

    forward = new ActionForward();
    forward.setRedirect(false);
    forward.setPath("/WEB-INF/views/etc/redirect.jsp");

    return forward;
  }
}
