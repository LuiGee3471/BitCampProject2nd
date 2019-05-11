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

public class LectureDao {
  private Connection conn;
  private PreparedStatement pstmt;
  private ResultSet rs;
  private DataSource ds;

  public LectureDao() throws NamingException {
    Context context = new InitialContext();
    ds = (DataSource) context.lookup("java:comp/env/jdbc/mysql");
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
  public List<Lecture> selectAll() throws SQLException {
    String sql = "SELECT l.id as id, lecture_name, credit, time, lecture_type,"
        + "prof_name, major_name, lt.id as lecture_type_id, m.id as major_id, p.id as prof_id " + "FROM lecture l "
        + "LEFT JOIN lecturetype lt " + "ON l.lecture_type_id = lt.id " + "LEFT JOIN professor p "
        + "ON l.prof_id = p.id " + "LEFT JOIN major m " + "ON p.major_id = m.id";

    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    rs = pstmt.executeQuery();

    List<Lecture> lectureList = new ArrayList<>();
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

    rs.close();
    pstmt.close();
    conn.close();

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
  public int insertLecture(Lecture lecture) throws SQLException {
    String sql = "insert into lecture (lecture_name, credit, time, "
        + "lecture_type_id, prof_id) values (?, ?, ?, ?, ?)";

    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, lecture.getLectureName());
    pstmt.setInt(2, lecture.getCredit());
    pstmt.setString(3, lecture.getTime());
    pstmt.setInt(4, lecture.getLectureTypeId());
    pstmt.setInt(5, lecture.getProfId());

    int row = pstmt.executeUpdate();

    pstmt.close();
    conn.close();

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
  public int deleteLecture(int id) throws SQLException {
    String sql = "delete from lecture where id = ?";

    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, id);

    int row = pstmt.executeUpdate();

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
  public int updateLecture(Lecture lecture) throws SQLException {
    String sql = "update lecture set lecture_name = ?, credit = ?, time = ?, "
        + "lecture_type_id = ?, prof_id = ? where id = ?";

    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);

    pstmt.setString(1, lecture.getLectureName());
    pstmt.setInt(2, lecture.getCredit());
    pstmt.setString(3, lecture.getTime());
    pstmt.setInt(4, lecture.getLectureTypeId());
    pstmt.setInt(5, lecture.getProfId());
    pstmt.setInt(6, lecture.getId());

    int row = pstmt.executeUpdate();
    pstmt.close();
    conn.close();

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
  public List<Lecture> selectByName(String criterion, String input) throws SQLException {
    String firstSQL = "SELECT l.id as id, lecture_name, credit, time, lecture_type,"
        + "prof_name, major_name, lt.id as lecture_type_id, m.id as major_id, p.id as prof_id " + "FROM lecture l "
        + "LEFT JOIN lecturetype lt " + "ON l.lecture_type_id = lt.id " + "LEFT JOIN professor p "
        + "ON l.prof_id = p.id " + "LEFT JOIN major m " + "ON p.major_id = m.id " + "WHERE ";

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

    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);

    String strToSearch = "%%" + input + "%%";

    pstmt.setString(1, strToSearch);
    rs = pstmt.executeQuery();

    List<Lecture> lectureList = new ArrayList<>();
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

    rs.close();
    pstmt.close();
    conn.close();

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
  public List<Lecture> selectByLectureType(int lectureType) throws SQLException {
    String sql = "SELECT l.id as id, lecture_name, credit, time, lecture_type,"
        + "prof_name, major_name, lt.id as lecture_type_id, m.id as major_id, p.id as prof_id " + "FROM lecture l "
        + "LEFT JOIN lecturetype lt " + "ON l.lecture_type_id = lt.id " + "LEFT JOIN professor p "
        + "ON l.prof_id = p.id " + "LEFT JOIN major m " + "ON p.major_id = m.id" + "WHERE lt.id = ?";

    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, lectureType);
    rs = pstmt.executeQuery();

    List<Lecture> lectureList = new ArrayList<>();
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

    rs.close();
    pstmt.close();
    conn.close();

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
  public List<Lecture> sortLecture(String criterion) throws SQLException {
    String firstSQL = "SELECT l.id as id, lecture_name, credit, time, lecture_type,"
        + "prof_name, major_name, lt.id as lecture_type_id, m.id as major_id, p.id as prof_id " + "FROM lecture l "
        + "LEFT JOIN lecturetype lt " + "ON l.lecture_type_id = lt.id " + "LEFT JOIN professor p "
        + "ON l.prof_id = p.id " + "LEFT JOIN major m " + "ON p.major_id = m.id " + "ORDER BY ";

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
    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);

    rs = pstmt.executeQuery();

    List<Lecture> lectureList = new ArrayList<>();
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

    rs.close();
    pstmt.close();
    conn.close();

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

  public JSONObject countByLecture() throws SQLException {
    String sql = "select " + 
        "m.major_name, count(*) as count " + 
        "from lecture l " + 
        "left join professor p " + 
        "on l.prof_id = p.id " + 
        "left join major m " + 
        "on p.major_id = m.id " + 
        "group by m.major_name " + 
        "order by m.major_name";
    
    JSONObject json = new JSONObject();
    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    rs = pstmt.executeQuery();

    while (rs.next()) {
      json.put(rs.getString("major_name"), rs.getInt("count"));
    }
    rs.close();
    pstmt.close();
    conn.close();

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
  public JSONObject countByTime() throws SQLException {
    String sql = "select substr(time, 1, 1) as day, " + "count(substr(time, 1, 1)) as count from lecture "
        + "group by substr(time, 1, 1) " + "order by field(day, '월','화','수','목','금')";
    JSONObject json = new JSONObject();
    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    rs = pstmt.executeQuery();

    while (rs.next()) {
      json.put(rs.getString("day"), rs.getInt("count"));
    }
    rs.close();
    pstmt.close();
    conn.close();

    System.out.println(json);
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

  public JSONObject countByProfessor() throws SQLException {
    String sql = "select m.major_name as major_name, count(m.major_name) as count "
        + "from professor p left join major m " + "on p.major_id = m.id " + "group by m.major_name";

    JSONObject json = new JSONObject();
    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    rs = pstmt.executeQuery();

    while (rs.next()) {
      json.put(rs.getString("major_name"), rs.getInt("count"));
    }
    rs.close();
    pstmt.close();
    conn.close();

    System.out.println(json);
    return json;

  }

}
