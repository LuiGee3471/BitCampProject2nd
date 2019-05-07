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
import kr.co.groot.service.LoginAction;

@WebServlet(description = "로그인, 회원가입 처리", urlPatterns = { "/login", "/register" })
public class SignController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public SignController() {
    
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {    
    String requestUri = request.getRequestURI();
    String contextPath = request.getContextPath();
    String urlCommand = requestUri.substring(contextPath.length());
    
    Action action = null;
    ActionForward forward = null;
    
    if (urlCommand.equals("/login")) {
      action = new LoginAction();
      forward = action.execute(request, response);
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
}
