package kr.co.groot.service;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.MessageDao;

public class MessageDeleteAction implements Action {

  @Override
  public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
    ActionForward forward = null;
    int message_id = Integer.parseInt(request.getParameter("id"));

    try {
      MessageDao messageDao = new MessageDao();
      messageDao.deleteMessage(message_id);

      forward = new ActionForward();
      forward.setRedirect(true);
      forward.setPath(request.getContextPath() + "/message");
    } catch (NamingException | SQLException e) {
      System.out.println("Message Delete: " + e.getMessage());
    }

    return forward;
  }
}
