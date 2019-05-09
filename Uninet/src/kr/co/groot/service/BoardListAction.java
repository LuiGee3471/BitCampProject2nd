package kr.co.groot.service;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.CommentDao;
import kr.co.groot.dao.PostDao;
import kr.co.groot.dao.StaffDao;
import kr.co.groot.dto.Post;
import kr.co.groot.dto.Staff;


public class BoardListAction implements Action{

  @Override
  public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
    ActionForward forward = null;
    try {
      PostDao postDao = new PostDao();
      CommentDao commentDao = new CommentDao();
      List<Post> postlist;
      postlist = postDao.selectPostByBoardType(2);
      for (Post p : postlist) {
        p.setCommentCount(commentDao.getCommentCount(p.getId()));
      }
      request.setAttribute("list", postlist);
      forward = new ActionForward();
      forward.setRedirect(false);
      forward.setPath("/WEB-INF/views/boardlist.jsp");
    } catch (SQLException e) {  
      System.out.println(e.getMessage());
    } catch (NamingException e) {
      System.out.println(e.getMessage());
    } 
    return forward;
  }
}
