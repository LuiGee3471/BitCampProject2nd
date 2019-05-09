package kr.co.groot.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.CommentDao;
import kr.co.groot.dto.Comment;
import kr.co.groot.dto.Staff;

public class BoardCommentAction implements Action {

  @Override
  public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
    ActionForward forward = null;
    int id = Integer.parseInt(request.getParameter("commentId"));
    Comment comment = new Comment();
    try {
      HttpSession session = request.getSession();
      Staff staff = (Staff) session.getAttribute("staff");
      CommentDao dao = new CommentDao();
     
          
      comment.setContent(request.getParameter("comment"));
      comment.setWriterId(staff.getId());
      comment.setRefer(Integer.parseInt(request.getParameter("commentId")));
      dao.insertComment(comment);
      
      request.setAttribute("postId", id);
      forward = new ActionForward();
      forward.setRedirect(false);
      forward.setPath("/WEB-INF/views/boardcontent.jsp");
    }catch (Exception e) {
      System.out.println(e.getMessage());
    }
    
     
    return forward;
  }

}
