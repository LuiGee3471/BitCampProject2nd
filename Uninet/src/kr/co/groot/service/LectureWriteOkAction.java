package kr.co.groot.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dto.Lecture;

public class LectureWriteOkAction implements Action{

  @Override
  public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
    ActionForward forward = null;
    
    int result = 0;
    String url = "";
    String msg = "";
    
    Lecture lecture = new Lecture();
    
   
    
    
    return null;
  }

}
