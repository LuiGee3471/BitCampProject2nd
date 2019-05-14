package kr.co.groot.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.PostDao;
import kr.co.groot.dto.Post;

public class BoardSearchAction implements Action {

  @Override
  public ActionForward execute(HttpServletRequest request,
      HttpServletResponse response) {
    ActionForward forward = new ActionForward();
    String option = request.getParameter("searchOption");
    int boardType = Integer.parseInt(request.getParameter("boardType"));
    String searchWord = request.getParameter("searchWord");

    PostDao postDao = new PostDao();
    List<Post> postList = null;

    switch (option) {
    case "title":
      postList = postDao.selectByTitle(searchWord, boardType);
      break;
    case "content":
      postList = postDao.selectByContent(searchWord, boardType);
      break;
    case "all":
      postList = postDao.selectByAll(searchWord, boardType);
      break;
    case "count":
      postList = postDao.selectByCount(boardType);
      break;
    }
    request.setAttribute("list", postList);

    forward.setRedirect(false);
    forward.setPath("/board/list?option=" + option);
    return forward;
  }

}
