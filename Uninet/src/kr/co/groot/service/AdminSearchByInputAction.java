package kr.co.groot.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.StaffDao;
import kr.co.groot.dto.Staff;

public class AdminSearchByInputAction implements Action {

  @Override
  public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
    ActionForward forward = new ActionForward();
    List<Staff> staffList = new ArrayList<Staff>();
    String input = request.getParameter("searchText");
    String search = request.getParameter("orderBy");

    System.out.println("input : " + input);
    System.out.println("search : " + search);
    StaffDao dao;
    try {
      dao = new StaffDao();
      staffList = dao.selectByName(search, input);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    request.setAttribute("staffList", staffList);
    forward.setRedirect(false);
    forward.setPath("/WEB-INF/views/admin/searchResult.jsp");
    return forward;
  }

}
