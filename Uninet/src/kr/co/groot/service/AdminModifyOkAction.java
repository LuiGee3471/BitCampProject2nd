package kr.co.groot.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.StaffDao;
import kr.co.groot.dto.Staff;

public class AdminModifyOkAction implements Action {

  @Override
  public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
    ActionForward forward = null;
    HttpSession session = request.getSession();
    Staff staff = (Staff) session.getAttribute("staff");
    staff.setPhoneNumber(request.getParameter("phoneNumber"));
    staff.setIsAdmin(request.getParameter("isAdmin"));
    staff.setIsManager(request.getParameter("isManager"));
    staff.setId(staff.getId());
    staff.setStaffName(staff.getStaffName());
    staff.setEmail(staff.getEmail());
    staff.setBirthday(staff.getBirthday());

    String msg = "";
    String url = "";

    try {
      StaffDao dao = new StaffDao();

      int row = dao.modifyStaff(staff);
      if (row > 0) {
        msg = "수정 성공";
        url = "admin?page=1&option=default";
      } else {
        msg = "수정 실패";
        url = "modify";
      }
      request.setAttribute("msg", msg);
      request.setAttribute("url", url);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    forward = new ActionForward();
    forward.setRedirect(false);
    forward.setPath("/WEB-INF/views/etc/redirect.jsp");

    return forward;
  }

}
