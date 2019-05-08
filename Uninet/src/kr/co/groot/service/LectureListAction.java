package kr.co.groot.service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.LectureDao;
import kr.co.groot.dto.Lecture;

public class LectureListAction implements Action {

  @Override
  public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
    ActionForward forward = null;
    LectureDao dao = null;
    List<Lecture> lecturelist = null;
    try {
      dao = new LectureDao();
      lecturelist = dao.selectAll();
    } catch (Exception e) {
      e.printStackTrace();
    }

    request.setAttribute("lecturelist", lecturelist);
    forward = new ActionForward();
    forward.setRedirect(false);
    forward.setPath("/WEB-INF/views/lecturelist.jsp");

    return forward;
  }

}