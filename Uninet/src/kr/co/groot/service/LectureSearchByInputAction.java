package kr.co.groot.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.LectureDao;
import kr.co.groot.dto.Lecture;

public class LectureSearchByInputAction implements Action{

  @Override
  public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
    ActionForward forward = new ActionForward();
    
    List<Lecture> list = new ArrayList<Lecture>();
  
    
    String searchInput = request.getParameter("searchInput");
    String searchradio = request.getParameter("searchradio");
    
    System.out.println("searchInput : " + searchInput);
    System.out.println("searchradio : " + searchradio);
    LectureDao dao;
    try {
      dao = new LectureDao();
      list = dao.selectByName(searchradio, searchInput);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    
    request.setAttribute("list", list);

    forward.setRedirect(false);
    forward.setPath("/WEB-INF/views/lectureSearchResult.jsp");
    
    
    return forward;
  }

}