package kr.co.groot.page;

import java.sql.SQLException;

import javax.naming.NamingException;

import kr.co.groot.dao.LectureDao;
import kr.co.groot.dao.PostDao;

public class Paginator {
  private PostDao postDao;
  private LectureDao lectureDao;
  
  public int getPageNumber(int boardType) throws NamingException, SQLException {
     postDao = new PostDao();
    
    int post = postDao.countHowManyPost(boardType);
    int page = (post % 20 == 0) ? (post / 20) : (post / 20 + 1);
    
    return page;
  }
  
  public int getPageNumber(String option, String word, int boardType) throws NamingException, SQLException {
    postDao = new PostDao();
    int page = 0;
    int post = 0;
    
    switch (option) {
    case "default":
    case "count":
      post = postDao.countHowManyPost(boardType);
      break;
    case "title":
    case "content":
    case "all":
      post = postDao.countHowManyPostWithOption(option, word, boardType);
      break;
    }
    
    page = (post % 20 == 0) ? (post / 20) : (post / 20 + 1);
    return page;
  }
  
  public int getMyCommentPageNumber(int id) throws NamingException, SQLException {
    postDao = new PostDao();
    
    int post = postDao.countHowManyMyComment(id);
    int page = (post % 20 == 0) ? (post / 20) : (post / 20 + 1);
    
    return page;
  }
  
  public int getMyCommentPageNumber(String option, String word, int id) throws NamingException, SQLException {
    postDao = new PostDao();
    int page = 0;
    int post = 0;
    
    switch (option) {
    case "default":
    case "count":
      post = postDao.countHowManyMyComment(id);
      break;
    case "title":
    case "content":
    case "all":
      post = postDao.countHowManyMyCommentWithOption(option, word, id);
      break;
    }
    
    page = (post % 20 == 0) ? (post / 20) : (post / 20 + 1);
    return page;
  }
  
  public int getMyPostPageNumber(int id) throws NamingException, SQLException {
    postDao = new PostDao();
    
    int post = postDao.countHowManyMyPost(id);
    int page = (post % 20 == 0) ? (post / 20) : (post / 20 + 1);
    
    return page;
  }
  
  public int getMyPostPageNumber(String option, String word, int id) throws NamingException, SQLException {
    postDao = new PostDao();
    int page = 0;
    int post = 0;
    
    switch (option) {
    case "default":
    case "count":
      post = postDao.countHowManyMyPost(id);
      break;
    case "title":
    case "content":
    case "all":
      post = postDao.countHowManyMyPostWithOption(option, word, id);
      break;
    }
    
    page = (post % 20 == 0) ? (post / 20) : (post / 20 + 1);
    return page;
  }
  
  public int getLecturePageNumber() throws NamingException, SQLException {
    lectureDao = new LectureDao();
    
    int lectures = lectureDao.countHowManyLectureList();
    
    int page = (lectures % 20 == 0) ? (lectures / 20) : (lectures / 20 + 1);
    
    return page;
  }
  
  public int getLecturePageNumberByOption(String option, String word) throws NamingException, SQLException {
    lectureDao = new LectureDao();
    
    int lectures = lectureDao.countHowManyLectureWithOption(option, word);
    // 기준과 검색어로 검색하고 몇 개인지 리턴하는 함수
    
    int page = (lectures % 20 == 0) ? (lectures / 20) : (lectures / 20 + 1);
    
    return page;
  }
}
