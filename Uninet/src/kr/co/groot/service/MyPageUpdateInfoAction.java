package kr.co.groot.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.StaffDao;
import kr.co.groot.dto.Staff;

public class MyPageUpdateInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String id = request.getParameter("staff_Id");
		ActionForward forward = new ActionForward();
		Staff staff = new Staff();
		System.out.println("id의 값은 : " +id);
		staff.setEmail(request.getParameter("staffEmail"));
		staff.setPhoneNumber(request.getParameter("staffPhone"));
		staff.setStaffName(request.getParameter("staffName"));
		staff.setStaffId(id);
		
			String msg = "";
			String url = "";
			try {
			StaffDao dao = new StaffDao();
			System.out.println("1");
			int row = dao.updateInfo(staff);
			System.out.println("2");
			if (row > 0) {
				msg = "수정 성공";
				url = "mypage";
			} else {
				msg = "수정 실패";
				url = "setinfo";
			}
			System.out.println(row);
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/views/redirect.jsp");
		return forward;
	}

}
