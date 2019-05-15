package kr.co.groot.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.StaffDao;
import kr.co.groot.dto.Staff;

public class SignLoginAction implements Action {
  @Override
  public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
    ActionForward forward = null;
    HttpSession session = request.getSession();

    String id = request.getParameter("id");
    String password = request.getParameter("password");

    try {
      StaffDao dao = new StaffDao();
      Staff staff = dao.selectStaff(id);

      String correctPassword = staff.getPassword();

      if (password.equals(correctPassword)) {
        session.setAttribute("staff", staff);
        response.sendRedirect("main");
      } else {
        forward = new ActionForward();
        String msg = "아이디나 비밀번호를 바르게 입력해주세요.";
        String url = "index.html";
        request.setAttribute("msg", msg);
        request.setAttribute("url", url);
        forward.setRedirect(false);
        forward.setPath("/WEB-INF/views/etc/redirect.jsp");
      }
    } catch (IOException e) {
      System.out.println("SignLoginAction: " + e.getMessage());
    }

    return forward;
  }
}
