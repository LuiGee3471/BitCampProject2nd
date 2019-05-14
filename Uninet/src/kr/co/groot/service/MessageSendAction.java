package kr.co.groot.service;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.MessageDao;
import kr.co.groot.dao.StaffDao;
import kr.co.groot.dto.Message;
import kr.co.groot.dto.Staff;

public class MessageSendAction implements Action {

  @Override
  public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
    ActionForward forward = null;
    int id = Integer.parseInt(request.getParameter("receiver"));
    String text = request.getParameter("text");
    HttpSession session = request.getSession();
    Staff user = (Staff) session.getAttribute("staff");
    String postId = request.getParameter("postId");
    String origin = request.getParameter("origin");

    System.out.println(id);

    try {
      StaffDao staffDao = new StaffDao();
      Staff staff = staffDao.selectByUniqueId(id);
      System.out.println(staff.getId());
      Message message = new Message();
      MessageDao messageDao = new MessageDao();
      System.out.println(id + text + staff.getId() + user.getId() + postId);
      message.setContent(text);
      message.setReceiverId(staff.getId());
      message.setSenderId(user.getId());

      messageDao.insertMessage(message);

      forward = new ActionForward();
      forward.setRedirect(true);
      if (origin.equals("post")) {
        forward.setPath(request.getContextPath() + "/board/read?id=" + postId);
      } else {
        forward.setPath(request.getContextPath() + "/message");
      }
    } catch (NamingException e) {
      System.out.println("MessageSendAction : " + e.getMessage());
    } catch (SQLException e) {
      System.out.println("MessageSendAction : " + e.getMessage());
    }
    return forward;
  }

}
