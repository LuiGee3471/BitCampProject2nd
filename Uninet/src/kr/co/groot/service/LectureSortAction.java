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
  public ActionForward execute(HttpServletRequest request,
      HttpServletResponse response) {
    ActionForward forward = new ActionForward();
    List<Lecture> list = new ArrayList<Lecture>();
    PaginatorLectureList paginator = new PaginatorLectureList();
    int page = 1;

    String sort = request.getParameter("sort");
    int pageNumber = Integer.parseInt(request.getParameter("page"));

    LectureDao dao = new LectureDao();
    /* list = dao.sortLecture(sort); */
    list = dao.getLectureSortByOption(pageNumber, sort);
    page = paginator.getPageNumber();

    request.setAttribute("list", list);
    request.setAttribute("pages", page);
    request.setAttribute("option", sort);
    request.setAttribute("currentPage", pageNumber);

    forward.setRedirect(false);
    forward.setPath("/WEB-INF/views/lecture/lectureSearchResult.jsp");

    return forward;
  }
}
