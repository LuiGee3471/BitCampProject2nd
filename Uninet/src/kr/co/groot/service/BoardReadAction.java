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
import kr.co.groot.dto.Comment;
import kr.co.groot.dto.Post;
import kr.co.groot.dto.Staff;

public class BoardReadAction implements Action {

  @Override
  public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
    ActionForward forward = null;
    int id = Integer.parseInt(request.getParameter("id"));
    Post post = null;
    List<Comment> commentList = null;
    
    try {
      PostDao postDao = new PostDao();
      CommentDao commentDao = new CommentDao();
      postDao.addCount(id);
      post = postDao.getPost(id);
      
      commentList = commentDao.getCommentList(id);

    } catch (NamingException e) {
      System.out.println(e.getMessage());
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }

    forward = new ActionForward();
    forward.setRedirect(false);
    forward.setPath("/WEB-INF/views/boardcontent.jsp");
    request.setAttribute("post", post);
    request.setAttribute("comments", commentList);
    request.setAttribute("postId", id);
    
    return forward;
  }
}
