package kr.co.groot.service;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.PostDao;
import kr.co.groot.dao.StaffDao;
import kr.co.groot.dto.Post;
import kr.co.groot.dto.Staff;


public class BoardListAction implements Action{

  @Override
  public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
    ActionForward forward = null;
    try {
      PostDao dao = new PostDao();
      List<Post> postlist;
      postlist = dao.selectAll(); 
      request.setAttribute("list", postlist);
      forward = new ActionForward();
      forward.setRedirect(false);
      forward.setPath("/WEB-INF/views/boardlist.jsp");
    } catch (SQLException e) {  
      System.out.println(e.getMessage());
    } 
    return forward;
  }
}
