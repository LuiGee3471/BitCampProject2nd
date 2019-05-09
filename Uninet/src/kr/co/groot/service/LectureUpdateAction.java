package kr.co.groot.service;

import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.LectureDao;
import kr.co.groot.dao.LectureTypeDao;
import kr.co.groot.dao.MajorDao;
import kr.co.groot.dao.ProfessorDao;
import kr.co.groot.dto.Lecture;
import kr.co.groot.dto.LectureType;
import kr.co.groot.dto.Major;
import kr.co.groot.dto.Professor;

public class LectureUpdateAction implements Action{

  @Override
  public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
    ActionForward forward = new ActionForward();
    int id = Integer.parseInt(request.getParameter("id"));
    System.out.println("id의 값은 : " + id);
    List<Lecture> lectureList = null;
    List<Professor> profList = null;
    List<LectureType> ltList = null;
    List<Major> majorList = null;
    
    try {
      LectureDao lecture = new LectureDao();
      lectureList = lecture.selectAll();
      
      ProfessorDao professor = new ProfessorDao();
      profList = professor.selectAll();
      
      LectureTypeDao lectureType = new LectureTypeDao();
      ltList = lectureType.selectAll();
      
      MajorDao major = new MajorDao();
      majorList = major.selectAll();
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    request.setAttribute("lectureList", lectureList);
    request.setAttribute("profList", profList);
    request.setAttribute("ltList", ltList);
    request.setAttribute("majorList", majorList);
    request.setAttribute("id", id);

    
    forward.setRedirect(false);
    forward.setPath("/WEB-INF/views/lectureupdate.jsp");
    return forward;
  }

}
