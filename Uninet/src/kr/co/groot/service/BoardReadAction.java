package kr.co.groot.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.PostDao;
import kr.co.groot.dto.Comment;
import kr.co.groot.dto.Post;

public class BoardReadAction implements Action{

  @Override
  public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
   ActionForward forward = null;
   try {
     PostDao dao = new PostDao();
     int id = Integer.parseInt(request.getParameter("id"));
     Post post= dao.getContent(id);
     List<Comment> commentlist;
     commentlist = dao.getCommentList(id);
     
     request.setAttribute("post",post);
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
