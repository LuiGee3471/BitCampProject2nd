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
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		PaginatorByAdmin paginator = new PaginatorByAdmin();
		int pageNumber = Integer.parseInt(request.getParameter("page"));

		StaffDao dao = null;
		List<Staff> staffList = null;
		int page = 1;

		try {
			dao = new StaffDao();
			staffList = dao.getStaffByPage(pageNumber);
			page = paginator.getStaffPageNumber();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		request.setAttribute("staffList", staffList);
		request.setAttribute("currentPage", pageNumber);
		request.setAttribute("pages", page);
		
		forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/views/admin/admin.jsp");
		
		return forward;
	}

}
