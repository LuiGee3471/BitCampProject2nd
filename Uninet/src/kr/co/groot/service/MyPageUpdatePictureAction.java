package kr.co.groot.service;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.StaffDao;
import kr.co.groot.dto.Staff;

public class MyPageUpdatePictureAction implements Action {

  @Override
  public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
    String uploadpath = request.getServletContext().getRealPath("images");
    System.out.println(uploadpath);
    int size = 1024 * 1024 * 10;

    HttpSession session = request.getSession();
    Staff staff = (Staff) session.getAttribute("staff");

    ActionForward forward = new ActionForward();
    staff.setId(staff.getId());

    String msg = "";
    String url = "";
    try {
      MultipartRequest multi = new MultipartRequest(request, uploadpath, size, "UTF-8", new DefaultFileRenamePolicy());
      Enumeration<String> filenames = multi.getFileNames();
      String file = filenames.nextElement();
      String image = multi.getFilesystemName(file);
      staff.setImage(image);
      StaffDao dao = new StaffDao();
      int row = dao.updateImage(staff);
      if (row > 0) {
        msg = "수정 성공";
        url = "../mypage";
        staff = dao.selectByUniqueId(staff.getId());
        session.setAttribute("staff", staff);
        ;
      } else {
        msg = "수정실패";
        url = "setpicture";
      }
      request.setAttribute("msg", msg);
      request.setAttribute("url", url);

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    forward = new ActionForward();
    forward.setRedirect(false);
    forward.setPath("/WEB-INF/views/etc/redirect.jsp");
    return forward;
  }

}
