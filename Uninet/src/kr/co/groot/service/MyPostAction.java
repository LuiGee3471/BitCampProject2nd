package kr.co.groot.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.PostDao;
import kr.co.groot.dto.Post;

public class MyPostAction implements Action {

  @Override
  public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
    ActionForward forward = null;
    List<Post> list = null;
    PostDao dao = null;
    try {
      int id = Integer.parseInt(request.getParameter("id"));
      list= new ArrayList<>();
      dao = new PostDao();
      list = dao.selectByWriter(id);
      
      request.setAttribute("mypost",list);
      forward = new ActionForward();
      forward.setRedirect(false);
      forward.setPath("/WEB-INF/views/mypage/mypost.jsp");
      
    }catch (Exception e) {
     System.out.println(e.getMessage());
    }
    return forward;
  }

}
