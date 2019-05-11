package kr.co.groot.service;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.MessageDao;
import kr.co.groot.dto.Staff;

public class MessagePageAction implements Action {
  @Override
  public ActionForward execute(HttpServletRequest request,
      HttpServletResponse response) {
    ActionForward forward = null;
    HttpSession session = request.getSession();
    Staff user = (Staff) session.getAttribute("staff");
    
    try {
		MessageDao messageDao = new MessageDao();
		
	} catch (NamingException e) {
		System.out.println("MessagePageAction: " + e.getMessage());
	}
    
    forward = new ActionForward();
    forward.setRedirect(false);
    forward.setPath("/WEB-INF/views/message/message.jsp");
    return forward;
  }
}
