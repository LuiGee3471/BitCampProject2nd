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
  public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
    ActionForward forward = new ActionForward();

    List<Staff> list = null;
    int pageNumber = Integer.parseInt(request.getParameter("page"));
    String method = request.getParameter("method");
    String option = request.getParameter("option");
    String word = request.getParameter("word");
    int page = 1;
    StaffDao dao;
    try {
    	dao = new StaffDao();
    	PaginatorByAdmin paginator = new PaginatorByAdmin();
    	
    	switch(method) {
    	case "default" :
    	  page = paginator.getStaffPageNumber();
    	  list = dao.getStaffByPage(pageNumber);
    	  break;
    	case "name" :
    	case "deptName" :
    	  page = paginator.getStaffPageNumberByOption(option, word);
    		list = dao.getStaffByOption(pageNumber, option, word);
    		break;
    	}
    }catch(Exception e) {
    	System.out.println(e.getMessage());
    }
    request.setAttribute("method", method);
    request.setAttribute("option", option);
    request.setAttribute("word", word);
    request.setAttribute("currentPage", pageNumber);
    request.setAttribute("totalPages", page);
    request.setAttribute("staffList", list);
//    StaffDao dao;
//    try {
//      dao = new StaffDao();
//      staffList = dao.selectByName(search, input);
//    } catch (Exception e) {
//      System.out.println(e.getMessage());
//    }
//    request.setAttribute("staffList", staffList);
    forward.setRedirect(false);
    System.out.println("액션타냐고");
    forward.setPath ("/WEB-INF/views/admin/searchResult.jsp");
    return forward;
  }

}
