package kr.co.groot.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.StaffDao;
import kr.co.groot.dto.Staff;
import kr.co.groot.page.PaginatorByAdmin;

public class AdminAction implements Action {

  @Override
  public ActionForward execute(HttpServletRequest request,
      HttpServletResponse response) {
    ActionForward forward = null;

    StaffDao dao = new StaffDao();
    PaginatorByAdmin paginator = new PaginatorByAdmin();
    int pageNumber = Integer.parseInt(request.getParameter("page"));
    System.out.println(request.getParameter("option") + "31");
    String option = request.getParameter("option");
    String word = request.getParameter("word");
    System.out.println(option);
    if (word == null) {
      word = "";
    }
    List<Staff> list = null;
    int page = 1;
    switch (option) {
    case "default":
      list = dao.getStaffByPage(pageNumber);
      page = paginator.getPageNumber();
      break;
    case "deptname":
    case "name":
      list = dao.getStaffByOption(pageNumber, option, word);
      page = paginator.getPageNumber(option, word);
      break;
    }
    request.setAttribute("option", option);
    request.setAttribute("word", word);
    request.setAttribute("currentPage", pageNumber);
    request.setAttribute("pages", page);
    request.setAttribute("staffList", list);
    forward = new ActionForward();
    forward.setRedirect(false);
    forward.setPath("/WEB-INF/views/admin/admin.jsp");

    return forward;
  }

}
