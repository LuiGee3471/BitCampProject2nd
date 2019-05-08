package kr.co.groot.service;

import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.LectureDao;
import kr.co.groot.dto.Lecture;

public class LectureUpdateAction implements Action{

  @Override
  public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
    ActionForward forward = new ActionForward();
    int id = Integer.parseInt(request.getParameter("id"));
    List<Lecture> lectureList = null;
 
    
    try {
      LectureDao dao = new LectureDao();
      lectureList = dao.selectAll();
      
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    request.setAttribute("lectureList", lectureList);

    
    forward.setRedirect(false);
    forward.setPath("/WEB-INF/views/update.jsp");
    return forward;
  }

}
