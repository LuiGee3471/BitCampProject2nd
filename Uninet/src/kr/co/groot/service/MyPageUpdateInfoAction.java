package kr.co.groot.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.StaffDao;
import kr.co.groot.dto.Staff;

public class MyPageUpdateInfoAction implements Action {

  @Override
  public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
    HttpSession session = request.getSession();
    Staff staff = (Staff) session.getAttribute("staff");
    ActionForward forward = new ActionForward();
    staff.setEmail(request.getParameter("staffEmail"));
    staff.setPhoneNumber(request.getParameter("staffPhone"));
    staff.setStaffName(request.getParameter("staffName"));
    staff.setSelfIntroduce(request.getParameter("selfIntroduce"));
    staff.setId(staff.getId());
    
    String msg = "";
    String url = "";
    StaffDao dao = new StaffDao();
    int row = dao.updateInfo(staff);
    if (row > 0) {
      msg = "수정 성공";
      url = "../mypage";
      session.setAttribute("staff", staff);
    } else {
      msg = "수정 실패";
      url = "setinfo";
    }

    request.setAttribute("msg", msg);
    request.setAttribute("url", url);
    
    forward = new ActionForward();
    forward.setRedirect(false);
    forward.setPath("/WEB-INF/views/etc/redirect.jsp");
    return forward;
  }

}
