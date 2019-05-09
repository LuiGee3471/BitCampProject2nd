package kr.co.groot.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.LectureDao;
import kr.co.groot.dto.Lecture;

public class LectureSortAction implements Action {

  @Override
  public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
    ActionForward forward = new ActionForward();
    List<Lecture> list = new ArrayList<Lecture>();

    String sort = request.getParameter("sort");
    System.out.println("sort잘넘어왔냐? : " + sort);

    try {
      LectureDao dao = new LectureDao();
      list = dao.sortLecture(sort);
    } catch (Exception e) {
      System.out.println("이점 겟메시지 : " + e.getMessage());
    }
    request.setAttribute("list", list);

    forward.setRedirect(false);
    forward.setPath("/WEB-INF/views/lectureSearchResult.jsp");

    return forward;
  }

}
