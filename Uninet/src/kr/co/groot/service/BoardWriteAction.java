package kr.co.groot.service;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.PostDao;
import kr.co.groot.dto.Post;
import kr.co.groot.dto.Staff;

public class BoardWriteAction implements Action {

  @Override
  public ActionForward execute(HttpServletRequest request,
      HttpServletResponse response) {
    ActionForward forward = null;
    ServletContext application = request.getServletContext();
    String uploadPath = application.getRealPath("file");


    int size = 1024 * 1024 * 5;

    try {
      MultipartRequest multi = new MultipartRequest(request, uploadPath, size,
          "UTF-8", new DefaultFileRenamePolicy());

      HttpSession session = request.getSession();
      Staff staff = (Staff) session.getAttribute("staff");

      int result = 0;
      Post post = new Post();
      String url = "";
      String msg = "";

      String title = multi.getParameter("title");
      String content = multi.getParameter("content");
      int boardType = Integer.parseInt(multi.getParameter("boardType"));

      Enumeration<String> filenames = multi.getFileNames();
      String file = filenames.nextElement();
      String filename = multi.getFilesystemName(file);

      post.setTitle(title);
      post.setContent(content);
      post.setWriterId(staff.getId());
      post.setBoardType(Integer.parseInt(multi.getParameter("boardType")));
      post.setFileName(filename);

      PostDao dao = new PostDao();
      result = dao.insertPost(post);

      forward = new ActionForward();

      if (result > 0) {
        request.setAttribute("boardType", boardType);
        forward.setRedirect(false);
        forward.setPath("/board/writeok");
      } else {
        msg = "실패하였습니다.";
        url = "board/list?page=1&option=false&boardtype=" + boardType;
        forward.setRedirect(false);
        forward.setPath("/WEB-INF/views/etc/redirect.jsp");
        request.setAttribute("msg", msg);
        request.setAttribute("url", url);
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return forward;
  }
}
