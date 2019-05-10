package kr.co.groot.page;

import java.sql.SQLException;

import javax.naming.NamingException;

import kr.co.groot.dao.PostDao;

public class Paginator {
  private PostDao postDao;
  
  public int getPageNumber(String option) throws NamingException, SQLException {
    postDao = new PostDao();
    int page = 0;
    
    switch (option) {
    case "default":
    case "count":
      int post = postDao.countHowManyPost();
      page = (post % 20 == 0) ? (post / 20) : (post / 20 + 1);
      break;
    }
    
    return page;
  }
  
  public int getPageNumber(String option, String word) throws NamingException, SQLException {
    postDao = new PostDao();
    int page = 0;
    int post = 0;
    
    switch (option) {
    case "default":
    case "count":
      post = postDao.countHowManyPost();
      break;
    case "title":
    case "content":
    case "all":
      post = postDao.countHowManyPostWithOption(option, word);
      break;
    }
    
    page = (post % 20 == 0) ? (post / 20) : (post / 20 + 1);
    return page;
  }
}
