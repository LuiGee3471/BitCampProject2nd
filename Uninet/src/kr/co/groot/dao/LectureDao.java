package kr.co.groot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.json.simple.JSONObject;

import kr.co.groot.dto.Lecture;
import kr.co.groot.dto.Professor;

public class LectureDao {
  private Connection conn;
  private PreparedStatement pstmt;
  private ResultSet rs;
  private DataSource ds;

  public LectureDao() {
    Context context;
    try {
      context = new InitialContext();
      ds = (DataSource) context.lookup("java:comp/env/jdbc/mysql");
    } catch (NamingException e) {
      System.out.println("LectureDao:" + e.getMessage());
    }

  }

  /*
   * @method Name: selectAll
   * 
   * @date: 2019. 5. 7
   * 
   * @author: 윤종석
   * 
   * @description: 모든 강의 정보를 불러온다
   * 
   * @param spec: none
   * 
   * @return: List<Lecture>
   */
  public List<Lecture> selectAll() {
    String sql = "SELECT l.id as id, lecture_name, credit, time, lecture_type,"
        + "prof_name, major_name, lt.id as lecture_type_id, m.id as major_id, p.id as prof_id "
        + "FROM lecture l " + "LEFT JOIN lecturetype lt "
        + "ON l.lecture_type_id = lt.id " + "LEFT JOIN professor p "
        + "ON l.prof_id = p.id " + "LEFT JOIN major m "
        + "ON p.major_id = m.id";
    List<Lecture> lectureList = null;
    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      lectureList = new ArrayList<>();

      while (rs.next()) {
        Lecture lecture = new Lecture();
        lecture.setId(rs.getInt("id"));
        lecture.setLectureName(rs.getString("lecture_name"));
        lecture.setCredit(rs.getInt("credit"));
        lecture.setTime(rs.getString("time"));
        lecture.setLectureType(rs.getString("lecture_type"));
        lecture.setProfName(rs.getString("prof_name"));
        lecture.setMajorName(rs.getString("major_name"));
        lecture.setLectureTypeId(rs.getInt("lecture_type_id"));
        lecture.setMajorId(rs.getInt("major_id"));
        lecture.setProfId(rs.getInt("prof_id"));
        lectureList.add(lecture);
      }
    } catch (SQLException e) {
      System.out.println("selectAll:" + e.getMessage());
    } finally {
      try {
        rs.close();
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("selectAll:" + e.getMessage());
      }
    }
    return lectureList;
  }

  /*
   * @method Name: insertLecture
   * 
   * @date: 2019. 5. 7
   * 
   * @author: 윤종석
   * 
   * @description: 강의를 새로 추가한다.
   * 
   * @param spec: Lecture lecture
   * 
   * @return: int
   */
  public int insertLecture(Lecture lecture) {
    String sql = "insert into lecture (lecture_name, credit, time, "
        + "lecture_type_id, prof_id) values (?, ?, ?, ?, ?)";
    int row = 0;
    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, lecture.getLectureName());
      pstmt.setInt(2, lecture.getCredit());
      pstmt.setString(3, lecture.getTime());
      pstmt.setInt(4, lecture.getLectureTypeId());
      pstmt.setInt(5, lecture.getProfId());

      row = pstmt.executeUpdate();
    } catch (SQLException e) {
      System.out.println("insertLecture:" + e.getMessage());
    } finally {
      try {
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("insertLecture:" + e.getMessage());
      }
    }

    return row;
  }

  /*
   * @method Name: deleteLecture
   * 
   * @date: 2019. 5. 7
   * 
   * @author: 윤종석
   * 
   * @description: 강의 정보를 삭제한다.
   * 
   * @param spec: int id
   * 
   * @return: int
   */
  public int deleteLecture(int id) {
    String sql = "delete from lecture where id = ?";
    int row = 0;
    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, id);

      row = pstmt.executeUpdate();
    } catch (SQLException e) {
      System.out.println("deleteLecture:" + e.getMessage());
    } finally {
      try {
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("deleteLecture:" + e.getMessage());
      }
    }

    return row;
  }

  /*
   * @method Name: updateLecture
   * 
   * @date: 2019. 5. 7
   * 
   * @author: 윤종석
   * 
   * @description: 강의 정보를 수정한다
   * 
   * @param spec: Lecture lecture
   * 
   * @return: int
   */
  public int updateLecture(Lecture lecture) {
    String sql = "update lecture set lecture_name = ?, credit = ?, time = ?, "
        + "lecture_type_id = ?, prof_id = ? where id = ?";
    int row = 0;
    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);

      pstmt.setString(1, lecture.getLectureName());
      pstmt.setInt(2, lecture.getCredit());
      pstmt.setString(3, lecture.getTime());
      pstmt.setInt(4, lecture.getLectureTypeId());
      pstmt.setInt(5, lecture.getProfId());
      pstmt.setInt(6, lecture.getId());

      row = pstmt.executeUpdate();
    } catch (SQLException e) {
      System.out.println("updateLecture: " + e.getMessage());
    } finally {
      try {
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("updateLecture: " + e.getMessage());
      }
    }

    return row;
  }

  /*
   * @method Name: selectByName
   * 
   * @date: 2019. 5. 7
   * 
   * @author: 윤종석
   * 
   * @description: 입력값을 가지고 있는 강의 정보를 가져온다.
   * 
   * @param spec: String criterion(기준), String input
   * 
   * @return: List<Lecture>
   */
  public List<Lecture> selectByName(String criterion, String input) {
    String firstSQL = "SELECT l.id as id, lecture_name, credit, time, lecture_type,"
        + "prof_name, major_name, lt.id as lecture_type_id, m.id as major_id, p.id as prof_id "
        + "FROM lecture l " + "LEFT JOIN lecturetype lt "
        + "ON l.lecture_type_id = lt.id " + "LEFT JOIN professor p "
        + "ON l.prof_id = p.id " + "LEFT JOIN major m "
        + "ON p.major_id = m.id " + "WHERE ";

    String lastSQL = " LIKE ?";

    String column = "";
    switch (criterion) {
    case "lecture":
      column = "lecture_name";
      break;
    case "prof":
      column = "prof_name";
      break;
    case "major":
      column = "major_name";
      break;
    default:
      column = "lecture_name";
    }

    String sql = firstSQL + column + lastSQL;
    List<Lecture> lectureList = null;

    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);

      String strToSearch = "%%" + input + "%%";

      pstmt.setString(1, strToSearch);
      rs = pstmt.executeQuery();

      lectureList = new ArrayList<>();
      while (rs.next()) {
        Lecture lecture = new Lecture();
        lecture.setId(rs.getInt("id"));
        lecture.setLectureName(rs.getString("lecture_name"));
        lecture.setCredit(rs.getInt("credit"));
        lecture.setTime(rs.getString("time"));
        lecture.setLectureType(rs.getString("lecture_type"));
        lecture.setProfName(rs.getString("prof_name"));
        lecture.setMajorName(rs.getString("major_name"));
        lecture.setLectureTypeId(rs.getInt("lecture_type_id"));
        lecture.setMajorId(rs.getInt("major_id"));
        lecture.setProfId(rs.getInt("prof_id"));
        lectureList.add(lecture);
      }
    } catch (SQLException e) {
      System.out.println("selectByName: " + e.getMessage());
    } finally {
      try {
        rs.close();
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("selectByName: " + e.getMessage());
      }
    }

    return lectureList;
  }

  /*
   * @method Name: selectByLectureType
   * 
   * @date: 2019. 5. 7
   * 
   * @author: 윤종석
   * 
   * @description: 종별로 강의를 검색한다
   * 
   * @param spec: int lectureType
   * 
   * @return: List<Lecture>
   */
  public List<Lecture> selectByLectureType(int lectureType) {
    String sql = "SELECT l.id as id, lecture_name, credit, time, lecture_type,"
        + "prof_name, major_name, lt.id as lecture_type_id, m.id as major_id, p.id as prof_id "
        + "FROM lecture l " + "LEFT JOIN lecturetype lt "
        + "ON l.lecture_type_id = lt.id " + "LEFT JOIN professor p "
        + "ON l.prof_id = p.id " + "LEFT JOIN major m " + "ON p.major_id = m.id"
        + "WHERE lt.id = ?";
    List<Lecture> lectureList = null;
    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, lectureType);
      rs = pstmt.executeQuery();

      lectureList = new ArrayList<>();
      while (rs.next()) {
        Lecture lecture = new Lecture();
        lecture.setId(rs.getInt("id"));
        lecture.setLectureName(rs.getString("lecture_name"));
        lecture.setCredit(rs.getInt("credit"));
        lecture.setTime(rs.getString("time"));
        lecture.setLectureType(rs.getString("lecture_type"));
        lecture.setProfName(rs.getString("prof_name"));
        lecture.setMajorName(rs.getString("major_name"));
        lecture.setLectureTypeId(rs.getInt("lecture_type_id"));
        lecture.setMajorId(rs.getInt("major_id"));
        lecture.setProfId(rs.getInt("prof_id"));
        lectureList.add(lecture);
      }

    } catch (SQLException e) {
      System.out.println("selectByLectureType: " + e.getMessage());
    } finally {
      try {
        rs.close();
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("selectByLectureType: " + e.getMessage());
      }
    }
    return lectureList;
  }

  /*
   * @method Name: sortLecture
   * 
   * @date: 2019. 5. 7
   * 
   * @author: 윤종석
   * 
   * @description: 입력 기준에 따라 정렬된 강의 데이터를 불러온다.
   * 
   * @param spec: String criterion(기준)
   * 
   * @return: List<Lecture>
   */
  public List<Lecture> sortLecture(String criterion) {
    String firstSQL = "SELECT l.id as id, lecture_name, credit, time, lecture_type,"
        + "prof_name, major_name, lt.id as lecture_type_id, m.id as major_id, p.id as prof_id "
        + "FROM lecture l " + "LEFT JOIN lecturetype lt "
        + "ON l.lecture_type_id = lt.id " + "LEFT JOIN professor p "
        + "ON l.prof_id = p.id " + "LEFT JOIN major m "
        + "ON p.major_id = m.id " + "ORDER BY ";

    String order = "";
    String lastSQL = " asc ";
    switch (criterion) {
    case "basic":
      order = "id";
      break;
    case "lecture":
      order = "lecture_name";
      break;
    case "credit":
      order = "credit";
      break;
    case "prof":
      order = "prof_name";
      break;
    case "major":
      order = "major_name";
      break;
    default:
      order = "id";
    }

    String sql = firstSQL + order + lastSQL;
    List<Lecture> lectureList = null;
    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);

      rs = pstmt.executeQuery();

      lectureList = new ArrayList<>();
      while (rs.next()) {
        Lecture lecture = new Lecture();
        lecture.setId(rs.getInt("id"));
        lecture.setLectureName(rs.getString("lecture_name"));
        lecture.setCredit(rs.getInt("credit"));
        lecture.setTime(rs.getString("time"));
        lecture.setLectureType(rs.getString("lecture_type"));
        lecture.setProfName(rs.getString("prof_name"));
        lecture.setMajorName(rs.getString("major_name"));
        lecture.setLectureTypeId(rs.getInt("lecture_type_id"));
        lecture.setMajorId(rs.getInt("major_id"));
        lecture.setProfId(rs.getInt("prof_id"));
        lectureList.add(lecture);
      }
    } catch (SQLException e) {
      System.out.println("sortLecture: " + e.getMessage());
    } finally {
      try {
        rs.close();
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("sortLecture: " + e.getMessage());
      }

    }

    return lectureList;
  }
  /*
   * @method Name: countByLecture
   * 
   * @date: 2019. 5. 10
   * 
   * @author: 정성윤
   * 
   * @description: 학과별 강의수를 가져온다
   * 
   * @param spec: none
   * 
   * @return: JSON
   */

  public JSONObject countByLecture() {
    String sql = "select " + "m.major_name, count(*) as count "
        + "from lecture l " + "left join professor p " + "on l.prof_id = p.id "
        + "left join major m " + "on p.major_id = m.id "
        + "group by m.major_name " + "order by m.major_name";

    JSONObject json = null;
    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      json = new JSONObject();
      while (rs.next()) {
        json.put(rs.getString("major_name"), rs.getInt("count"));
      }
    } catch (SQLException e) {
      System.out.println("countByLecture: " + e.getMessage());
    } finally {
      try {
        rs.close();
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("countByLecture: " + e.getMessage());
      }

    }
    return json;
  }

  /*
   * @method Name: countByTime
   * 
   * @date: 2019. 5. 10
   * 
   * @author: 정성윤
   * 
   * @description: 요일별 수업개수를 가져온다
   * 
   * @param spec: none
   * 
   * @return: JSON
   */
  public JSONObject countByTime() {
    String sql = "select substr(time, 1, 1) as day, "
        + "count(substr(time, 1, 1)) as count from lecture "
        + "group by substr(time, 1, 1) "
        + "order by field(day, '월','화','수','목','금')";
    JSONObject json = null;
    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      json = new JSONObject();
      while (rs.next()) {
        json.put(rs.getString("day"), rs.getInt("count"));
      }
    } catch (SQLException e) {
      System.out.println("countByTime: " + e.getMessage());
    } finally {
      try {
        rs.close();
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("countByTime: " + e.getMessage());
      }
    }
    return json;
  }

  /*
   * @method Name: countByProfessor
   * 
   * @date: 2019. 5. 10
   * 
   * @author: 정성윤
   * 
   * @description: 학과별 교수 인원 수를 가져온다
   * 
   * @param spec: none
   * 
   * @return: JSON
   */

  public JSONObject countByProfessor() {
    String sql = "select m.major_name as major_name, count(m.major_name) as count "
        + "from professor p left join major m " + "on p.major_id = m.id "
        + "group by m.major_name";

    JSONObject json = null;
    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      json = new JSONObject();

      while (rs.next()) {
        json.put(rs.getString("major_name"), rs.getInt("count"));
      }
    } catch (SQLException e) {
      System.out.println("countByProfessor: " + e.getMessage());
    } finally {
      try {
        rs.close();
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("countByProfessor: " + e.getMessage());
      }
    }
    return json;

  }

  public Lecture selectById(int id) {
    Lecture lecture = null;
    String sql = "select * from lecture l left join lectureType t on l.lecture_type_id = t.id "
        + "left join professor p on l.prof_id = p.id where l.id = ?";

    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, id);
      rs = pstmt.executeQuery();

      if (rs.next()) {
        lecture = new Lecture();
        lecture.setLectureName(rs.getString("lecture_name"));
        lecture.setCredit(rs.getInt("credit"));
        lecture.setTime(rs.getString("time"));
        lecture.setLectureType(rs.getString("lecture_type"));
        lecture.setProfName(rs.getString("prof_name"));
        lecture.setId(rs.getInt("id"));
        lecture.setLectureTypeId(rs.getInt("lecture_type_id"));
        lecture.setProfId(rs.getInt("prof_id"));
      }
    } catch (SQLException e) {
      System.out.println("selectById: " + e.getMessage());
    } finally {
      try {
        rs.close();
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("selectById: " + e.getMessage());
      }
    }
    return lecture;
  }

  /*
   * @method Name: countHowManyLectureList
   * 
   * @date: 2019. 5. 12
   * 
   * @author: 정성윤
   * 
   * @description: 강의 개수를 구한다
   * 
   * @param spec: none
   * 
   * @return: int
   */

  public int countHowManyLectureList() {
    String sql = "select count(id) as countLectureList from lecture";
    int count = 0;
    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();

      rs.next();
      count = rs.getInt("countLectureList");
    } catch (SQLException e) {
      System.out.println("countHowManyLectureList: " + e.getMessage());
    } finally {
      try {
        rs.close();
        pstmt.close();
        rs.close();
      } catch (SQLException e) {
        System.out.println("countHowManyLectureList: " + e.getMessage());
      }
    }
    return count;

  }

  /*
   * @method Name: countHowManyPostWithOption
   * 
   * @date: 2019. 5. 12
   * 
   * @author: 정성윤
   * 
   * @description: 특정 조건 하에서 게시판 글 개수를 구한다
   * 
   * @param spec: int page
   * 
   * @return: List<Lecture>
   */

  public List<Lecture> getLectureByPage(int page) {
    String sql1 = "set @rownum:=0";
    String sql2 = "select * from "
        + "(select @rownum:=@rownum + 1 as no, l.id, l.lecture_name, l.credit, "
        + "l.time, t.lecture_type as lecturetype, p.prof_name as profname, "
        + "m.major_name as majorname "
        + "from lecture l left join lecturetype t on l.lecture_type_id = t.id "
        + "left join professor p on l.prof_id = p.id "
        + "left join major m on p.major_id = m.id) u "
        + "where no > ? limit 20";
    List<Lecture> list = new ArrayList<Lecture>();
    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql1);
      pstmt.executeUpdate();
      pstmt = conn.prepareStatement(sql2);
      pstmt.setInt(1, (page - 1) * 20);

      rs = pstmt.executeQuery();

      list = new ArrayList<Lecture>();
      while (rs.next()) {
        Lecture lecture = new Lecture();
        lecture.setId(rs.getInt("id"));
        lecture.setLectureName(rs.getString("lecture_name"));
        lecture.setCredit(rs.getInt("credit"));
        lecture.setTime(rs.getString("time"));
        lecture.setLectureType(rs.getString("lecturetype"));
        lecture.setProfName(rs.getString("profname"));
        lecture.setMajorName(rs.getString("majorname"));
        list.add(lecture);
      }
    } catch (SQLException e) {
      System.out.println("getLectureByPage: " + e.getMessage());
    } finally {
      try {
        rs.close();
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("getLectureByPage: " + e.getMessage());
      }
    }
    return list;
  }

  public List<Lecture> getLectureSortByOption(int page, String option) {

    String sql1 = "set @rownum:=0";
    String firstSQL = "select * from (select @rownum:=@rownum +1 as no, c.* from (select l.id as id, lecture_name, credit, time, lecture_type, "
        + "prof_name, major_name, lt.id as lecture_type_id, m.id as major_id, p.id as prof_id FROM lecture l "
        + "LEFT JOIN lecturetype lt ON l.lecture_type_id = lt.id LEFT JOIN professor p "
        + "ON l.prof_id = p.id LEFT JOIN major m ON p.major_id = m.id";
    String test = "order by prof_name asc) c) c2 where no > ? limit 20";
    String order = "";

    switch (option) {
    case "basic":
      order = " id";
      break;
    case "lecture":
      order = " lecture_name";
      break;
    case "credit":
      order = " credit";
      break;
    case "prof":
      order = " prof_name";
      break;
    case "major":
      order = " major_name";
      break;
    default:
      order = " id";
    }
    String thirdSQL = " ORDER BY " + order + " asc) c) c2 ";
    String lastSQL2 = " where no > ? limit 20";
    String sql = firstSQL + thirdSQL + lastSQL2;
    List<Lecture> lectureList = null;
    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql1);
      pstmt.executeUpdate();
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, (page - 1) * 20);
      rs = pstmt.executeQuery();

      lectureList = new ArrayList<>();
      while (rs.next()) {
        Lecture lecture = new Lecture();
        lecture.setId(rs.getInt("id"));
        lecture.setLectureName(rs.getString("lecture_name"));
        lecture.setCredit(rs.getInt("credit"));
        lecture.setTime(rs.getString("time"));
        lecture.setLectureType(rs.getString("lecture_type"));
        lecture.setProfName(rs.getString("prof_name"));
        lecture.setMajorName(rs.getString("major_name"));
        lecture.setLectureTypeId(rs.getInt("lecture_type_id"));
        lecture.setMajorId(rs.getInt("major_id"));
        lecture.setProfId(rs.getInt("prof_id"));
        lectureList.add(lecture);
      }
    } catch (SQLException e) {
      System.out.println("getLectureSortByOption: " + e.getMessage());
    } finally {
      try {
        rs.close();
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("getLectureSortByOption: " + e.getMessage());
      }
    }
    return lectureList;
  }

  public int countHowManyLectureWithOption(String option, String word) {
    String sql1 = "select count(*) "
        + "from lecture l left join lecturetype t on l.lecture_type_id = t.id "
        + "left join professor p on l.prof_id = p.id "
        + "left join major m on p.major_id = m.id " + "where ";
    String sql2 = "";
    word = "%" + word + "%";
    int count = 0;
    try {
      conn = ds.getConnection();
      switch (option) {
      case "lecture":
        sql2 = "lecture_name LIKE ? ";
        pstmt = conn.prepareStatement(sql1 + sql2);
        pstmt.setString(1, word);
        break;
      case "prof":
        sql2 = "prof_name LIKE ? ";
        pstmt = conn.prepareStatement(sql1 + sql2);
        pstmt.setString(1, word);
        break;
      case "major":
        sql2 = "major_name LIKE ? ";
        pstmt = conn.prepareStatement(sql1 + sql2);
        pstmt.setString(1, word);
        break;
      }

      rs = pstmt.executeQuery();
      rs.next();
      count = rs.getInt(1);
    } catch (SQLException e) {
      System.out.println("countHowManyLectureWithOption: " + e.getMessage());
    } finally {
      try {
        rs.close();
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("countHowManyLectureWithOption: " + e.getMessage());
      }
    }
    return count;
  }

  public List<Lecture> getLectureBySearchWord(int page, String option,
      String word) {
    String sql1 = "set @rownum:=0";
    String firstSQL = "select * from "
        + "(select @rownum:=@rownum +1 as no, l.id as id, lecture_name, credit, time, lecture_type, "
        + "prof_name, major_name, lt.id as lecture_type_id, m.id as major_id, p.id as prof_id FROM lecture l "
        + "LEFT JOIN lecturetype lt ON l.lecture_type_id = lt.id LEFT JOIN professor p "
        + "ON l.prof_id = p.id LEFT JOIN major m ON p.major_id = m.id where ";
    String order = "";
    word = "'%" + word + "%') ";
    switch (option) {
    case "lecture":
      order = " lecture_name ";
      break;
    case "prof":
      order = " prof_name ";
      break;
    case "major":
      order = " major_name ";
      break;
    default:
      order = " lecture_name ";
    }
    String thirdSQL = order + " like " + word + " c";
    String lastSQL2 = " where no > ? limit 20";
    String sql = firstSQL + thirdSQL + lastSQL2;
    List<Lecture> lectureList = null;
    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql1);
      pstmt.executeUpdate();
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, (page - 1) * 20);
      rs = pstmt.executeQuery();

      lectureList = new ArrayList<>();
      while (rs.next()) {
        Lecture lecture = new Lecture();
        lecture.setId(rs.getInt("id"));
        lecture.setLectureName(rs.getString("lecture_name"));
        lecture.setCredit(rs.getInt("credit"));
        lecture.setTime(rs.getString("time"));
        lecture.setLectureType(rs.getString("lecture_type"));
        lecture.setProfName(rs.getString("prof_name"));
        lecture.setMajorName(rs.getString("major_name"));
        lecture.setLectureTypeId(rs.getInt("lecture_type_id"));
        lecture.setMajorId(rs.getInt("major_id"));
        lecture.setProfId(rs.getInt("prof_id"));
        lectureList.add(lecture);
      }
    } catch (SQLException e) {
      System.out.println("getLectureBySearchWord: " + e.getMessage());
    } finally {
      try {
        rs.close();
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("getLectureBySearchWord: " + e.getMessage());
      }
    }
    return lectureList;
  }

  public List<Professor> getProfessorListByMajorId() {
    List<Professor> professorList = null;
    Professor professor = null;
    String sql = "select p.*, m.major_name from professor p inner join major m on p.major_id = m.id";
    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      professorList = new ArrayList<Professor>();
      while (rs.next()) {
        professor = new Professor();
        professor.setId(rs.getInt(1));
        professor.setProfName(rs.getString(2));
        professor.setMajorId(rs.getInt(3));
        professor.setMajorName(rs.getString(4));
        professorList.add(professor);
      }
    } catch (Exception e) {
      System.out.println("getProfessorListByMajorId" + e.getMessage());
    } finally {
      try {
        rs.close();
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("getProfessorListByMajorId" + e.getMessage());
      }
    }

    return professorList;
  }

}
