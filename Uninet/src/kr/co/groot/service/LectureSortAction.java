package kr.co.groot.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.LectureDao;
import kr.co.groot.dto.Lecture;
import kr.co.groot.page.PaginatorLectureList;

public class LectureSortAction implements Action {

  @Override
  public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
    ActionForward forward = new ActionForward();
    List<Lecture> list = new ArrayList<Lecture>();
    PaginatorLectureList paginator = new PaginatorLectureList();
    int page = 1;
   
    String sort = request.getParameter("sort");
    int pageNumber = Integer.parseInt(request.getParameter("page"));
    System.out.println("sort잘넘어왔냐? : " + sort);
    System.out.println(pageNumber);
    
    
    try {
      LectureDao dao = new LectureDao();
      /* list = dao.sortLecture(sort); */
      list= dao.getLectureSortByOption(pageNumber, sort);
      page = paginator.getPageNumber(); 
      
    } catch (Exception e) {
      System.out.println("이점 겟메시지 : " + e.getMessage());
    }
    request.setAttribute("list", list);
    request.setAttribute("pages", page);
    request.setAttribute("option", sort);
    request.setAttribute("currentPage", pageNumber);

    forward.setRedirect(false);
    forward.setPath("/WEB-INF/views/lecture/lectureSearchResult.jsp");

    return forward;
  }

}
