package kr.co.groot.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.StaffDao;
import kr.co.groot.dto.Staff;

public class SignRegisterCheckAction implements Action {
  @Override
  public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
    ActionForward forward = null;
    int deptId = Integer.parseInt(request.getParameter("dept"));
    String id = request.getParameter("id");
    String password = request.getParameter("password");
    String email = request.getParameter("email");
    String name = request.getParameter("name");
    LocalDate birthdate = LocalDate.parse(request.getParameter("birthday"));
    Timestamp birthday = Timestamp.valueOf(birthdate.atStartOfDay());
    String phoneNumber = request.getParameter("phoneNumber");

    try {
      Staff staff = new Staff();
      staff.setStaffId(id);
      staff.setPassword(password);
      staff.setEmail(email);
      staff.setStaffName(name);
      staff.setBirthday(birthday);
      staff.setPhoneNumber(phoneNumber);
      staff.setDeptId(deptId);

      StaffDao dao = new StaffDao();
      int row = dao.insertStaff(staff);

      staff = dao.selectStaff(id);

      if (row > 0) {
        HttpSession session = request.getSession();
        session.setAttribute("staff", staff);
        forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("/registerOK");
      } else {
        forward = new ActionForward();
        String msg = "회원 가입이 실패했습니다.";
        String url = "register";
        request.setAttribute("msg", msg);
        request.setAttribute("url", url);
        forward.setRedirect(false);
        forward.setPath("/WEB-INF/views/etc/redirect.jsp");
      }
    } catch (NamingException | SQLException e) {
      System.out.println(e.getMessage());
    }

    return forward;
  }
}
