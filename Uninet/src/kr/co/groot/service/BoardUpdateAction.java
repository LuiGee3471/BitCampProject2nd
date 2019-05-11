package kr.co.groot.service;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.PostDao;
import kr.co.groot.dto.Post;

public class BoardUpdateAction implements Action {

  @Override
  public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
    ActionForward forward = null;
    String msg = "";
    String url = "";

    try {
      int result = 0;
      Post post = new Post();
      int id = Integer.parseInt(request.getParameter("id"));
      String title = request.getParameter("title");
      String content = request.getParameter("content");
      int writerId = Integer.parseInt(request.getParameter("writer_id"));
      Timestamp time = Timestamp.valueOf(request.getParameter("time"));
      int count = Integer.parseInt(request.getParameter("count"));
      int boardType = Integer.parseInt(request.getParameter("boardtype_id"));

      PostDao dao = new PostDao();
      result = dao.updatePost(post);

      if (result > 0) {
        msg = "글이 성공적으로 수정되었습니다.";
        url = "board/read";

      } else {
        msg = "실패하였습니다.";
        url = "board/update";
      }
      request.setAttribute("msg", msg);
      request.setAttribute("url", url);

      forward = new ActionForward();
      forward.setRedirect(false);
      forward.setPath("/WEB-INF/views/etc/redirect.jsp");

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    return forward;
  }

}
