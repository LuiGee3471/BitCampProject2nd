package kr.co.groot.service;

import java.sql.Timestamp;
import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.StaffDao;
import kr.co.groot.dto.Staff;

public class StaffInfoAction implements Action {

  @Override
  public ActionForward execute(HttpServletRequest request,
      HttpServletResponse response) {
    ActionForward forward = new ActionForward();
    int id = Integer.parseInt(request.getParameter("id"));
    
    StaffDao staffDao = new StaffDao();
    Staff staff = staffDao.selectByUniqueId(id);
    
    Timestamp birthday = staff.getBirthday();
    LocalDate newBirthday = birthday.toLocalDateTime().toLocalDate();
    String birthdayString = newBirthday.toString();
    
    request.setAttribute("staff", staff);
    request.setAttribute("birthday", birthdayString);
    
    forward.setRedirect(false);
    forward.setPath("/WEB-INF/views/mypage/staffInfo.jsp");
    
    return forward;
  }
}
