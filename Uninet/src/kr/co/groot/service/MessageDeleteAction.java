package kr.co.groot.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.MessageDao;

public class MessageDeleteAction implements Action {
  @Override
  public ActionForward execute(HttpServletRequest request,
      HttpServletResponse response) {
    ActionForward forward = null;
    int message_id = Integer.parseInt(request.getParameter("id"));

    MessageDao messageDao = new MessageDao();
    messageDao.deleteMessage(message_id);

    forward = new ActionForward();
    forward.setRedirect(true);
    forward.setPath(request.getContextPath() + "/message");

    return forward;
  }
}
