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
import kr.co.groot.service.BoardListAction;
import kr.co.groot.service.BoardWriteAction;

@WebServlet(description = "게시판 작업 처리", urlPatterns = { "/board/*" })
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public BoardController() {
       
    }
    
    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      String requestUri = request.getRequestURI();
      String contextPath = request.getContextPath();
      String urlCommand = requestUri.substring(contextPath.length());
      
      Action action = null;
      ActionForward forward = null;
      if(urlCommand.equals("/board/list")) {
        action = new BoardListAction();
        forward = action.execute(request, response);
      } else if(urlCommand.equals("/board/write")) {
        action = new BoardWriteAction();
        forward = action.execute(request, response);        
      } else if(urlCommand.equals("/board/read")) {
        action = new BoardReadAction();
        forward = action.execute(request, response);        
      } else if(urlCommand.equals("/board/delete")) {
        action = new BoardDeleteAction();
        forward = action.execute(request, response);        
      } else if(urlCommand.equals("/board/update")) {
        action = new BoardUpdateAction();
        forward = action.execute(request, response);        
      } else if(urlCommand.equals("/board/comment")) {
        action = new BoardCommentAction();
        forward = action.execute(request, response);        
      } else if(urlCommand.equals("/board/recomment")) {
        action = new BoardRecommentAction();
        forward = action.execute(request, response);        
      } else if(urlCommand.equals("/board/search")) {
        action = new BoardSearchAction();
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
