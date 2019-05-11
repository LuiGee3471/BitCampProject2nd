package kr.co.groot.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.StaffDao;

public class MyPageDeleteAction implements Action{

  @Override
  public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
    
    ActionForward forward = new ActionForward();
    
    int id = Integer.parseInt(request.getParameter("id"));
    
    
    StaffDao dao;
    int result = 0;
    String msg = "";
    String url = "admin?page=1&option=default";
    
    try {
      dao = new StaffDao();
      result = dao.deleteStaff(id);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    if(result > 0) {
      msg = "삭제되었습니다";
      
    } else {
      msg = "삭제되지 않았습니다";
    }
    
    request.setAttribute("msg", msg);
    request.setAttribute("url", url);
    
    forward.setRedirect(false);
    forward.setPath("/WEB-INF/views/redirect.jsp");
    
    return forward;
  }

}
