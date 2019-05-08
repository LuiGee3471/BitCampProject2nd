package kr.co.groot.service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.LectureDao;

public class LectureDeleteAction implements Action{

  @Override
  public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
      
    ActionForward forward = null;
    
    int id = Integer.parseInt(request.getParameter("id"));
    int result = 0;
    try {
      LectureDao dao = new LectureDao();
      result = dao.deleteLecture(id);
      System.out.println("결과 : " + result);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    String msg = "";
    String url = "list";
    
    if(result > 0) {
      msg = "삭제되었습니다";
      
    } else {
      msg = "삭제되지 않았습니다";
    }
    
    request.setAttribute("msg", msg);
    request.setAttribute("url", url);
    
    forward = new ActionForward();
    forward.setRedirect(false);
    forward.setPath("/WEB-INF/views/redirect.jsp");
    
    return forward;
  }

}
