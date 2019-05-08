package kr.co.groot.service;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.LectureDao;
import kr.co.groot.dto.Lecture;
public class LectureUpdateokAction implements Action {

  @Override
  public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
    ActionForward forward = null;
    Lecture lecture = new Lecture();
    lecture.setLectureName(request.getParameter("lecturename"));
    lecture.setCredit(Integer.parseInt(request.getParameter("credit")));
    lecture.setTime(request.getParameter("lecturetime"));
    lecture.setLectureType(request.getParameter("lecturetype"));
    lecture.setMajorName(request.getParameter("major"));
    lecture.setProfName(request.getParameter("professor"));
    
    int row = 0;
    try {
      LectureDao dao = new LectureDao();
      row = dao.updateLecture(lecture);
    } catch (Exception e) {
      e.printStackTrace();
    }
       
    return null;
  }

}
