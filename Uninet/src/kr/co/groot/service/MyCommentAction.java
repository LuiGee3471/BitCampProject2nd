package kr.co.groot.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.CommentDao;
import kr.co.groot.dao.PostDao;
import kr.co.groot.dto.Post;
import kr.co.groot.dto.Staff;
import kr.co.groot.page.Paginator;

public class MyCommentAction implements Action {

  @Override
  public ActionForward execute(HttpServletRequest request,
      HttpServletResponse response) {
    ActionForward forward = null;
    List<Post> list = null;
    HttpSession session = request.getSession();
    Staff loggedUser = (Staff) session.getAttribute("staff");
    String option = request.getParameter("option");
    String word = request.getParameter("word");
    int pageNumber = Integer.parseInt(request.getParameter("page"));

    int id = loggedUser.getId();

    PostDao postDao = new PostDao();
    Paginator paginator = new Paginator();

    if (word == null) {
      word = "";
    }

    int page = 1;
    switch (option) {
    case "default":
      list = postDao.getMyCommentByPage(pageNumber, id);
      page = paginator.getMyCommentPageNumber(id);
      break;
    case "title":
    case "content":
    case "all":
      list = postDao.getMyCommentByOption(pageNumber, id, option, word);
      page = paginator.getMyCommentPageNumber(option, word, id);
      break;
    }

    request.setAttribute("list", list);
    request.setAttribute("option", option);
    request.setAttribute("word", word);
    request.setAttribute("currentPage", pageNumber);
    request.setAttribute("pages", page);

    forward = new ActionForward();
    forward.setRedirect(false);
    forward.setPath("/WEB-INF/views/mypost/mycomment.jsp");
    return forward;
  }
}
