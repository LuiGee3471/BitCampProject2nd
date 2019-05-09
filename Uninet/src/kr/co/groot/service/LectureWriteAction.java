package kr.co.groot.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.LectureDao;
import kr.co.groot.dao.LectureTypeDao;
import kr.co.groot.dao.ProfessorDao;
import kr.co.groot.dto.Lecture;
import kr.co.groot.dto.LectureType;
import kr.co.groot.dto.Major;
import kr.co.groot.dto.Professor;


public class LectureWriteAction implements Action{

  @Override
  public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
    ActionForward forward = new ActionForward();
  
    List<LectureType> ltList = null;
    List<Professor> profList = null;
    List<Lecture> list = null;
    try {
      LectureDao dao = new LectureDao();
      list = dao.selectAll();
      
      ProfessorDao professor = new ProfessorDao();
      profList = professor.selectAll();
      
      LectureTypeDao lectureType = new LectureTypeDao();
      ltList = lectureType.selectAll();
    } catch (Exception e) {
      e.printStackTrace();
    }

    request.setAttribute("profList", profList);
    request.setAttribute("ltList", ltList);
    
    forward.setRedirect(false);
    forward.setPath("/WEB-INF/views/lecturewrite.jsp");
    return forward;
  }

}
