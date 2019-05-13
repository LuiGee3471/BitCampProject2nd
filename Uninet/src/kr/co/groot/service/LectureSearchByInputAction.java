package kr.co.groot.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.LectureDao;
import kr.co.groot.dto.Lecture;
import kr.co.groot.page.Paginator;

public class LectureSearchByInputAction implements Action {

  @Override
  public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
    ActionForward forward = new ActionForward();

    List<Lecture> list = null;

    int pageNumber = Integer.parseInt(request.getParameter("page"));
    System.out.println("method : " + request.getParameter("method"));
    System.out.println("option : " + request.getParameter("option"));
    System.out.println("word : " + request.getParameter("word"));
    System.out.println("page : " + pageNumber);
    String method = request.getParameter("method");
    String option = request.getParameter("option");
    String word = request.getParameter("word");

    int page = 1;

    LectureDao dao;
    try {
      dao = new LectureDao();
      Paginator paginator = new Paginator();

      switch (method) {
      case "default":
        page = paginator.getLecturePageNumber();
        list = dao.getLectureByPage(pageNumber);
        break;
      case "search":
        page = paginator.getLecturePageNumberByOption(option, word);
        list = dao.getLectureBySearchWord(pageNumber, option, word);
        break;
      case "sort":
        page = paginator.getLecturePageNumber();
        list = dao.getLectureSortByOption(pageNumber, option);
        break;
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    request.setAttribute("method", method);
    request.setAttribute("option", option);
    request.setAttribute("word", word);
    request.setAttribute("currentPage", pageNumber);
    request.setAttribute("totalPages", page);
    request.setAttribute("list", list);

    forward.setRedirect(false);
    forward.setPath("/WEB-INF/views/lecture/lectureSearchResult.jsp");

    return forward;
  }

}