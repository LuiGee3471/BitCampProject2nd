package kr.co.groot.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.StaffDao;
import kr.co.groot.dto.Staff;

public class AdminModifyAction implements Action {

  @Override
  public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
    ActionForward forward = null;
    int id = Integer.parseInt(request.getParameter("id"));
    Staff staff = new Staff();
    try {
      StaffDao dao = new StaffDao();
      staff = dao.selectInfo(id);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    request.setAttribute("staff", staff);
    System.out.println(staff);
    forward = new ActionForward();
    forward.setRedirect(false);
    forward.setPath("/WEB-INF/views/admin/modify.jsp");
    return forward;
  }

}
