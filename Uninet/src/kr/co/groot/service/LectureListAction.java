package kr.co.groot.service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.LectureDao;
import kr.co.groot.dto.Lecture;
import kr.co.groot.page.PaginatorLectureList;

public class LectureListAction implements Action {

  @Override
  public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
    ActionForward forward = null;
    PaginatorLectureList paginator = new PaginatorLectureList();
    int pageNumber = Integer.parseInt(request.getParameter("page"));
    
    LectureDao dao = null;
    List<Lecture> lecturelist = null;
    int page = 1;
    
    try {
      dao = new LectureDao();
      
      /* lecturelist = dao.selectAll(); */
      lecturelist = dao.getLectureByPage(pageNumber);
      page = paginator.getPageNumber();
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    request.setAttribute("lecturelist", lecturelist);
    request.setAttribute("currentPage", pageNumber);
    request.setAttribute("pages", page);
    
    forward = new ActionForward();
    forward.setRedirect(false);
    forward.setPath("/WEB-INF/views/lecture/lecturelist.jsp");

    return forward;
  }

}