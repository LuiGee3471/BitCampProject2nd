package kr.co.groot.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.groot.dao.StaffDao;
import kr.co.groot.dto.Staff;


@WebServlet("/idcheck")
public class IDCheckAjax extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public IDCheckAjax() {
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
  }

  protected void doPost(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    PrintWriter out = response.getWriter();
    StaffDao dao = new StaffDao();
    String id = request.getParameter("id");
    Staff staff = dao.selectStaff(id);
    if (staff.getId() == 0) {
      out.print("not exists");
    } else {
      out.print("exists");
    }
  }

}
