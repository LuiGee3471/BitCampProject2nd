package kr.co.groot.service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.LectureDao;
import kr.co.groot.dto.Lecture;

public class LectureWriteOkAction implements Action {
  @Override
  public ActionForward execute(HttpServletRequest request,
      HttpServletResponse response) {
    ActionForward forward = null;

    int result = 0;
    String url = "";
    String msg = "";

    Lecture lecture = new Lecture();
    String weekday = request.getParameter("weekday");
    String Time = request.getParameter("lectureTime");

    String lectureTime = weekday + Time;

    lecture.setLectureName(request.getParameter("lectureName"));
    lecture.setCredit(Integer.parseInt(request.getParameter("credit")));
    lecture.setTime(lectureTime);
    lecture.setLectureTypeId(
        Integer.parseInt(request.getParameter("lectureTypeId")));
    lecture.setProfId(Integer.parseInt(request.getParameter("professorId")));

    LectureDao dao = new LectureDao();
    result = dao.insertLecture(lecture);

    if (result > 0) {
      msg = "등록 성공";
      url = "list";

    } else {
      msg = "등록 실패";
      url = "list";
    }
    request.setAttribute("msg", msg);
    request.setAttribute("url", url);

    forward = new ActionForward();
    forward.setRedirect(false);
    forward.setPath("/WEB-INF/views/etc/redirect.jsp");

    return forward;
  }
}
