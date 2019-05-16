package kr.co.groot.page;

import java.sql.SQLException;

import javax.naming.NamingException;

import kr.co.groot.dao.LectureDao;
import kr.co.groot.dao.PostDao;

public class Paginator {
  private PostDao postDao;
  private LectureDao lectureDao;

  /*
   * @method Name: getPageNumber
   * 
   * @date: 2019. 5. 10.
   * 
   * @author: 윤종석
   * 
   * @description: 필요한 페이지가 총 몇 페이지인지 구한다
   * 
   * @param spec: int boardType
   * 
   * @return: int
   */
  public int getPageNumber(int boardType) {
    postDao = new PostDao();

    int post = postDao.countHowManyPost(boardType);
    int page = (post % 20 == 0) ? (post / 20) : (post / 20 + 1);

    return page;
  }

  /*
   * @method Name: getPageNumber
   * 
   * @date: 2019. 5. 10.
   * 
   * @author: 윤종석
   * 
   * @description: 필요한 페이지가 총 몇 페이지인지 구한다
   * 
   * @param spec: String option, String word, int boardType
   * 
   * @return: int
   */
  public int getPageNumber(String option, String word, int boardType) {
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

  /*
   * @method Name: getMyCommentPageNumber
   * 
   * @date: 2019. 5. 12.
   * 
   * @author: 강기훈
   * 
   * @description: 댓글 단 글의 페이지 수를 구한다
   * 
   * @param spec: int id
   *
   * @return: int
   */
  public int getMyCommentPageNumber(int id) {
    postDao = new PostDao();

    int post = postDao.countHowManyMyComment(id);
    int page = (post % 20 == 0) ? (post / 20) : (post / 20 + 1);

    return page;
  }

  /*
   * @method Name: getMyCommentPageNumber
   * 
   * @date: 2019. 5. 12.
   * 
   * @author: 강기훈
   * 
   * @description: 댓글 단 글의 페이지 수를 구한다
   * 
   * @param spec: String option, String word, int id
   *
   * @return: int
   */
  public int getMyCommentPageNumber(String option, String word, int id) {
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

  /*
   * @method Name: getMyPostPageNumber
   * 
   * @date: 2019. 5. 12.
   * 
   * @author: 강기훈
   * 
   * @description: 내가 쓴 글의 페이지 수를 구한다
   * 
   * @param spec: int id
   *
   * @return: int
   */
  public int getMyPostPageNumber(int id) {
    postDao = new PostDao();

    int post = postDao.countHowManyMyPost(id);
    int page = (post % 20 == 0) ? (post / 20) : (post / 20 + 1);

    return page;
  }

  /*
   * @method Name: getMyPostPageNumber
   * 
   * @date: 2019. 5. 12.
   * 
   * @author: 강기훈
   * 
   * @description: 내가 쓴 글의 페이지 수를 구한다
   * 
   * @param spec: String option, String word, int id
   *
   * @return: int
   */
  public int getMyPostPageNumber(String option, String word, int id) {
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

  /*
   * @method Name: getLecturePageNumber
   * 
   * @date: 2019. 5. 13.
   * 
   * @author: 정성윤
   * 
   * @description: 강의 페이지의 페이지 수를 구한다
   * 
   * @param spec: none
   *
   * @return: int
   */
  public int getLecturePageNumber() {
    lectureDao = new LectureDao();

    int lectures = lectureDao.countHowManyLectureList();

    int page = (lectures % 20 == 0) ? (lectures / 20) : (lectures / 20 + 1);

    return page;
  }

  /*
   * @method Name: getLecturePageNumberByOption
   * 
   * @date: 2019. 5. 13.
   * 
   * @author: 정성윤
   * 
   * @description: 검색 또는 정렬 기준 강의 페이지의 페이지 수를 구한다
   * 
   * @param spec: none
   *
   * @return: int
   */
  public int getLecturePageNumberByOption(String option, String word) {
    lectureDao = new LectureDao();

    int lectures = lectureDao.countHowManyLectureWithOption(option, word);
    // 기준과 검색어로 검색하고 몇 개인지 리턴하는 함수

    int page = (lectures % 20 == 0) ? (lectures / 20) : (lectures / 20 + 1);

    return page;
  }
}
