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
  public ActionForward execute(HttpServletRequest request,
      HttpServletResponse response) {
    ActionForward forward = null;
    PaginatorLectureList paginator = new PaginatorLectureList();

    LectureDao dao = null;
    List<Lecture> lecturelist = null;

    dao = new LectureDao();

    lecturelist = dao.selectAll();

    request.setAttribute("lecturelist", lecturelist);

    forward = new ActionForward();
    forward.setRedirect(false);
    forward.setPath("/WEB-INF/views/lecture/lecturelist.jsp");

    return forward;
  }
}