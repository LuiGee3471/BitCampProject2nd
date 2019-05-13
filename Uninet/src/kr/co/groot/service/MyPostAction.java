package kr.co.groot.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.PostDao;
import kr.co.groot.dto.Post;
import kr.co.groot.dto.Staff;
import kr.co.groot.page.Paginator;

public class MyPostAction implements Action {

  @Override
  public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
    ActionForward forward = null;
    List<Post> list = null;
    PostDao postDao = null;
    HttpSession session = request.getSession();
    Staff loggedUser = (Staff) session.getAttribute("staff");
    String option = request.getParameter("option");
    String word = request.getParameter("word");
    int pageNumber = Integer.parseInt(request.getParameter("page"));

    try {
      int id = loggedUser.getId();
      Paginator paginator = new Paginator();
      postDao = new PostDao();
      
      if (word == null) {
        word = "";
      }
      
      int page = 1;
      switch (option) {
      case "default":
      case "count":
        list = postDao.getMyPostByPage(pageNumber, id);
        page = paginator.getMyPostPageNumber(id);
        break;
      case "title":
      case "content":
      case "all":
        list = postDao.getMyPostByOption(pageNumber, id, option, word);
        page = paginator.getMyPostPageNumber(option, word, id);
        break;
      }
      
      System.out.println("페이지 수: " + page);
 
      request.setAttribute("list", list);
      request.setAttribute("option", option);
      request.setAttribute("word", word);
      request.setAttribute("currentPage", pageNumber);
      request.setAttribute("pages", page);
      
      forward = new ActionForward();
      forward.setRedirect(false);
      forward.setPath("/WEB-INF/views/mypost/mypost.jsp");
    } catch (Exception e) {
      System.out.println("MyPostAction: " + e.getMessage());
    }
    return forward;
  }

}
