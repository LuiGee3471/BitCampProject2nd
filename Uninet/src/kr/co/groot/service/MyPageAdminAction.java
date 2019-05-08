package kr.co.groot.service;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.StaffDao;
import kr.co.groot.dto.Staff;

public class MyPageAdminAction implements Action {

  @Override
  public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
    ActionForward forward = null;
    try {
      HttpSession session = request.getSession();
      String id = (String)session.getAttribute("id");
      StaffDao dao = new StaffDao();
      Staff staff = dao.selectStaff(id);
      List<Staff> staffList = dao.selectAll();
      request.setAttribute("staffInfo", staff);
      request.setAttribute("staffList", staffList);
      forward = new ActionForward();
      forward.setRedirect(false);
      forward.setPath("/WEB-INF/views/mypage/admin.jsp");
    }catch(SQLException e) {
      System.out.println(e.getMessage());
    }catch(NamingException e) {
      System.out.println(e.getMessage());
    }
    return forward;
  }

}
