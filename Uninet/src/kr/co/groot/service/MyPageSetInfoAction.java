package kr.co.groot.service;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.StaffDao;
import kr.co.groot.dto.Staff;

public class MyPageSetInfoAction implements Action {

  @Override
  public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
    ActionForward forward = null;
    StaffDao dao;
    Staff staff;
    try {
      HttpSession session = request.getSession();
      String id = (String)session.getAttribute("id");
      dao = new StaffDao();
      staff = dao.selectStaff(id);
      request.setAttribute("staffInfo", staff);
      forward = new ActionForward();
      forward.setRedirect(false);
      forward.setPath("/WEB-INF/views/mypage/setinfo.jsp");
    }catch(SQLException e) {
      System.out.println(e.getMessage());
    }catch(NamingException e) {
      System.out.println(e.getMessage());
    }
    return forward;
  }

}
