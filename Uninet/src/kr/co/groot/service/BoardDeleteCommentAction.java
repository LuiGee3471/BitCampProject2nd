package kr.co.groot.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.CommentDao;
import kr.co.groot.dto.Comment;

public class BoardDeleteCommentAction implements Action {
 // id, 
  @Override
  public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
    ActionForward forward = null;
    int result = 0;
    int id = Integer.parseInt(request.getParameter("deleteId"));
      System.out.println("삭제할 댓글id"+id);
    int postId = Integer.parseInt(request.getParameter("postId"));
      System.out.println("원본글 id"+postId);
    try {
      String msg = "";
      String url ="";
      CommentDao dao = new CommentDao();
      
      result =  dao.deleteComment(id);
      forward = new ActionForward();
      System.out.println("result"+result);
      if(result>0) {
       forward.setRedirect(true);
       forward.setPath("read?id="+postId);
      }else {
        msg = "실패하였습니다.";
        url = "board/read?id="+postId;
        
        forward.setRedirect(false);
        forward.setPath("/WEB-INF/views/redierct.jsp");
        request.setAttribute("msg", msg);
        request.setAttribute("url", url);
      }
  
    }catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return forward;
  }

}
