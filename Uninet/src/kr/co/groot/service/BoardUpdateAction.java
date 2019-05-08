package kr.co.groot.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.PostDao;
import kr.co.groot.dto.Post;

public class BoardUpdateAction implements Action{

  @Override
  public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
    ActionForward forward = null;
    String msg = "";
    String url = "";
    
    try {
      Post post = new Post();
      int id = Integer.parseInt(request.getParameter("id"));

      
      
      PostDao dao = new PostDao();
      dao.updatePost(post);
      
      
    }catch (Exception e) {
      System.out.println(e.getMessage());
    }
    
    return forward;
  }

}
