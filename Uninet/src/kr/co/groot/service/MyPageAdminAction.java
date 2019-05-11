package kr.co.groot.service;

import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.StaffDao;
import kr.co.groot.dto.Staff;
import kr.co.groot.page.PaginatorByAdmin;

public class MyPageAdminAction implements Action {

  @Override
  public ActionForward execute(HttpServletRequest request,
      HttpServletResponse response) {
    ActionForward forward = new ActionForward();
    
	try {
		StaffDao dao = new StaffDao();
		PaginatorByAdmin paginator = new PaginatorByAdmin();
		int pageNumber = Integer.parseInt(request.getParameter("page"));
		String option = request.getParameter("option");
		String word = request.getParameter("word");
		if(word == null) {
			word = "";
		}
		List<Staff> list = null;
		int page = 1;
		switch(option) {
		case "default" :
			list =  dao.getStaffByPage(pageNumber);
			page = paginator.getPageNumber();
			break;
		case "deptname" :
		case "name" :
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
		forward.setPath("/WEB-INF/views/mypage/admin.jsp");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    return forward;
  }

}
