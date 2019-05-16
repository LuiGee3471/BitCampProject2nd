package kr.co.groot.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.StaffDao;
import kr.co.groot.dto.Staff;

public class MyPagePwdUpdateAction implements Action {
  @Override
  public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
    ActionForward forward = null;
    HttpSession session = request.getSession();
    Staff staff = (Staff) session.getAttribute("staff");

    staff.setPassword(request.getParameter("updatePwd"));
    staff.setId(staff.getId());

    String msg = "";
    String url = "";
    StaffDao dao = new StaffDao();
    int row = dao.updatePwd(staff);
    
    if (row > 0) {
      msg = "수정 성공";
      url = "../mypage";
    } else {
      msg = "수정 실패";
      url = "setpassword";
    }
    
    request.setAttribute("msg", msg);
    request.setAttribute("url", url);
    forward = new ActionForward();
    forward.setRedirect(false);
    forward.setPath("/WEB-INF/views/etc/redirect.jsp");

    return forward;
  }
}
