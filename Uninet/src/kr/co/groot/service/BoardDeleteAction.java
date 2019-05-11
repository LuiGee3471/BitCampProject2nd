package kr.co.groot.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.PostDao;

public class BoardDeleteAction implements Action {

  @Override
  public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
    ActionForward forward = null;
    String msg = "";
    String url = "";
    
    try {
      PostDao dao = new PostDao();
      int id = Integer.parseInt(request.getParameter("id"));
      int result = dao.deletePost(id);
      
      if(result>0) {
        msg = "글 삭제 성공";
        url = "board/list";
      }else {
        msg = "글 삭제 실패";
        url = "board/read";
      }
      forward = new ActionForward();
      forward.setRedirect(false);
      forward.setPath("/WEB-INF/views/etc/redirect.jsp"); 
    }catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return forward;
  }

}
