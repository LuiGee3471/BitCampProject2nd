package kr.co.groot.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.LectureDao;
import kr.co.groot.dto.Lecture;
public class LectureUpdateOkAction implements Action {

  @Override
  public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
    ActionForward forward = null;
    Lecture lecture = new Lecture();
    
    String weekday = request.getParameter("weekday");
    String lecturetime = request.getParameter("lecturetime");
    String lectime = weekday + lecturetime;
    
    System.out.println(request.getParameter("lecturename"));
    System.out.println("lecturename : " +request.getParameter("lecturename"));
    System.out.println("id : " +Integer.parseInt(request.getParameter("id")));
    System.out.println("credit : " + Integer.parseInt(request.getParameter("credit")));
    System.out.println("lectime" + lectime);
    System.out.println("lecturetype : " + Integer.parseInt(request.getParameter("lecturetype")));
    System.out.println("professor : " +Integer.parseInt(request.getParameter("professor")));
    
    
    
    lecture.setCredit(Integer.parseInt(request.getParameter("credit")));
    lecture.setTime(lectime);
    lecture.setLectureTypeId(Integer.parseInt(request.getParameter("lecturetype")));
    lecture.setProfId(Integer.parseInt(request.getParameter("professor")));
    lecture.setLectureName(request.getParameter("lectureName"));
    lecture.setId(Integer.parseInt(request.getParameter("id")));
    
    int row = 0;
    try {
      LectureDao dao = new LectureDao();
      row = dao.updateLecture(lecture);
    } catch (Exception e) {
      e.printStackTrace();
    }
       
    String msg = "";
    String url = "";
    
    if(row > 0 ) {
      msg = "수정 성공";
      url = "list";
    } else {
      msg = "수정 실패";
      url = "list";
    }
    System.out.println("url : " + url);
    request.setAttribute("msg", msg);
    request.setAttribute("url", url);
    
    forward = new ActionForward();
    forward.setRedirect(false);
    forward.setPath("/WEB-INF/views/redirect.jsp");
    
    return forward;
  }

}
