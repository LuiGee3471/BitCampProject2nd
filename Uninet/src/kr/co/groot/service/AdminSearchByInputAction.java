package kr.co.groot.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.StaffDao;
import kr.co.groot.dto.Staff;
import kr.co.groot.page.PaginatorByAdmin;

public class AdminSearchByInputAction implements Action {
  @Override
  public ActionForward execute(HttpServletRequest request,
      HttpServletResponse response) {
    ActionForward forward = new ActionForward();

    List<Staff> list = null;
    int pageNumber = Integer.parseInt(request.getParameter("page"));
    System.out.println("pageNumber : " + pageNumber);
    String method = request.getParameter("method");
    System.out.println("method : " + method);
    String option = request.getParameter("option");
    System.out.println("option : " + option);
    String word = request.getParameter("word");
    System.out.println("ok1");
    int page = 1;
    StaffDao dao;

    dao = new StaffDao();
    PaginatorByAdmin paginator = new PaginatorByAdmin();

    switch (method) {
    case "default":
      page = paginator.getStaffPageNumber();
      list = dao.getStaffByPage(pageNumber);
      break;
    case "name":
      page = paginator.getStaffPageNumberByOption(option, word);
      list = dao.getStaffByOption(pageNumber, option, word);
      break;
    case "deptName":
      page = paginator.getStaffPageNumberByOption(option, word);
      list = dao.getStaffByOption(page, option, word);
      break;
    }

    request.setAttribute("currentPage", pageNumber);
    request.setAttribute("totalPages", page);
    request.setAttribute("staffList", list);

    forward.setRedirect(false);
    System.out.println("액션타냐고");
    forward.setPath("/WEB-INF/views/admin/searchResult.jsp");
    return forward;
  }

}
