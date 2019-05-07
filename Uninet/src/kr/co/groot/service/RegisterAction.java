package kr.co.groot.service;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.DepartmentDao;

public class RegisterAction implements Action {
  @Override
  public ActionForward execute(HttpServletRequest request,
      HttpServletResponse response) {
    ActionForward forward = null;
    
    DepartmentDao dao;
    List<String> nameList;
    
    try {
      dao = new DepartmentDao();
      nameList = dao.getDistinctDeptName();
      
      request.setAttribute("deptList", nameList);
      
      forward = new ActionForward();
      forward.setRedirect(false);
      forward.setPath("/WEB-INF/views/register.jsp");
    } catch (NamingException | SQLException e) {
      System.out.println(e.getMessage());
    }
    
    return forward;
  }
}
