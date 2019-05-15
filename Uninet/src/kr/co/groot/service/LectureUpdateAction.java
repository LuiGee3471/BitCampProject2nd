package kr.co.groot.service;

import java.util.List;

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

public class LectureUpdateAction implements Action {

  @Override
  public ActionForward execute(HttpServletRequest request,
      HttpServletResponse response) {
    ActionForward forward = new ActionForward();
    int id = Integer.parseInt(request.getParameter("id"));
    
    List<Lecture> lectureList = null;
    List<Professor> profList = null;
    List<LectureType> ltList = null;
    List<Major> majorList = null;
    List<Professor> professorList = null;
    
    Lecture lecture2 = null;

    LectureDao dao = new LectureDao();
    lecture2 = new Lecture();
    lecture2 = dao.selectById(id);
    professorList = dao.getProfessorListByMajorId();
    

    LectureDao lecture = new LectureDao();
    lectureList = lecture.selectAll();

    ProfessorDao professor = new ProfessorDao();
    profList = professor.selectAll();

    LectureTypeDao lectureType = new LectureTypeDao();
    ltList = lectureType.selectAll();

    MajorDao major = new MajorDao();
    majorList = major.selectAll();

    request.setAttribute("lectureList", lectureList);
    request.setAttribute("profList", profList);
    request.setAttribute("ltList", ltList);
    request.setAttribute("majorList", majorList);
    request.setAttribute("id", id);
    request.setAttribute("lecture2", lecture2);
    request.setAttribute("professorList", professorList);

    forward.setRedirect(false);
    forward.setPath("/WEB-INF/views/lecture/lectureupdate.jsp");
    return forward;
  }

}
