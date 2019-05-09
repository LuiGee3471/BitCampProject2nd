package kr.co.groot.service;

import java.util.List;

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

public class BoardReadAction implements Action{

  @Override
  public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
   ActionForward forward = null;
   try {
     PostDao dao = new PostDao();
     CommentDao dao2 = new CommentDao();
     StaffDao dao3 = new StaffDao();
     int id = Integer.parseInt(request.getParameter("id"));
     Post post= dao.getContent(id);
     List<Comment> commentlist;
     commentlist = dao2.getCommentList(id);
     Staff staff = dao3.selectByUniqueId(id);
     request.setAttribute("post",post);
     request.setAttribute("staff", staff);
     System.out.println("ok");
     request.setAttribute("id", id);
     request.setAttribute("commentlist",commentlist);
     forward = new ActionForward();
     forward.setRedirect(false);
     forward.setPath("/WEB-INF/views/boardcontent.jsp"); 
   } catch (Exception e) {
   System.out.println(e.getMessage());
  }
    return forward;
  }
}
