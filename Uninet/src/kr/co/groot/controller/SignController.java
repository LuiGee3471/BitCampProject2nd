package kr.co.groot.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.service.MainAction;
import kr.co.groot.service.SignLoginAction;
import kr.co.groot.service.SignRegisterAction;
import kr.co.groot.service.SignRegisterCheckAction;

@WebServlet(description = "로그인, 회원가입 처리", urlPatterns = { "/login",
    "/register", "/registerCheck", "/main", "/logout", "/registerOK" })
public class SignController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public SignController() {

  }

  private void doProcess(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    String requestUri = request.getRequestURI();
    String contextPath = request.getContextPath();
    String urlCommand = requestUri.substring(contextPath.length());

    Action action = null;
    ActionForward forward = null;

    if (urlCommand.equals("/login")) {
      action = new SignLoginAction();
      forward = action.execute(request, response);
    } else if (urlCommand.equals("/register")) {
      action = new SignRegisterAction();
      forward = action.execute(request, response);
    } else if (urlCommand.equals("/registerCheck")) {
      action = new SignRegisterCheckAction();
      forward = action.execute(request, response);
    } else if (urlCommand.equals("/registerOK")) {
      forward = new ActionForward();
      forward.setRedirect(true);
      forward.setPath("main");
    } else if (urlCommand.equals("/main")) {
      action = new MainAction();
      forward = action.execute(request, response);
    } else if (urlCommand.equals("/logout")) {
      HttpSession session = request.getSession();
      session.invalidate();
      forward = new ActionForward();
      forward.setRedirect(true);
      forward.setPath(request.getContextPath());
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
