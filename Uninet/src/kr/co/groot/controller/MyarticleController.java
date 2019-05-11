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
import kr.co.groot.service.MyCommentAction;
import kr.co.groot.service.MyPostAction;


@WebServlet(description = "내가 쓴 글, 댓글 작업 처리", urlPatterns = { "/mypost","/mycomment"})
public class MyarticleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public MyarticleController() {
        
       
    }

   private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     String requestUri = request.getRequestURI();
     String contextPath = request.getContextPath();
     String urlCommand = requestUri.substring(contextPath.length());
      
     Action action = null;
     ActionForward forward = null;
     if (urlCommand.equals("/mypost")) {
       action = new MyPostAction();
       forward = action.execute(request, response);
     } else if(urlCommand.equals("/mycomment")) {
       action = new MyCommentAction();
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
	  doProcess(request,response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  doProcess(request,response);
	}

}
