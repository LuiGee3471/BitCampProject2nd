package kr.co.groot.service;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.PostDao;
import kr.co.groot.dto.Post;

public class BoardWriteAction implements Action {

  @Override
  public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
    ActionForward forward = null;
   
    try {
      int result = 0;
      Post post = new Post();
      String url = "";
      String msg = "";
      
      post.setId(Integer.parseInt(request.getParameter("id")));
      post.setTitle(request.getParameter("title"));
      post.setContent(request.getParameter("content"));
      post.setWriterId(Integer.parseInt(request.getParameter("writerId")));
      post.setTime(Timestamp.valueOf(request.getParameter("time")));
      post.setCount(Integer.parseInt(request.getParameter("count")));
      post.setBoardType(Integer.parseInt(request.getParameter("boardType")));
      
      PostDao dao = new PostDao();
      
      result = dao.insertPost(post);
      
      if (result > 0) {
        msg = "게시판에 글이 등록되었습니다.";
        url = "board/list";

      } else {
        msg = "실패하였습니다.";
        url = "/board/list";
      }
      request.setAttribute("msg", msg);
      request.setAttribute("url", url);

      forward = new ActionForward();
      forward.setRedirect(false);
      forward.setPath("/WEB-INF/views/redirect.jsp");
    
    }catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return forward;
  }
}
