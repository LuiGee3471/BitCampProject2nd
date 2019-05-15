package kr.co.groot.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.service.MessageDeleteAction;
import kr.co.groot.service.MessagePageAction;
import kr.co.groot.service.MessageSendAction;

@WebServlet({ "/message/*", "/message" })
public class MessageController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public MessageController() {

  }

  private void doProcess(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    String requestUri = request.getRequestURI();
    String contextPath = request.getContextPath();
    String urlCommand = requestUri.substring(contextPath.length());

    Action action = null;
    ActionForward forward = null;

    if (urlCommand.equals("/message/send")) {
      action = new MessageSendAction();
      forward = action.execute(request, response);
    } else if (urlCommand.equals("/message")) {
      action = new MessagePageAction();
      forward = action.execute(request, response);
    } else if (urlCommand.equals("/message/delete")) {
      action = new MessageDeleteAction();
      forward = action.execute(request, response);
    } else {
      forward = new ActionForward();
      forward.setRedirect(false);
      forward.setPath("/WEB-INF/views/etc/error_404.jsp");
    }

    if (forward != null) {
      if (forward.isRedirect()) {
        response.sendRedirect(forward.getPath());
      } else {
        RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
        dis.forward(request, response);
      }
    }
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doProcess(request, response);
  }

  protected void doPost(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    doProcess(request, response);
  }

}
