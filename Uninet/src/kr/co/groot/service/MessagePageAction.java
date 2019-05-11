package kr.co.groot.service;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.MessageDao;
import kr.co.groot.dto.Message;
import kr.co.groot.dto.Staff;

public class MessagePageAction implements Action {
  @Override
  public ActionForward execute(HttpServletRequest request,
      HttpServletResponse response) {
    ActionForward forward = null;
    HttpSession session = request.getSession();
    Staff user = (Staff) session.getAttribute("staff");
    String isFromMain = request.getParameter("fromMain");
    int id = Integer.parseInt(request.getParameter("id"));
    
    try {
		MessageDao messageDao = new MessageDao();
		List<Message> messageList = messageDao.selectUserMessage(user.getId());
		
		if (isFromMain != null) {
		  request.setAttribute("fromMain", true);
		  request.setAttribute("id", id);
		}
		request.setAttribute("messageList", messageList);	
	} catch (NamingException | SQLException e) {
		System.out.println("MessagePageAction: " + e.getMessage());
	}
    
    forward = new ActionForward();
    forward.setRedirect(false);
    forward.setPath("/WEB-INF/views/message/message.jsp");
    return forward;
  }
}
