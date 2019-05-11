package kr.co.groot.ajax;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.MessageDao;
import kr.co.groot.dto.Message;

@WebServlet("/message/call")
public class MessageAjax extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public MessageAjax() {

  }

  private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int id = Integer.parseInt(request.getParameter("id"));
    
    ActionForward forward = null;
    try {
      MessageDao messageDao = new MessageDao();
      Message message = messageDao.selectMessage(id);
      
      request.setAttribute("message", message);
      
      forward = new ActionForward();
      forward.setRedirect(false);
      forward.setPath("/WEB-INF/views/message/messagePaper.jsp");
    } catch (NamingException | SQLException e) {
      System.out.println("MessageAjax: " + e.getMessage());
    }
    
    RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
    dis.forward(request, response);    
  }
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doProcess(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doProcess(request, response);
  }

}
