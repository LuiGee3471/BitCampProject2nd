package kr.co.groot.service;

import java.util.List;

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
    String id = request.getParameter("id");

    MessageDao messageDao = new MessageDao();
    List<Message> messageList = messageDao.selectUserMessage(user.getId());

    if (isFromMain != null) {
      request.setAttribute("fromMain", true);
      request.setAttribute("id", id);
    } else {
      request.setAttribute("fromMain", false);
      request.setAttribute("id", 0);
    }
    request.setAttribute("messageList", messageList);

    forward = new ActionForward();
    forward.setRedirect(false);
    forward.setPath("/WEB-INF/views/message/message.jsp");
    return forward;
  }
}
