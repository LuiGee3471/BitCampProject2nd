package kr.co.groot.page;

import java.sql.SQLException;

import javax.naming.NamingException;

import kr.co.groot.dao.LectureDao;

public class PaginatorLectureList {
  private LectureDao lecturedao;
  
  public int getPageNumber() {
    lecturedao = new LectureDao();
    
    int post = lecturedao.countHowManyLectureList();
    int page = (post % 20 == 0) ? (post / 20) : (post / 20 + 1);
    
    return page;
  }
  
  public int getPageNumber(String option, String word, int boardType) throws NamingException, SQLException {
    lecturedao = new LectureDao();
    int page = 0;
    int post = 0;
    
    
    
    page = (post % 20 == 0) ? (post / 20) : (post / 20 + 1);
    return page;
  }
}
