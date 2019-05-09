package kr.co.groot.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;

public class MyPageMyAction implements Action {

  @Override
  public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
    ActionForward forward = new ActionForward();
    
    forward.setRedirect(false);
    forward.setPath("/WEB-INF/views/mypage/my.jsp");
    
    return forward;
  }

}
