package kr.co.groot.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.StaffDao;
import kr.co.groot.dto.Staff;

public class MyPageAdminSearchByInputAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		List<Staff> staffList = new ArrayList<Staff>();
		System.out.println("123");
		String input = request.getParameter("searchInput");
		String search = request.getParameter("orderBy");
		System.out.println(input+"222222");
		System.out.println(search+"111111");
		StaffDao dao;
		try {
			dao = new StaffDao();
			staffList = dao.selectByName(search , input);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		request.setAttribute("staffList", staffList);
		System.out.println(staffList);
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/views/mypage/searchResult.jsp");
		return forward;
	}

}
