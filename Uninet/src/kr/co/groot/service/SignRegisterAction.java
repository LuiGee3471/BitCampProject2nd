package kr.co.groot.service;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.DepartmentDao;

public class SignRegisterAction implements Action {
  @Override
  public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
    ActionForward forward = null;

    DepartmentDao dao;
    List<String> nameList;

    dao = new DepartmentDao();
    nameList = dao.getDistinctDeptName();

    request.setAttribute("deptList", nameList);

    forward = new ActionForward();
    forward.setRedirect(false);
    forward.setPath("/WEB-INF/views/sign/register.jsp");

    return forward;
  }
}
