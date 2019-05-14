package kr.co.groot.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.DepartmentDao;
import kr.co.groot.dao.StaffDao;
import kr.co.groot.dto.Department;
import kr.co.groot.dto.Staff;

public class AdminModifyAction implements Action {
  @Override
  public ActionForward execute(HttpServletRequest request,
      HttpServletResponse response) {
    ActionForward forward = null;
    int id = Integer.parseInt(request.getParameter("id"));
    Staff staff = new Staff();
    List<Department> departList = new ArrayList<Department>(); 
    StaffDao dao = new StaffDao();
    staff = dao.selectInfo(id);
    DepartmentDao departdao = new DepartmentDao();
    departList = departdao.getDistinctDeptName();
    request.setAttribute("staff", staff);
    request.setAttribute("departList", departList);
    forward = new ActionForward();
    forward.setRedirect(false);
    forward.setPath("/WEB-INF/views/admin/modify.jsp");
    return forward;
  }
}
