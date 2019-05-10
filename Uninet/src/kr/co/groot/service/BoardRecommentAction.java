package kr.co.groot.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.CommentDao;
import kr.co.groot.dto.Comment;
import kr.co.groot.dto.Staff;

public class BoardRecommentAction implements Action {
 //refer , referComment, content, writerId, writer
  @Override
  public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
    ActionForward forward = null;
    int id = Integer.parseInt(request.getParameter("commentId"));
    Comment comment = new Comment();
    int result = 0;
    try {
      HttpSession session = request.getSession();
      Staff staff = (Staff) session.getAttribute("staff");
      CommentDao dao = new CommentDao();
      String url = "";
      String msg = "";
      
      comment.setContent(request.getParameter("comment"));
      comment.setWriter(staff);
      comment.setWriterId(staff.getId());
      comment.setRefer(id);
      comment.setReferComment("");
      comment.setRecomment("Y");
      
      result = dao.insert
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    
    return forward;
  }

}
