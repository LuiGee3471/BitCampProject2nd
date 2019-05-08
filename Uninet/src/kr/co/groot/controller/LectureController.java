package kr.co.groot.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.service.LectureDeleteAction;
import kr.co.groot.service.LectureListAction;
import kr.co.groot.service.LectureSearchByInputAction;
import kr.co.groot.service.LectureSearchByMajorAction;
import kr.co.groot.service.LectureSortAction;
import kr.co.groot.service.LectureUpdateAction;
import kr.co.groot.service.LectureUpdateokAction;
import kr.co.groot.service.LectureWriteAction;

@WebServlet(description = "강의CRUD/검색/정렬 처리", urlPatterns = { "/lecture/*" })
public class LectureController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public LectureController() {

  }

  private void doProcess(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String requestUri = request.getRequestURI();
    String contextPath = request.getContextPath();
    String urlCommand = requestUri.substring(contextPath.length());
    System.out.println(urlCommand);

    Action action = null;
    ActionForward forward = null;

    if (urlCommand.equals("/lecture/delete")) {
      action = new LectureDeleteAction();
      forward = action.execute(request, response);
    } else if (urlCommand.equals("/lecture/list")) {
      action = new LectureListAction();
      forward = action.execute(request, response);
    } else if (urlCommand.equals("/lecture/input")) {
      action = new LectureSearchByInputAction();
      forward = action.execute(request, response);
    } else if (urlCommand.equals("/lecture/major")) {
      action = new LectureSearchByMajorAction();
      forward = action.execute(request, response);
    } else if (urlCommand.equals("/lecture/sort")) {
      action = new LectureSortAction();
      forward = action.execute(request, response);
    } else if (urlCommand.equals("/lecture/updatePage")) {
      action = new LectureUpdateAction();
      forward = action.execute(request, response);
    } else if (urlCommand.equals("/lecture/update")) {
      action = new LectureUpdateokAction();
      forward = action.execute(request, response);
    } else if (urlCommand.equals("/lecture/write")) {
      action = new LectureWriteAction();
      forward = action.execute(request, response);
    }

    if (forward != null) {
      if (forward.isRedirect()) {
        response.sendRedirect(forward.getPath());
      } else {
        RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
        dis.forward(request, response);
      }
    }

  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doProcess(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doProcess(request, response);
  }

}
