package kr.co.groot.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.CommentDao;
import kr.co.groot.dao.PostDao;
import kr.co.groot.dao.StaffDao;
import kr.co.groot.dto.Comment;
import kr.co.groot.dto.Post;
import kr.co.groot.dto.Staff;

public class BoardReadAction implements Action{

  @Override
  public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
   ActionForward forward = null;
   
   PostDao postDao = new PostDao();
   CommentDao commentDao = new CommentDao();
   
  }
}
