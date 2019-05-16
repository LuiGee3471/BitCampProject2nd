package kr.co.groot.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.service.AdminAction;
import kr.co.groot.service.AdminModifyAction;
import kr.co.groot.service.AdminModifyOkAction;
import kr.co.groot.service.AdminSearchByInputAction;
import kr.co.groot.service.AdminDeleteAction;

@WebServlet(description = "관리자페이지 작업 처리", urlPatterns = { "/admin/*" })
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminController() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String urlCommand = requestUri.substring(contextPath.length());

		Action action = null;
		ActionForward forward = null;

		if (urlCommand.equals("/admin")) {
			action = new AdminAction();
			forward = action.execute(request, response);
		} else if (urlCommand.equals("/admin/modify")) {
			action = new AdminModifyAction();
			forward = action.execute(request, response);
		} else if (urlCommand.equals("/admin/modifyOk")) {
			action = new AdminModifyOkAction();
			forward = action.execute(request, response);
		} else if (urlCommand.equals("/admin/search")) {
			action = new AdminSearchByInputAction();
			forward = action.execute(request, response);
		} else if (urlCommand.equals("/admin/delete")) {
			action = new AdminDeleteAction();
			forward = action.execute(request, response);
		}

		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);

			}
		}

	}
}
