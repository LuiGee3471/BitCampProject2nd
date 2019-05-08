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
import kr.co.groot.dto.Lecture;


public class LectureWriteAction implements Action{

  @Override
  public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
    ActionForward forward = new ActionForward();
    LectureDao dao;
    try {
      dao = new LectureDao();
      List<Lecture> list = dao.selectAll();
    } catch (Exception e) {
      e.printStackTrace();
    }

    forward.setRedirect(false);
    forward.setPath("/WEB-INF/views/lecturewrite.jsp");
    return forward;
  }

}
