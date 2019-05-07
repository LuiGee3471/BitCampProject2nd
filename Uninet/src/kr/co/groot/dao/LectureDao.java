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

  public List<Lecture> selectAll() throws SQLException {
    String sql = "SELECT l.id as id, lecture_name, credit, time, lecture_type,"
        + "prof_name, major_name, lt.id as lecture_type_id, m.id as major_id, p.id as prof_id "
        + "FROM lecture l " + "LEFT JOIN lecturetype lt "
        + "ON l.lecture_type_id = lt.id " + "LEFT JOIN professor p "
        + "ON l.prof_id = p.id " + "LEFT JOIN major m "
        + "ON p.major_id = m.id";

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

    System.out.println("강의 목록: " + lectureList);

    return lectureList;
  }

  public int insertLecture(Lecture lecture) throws SQLException {
    String sql = "insert into lecture (lecture_name, credit, time, "
        + "lecture_type_id, prof_id) values (?, ?, ?, ?, ?)";

    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, lecture.getLectureName());
    pstmt.setInt(2, lecture.getCredit());
    pstmt.setString(3, lecture.getTime());
    pstmt.setInt(4, lecture.getLectureTypeId());
    pstmt.setInt(5, lecture.getMajorId());

    int row = pstmt.executeUpdate();

    pstmt.close();
    conn.close();

    return row;
  }

  public int deleteLecture(int id) throws SQLException {
    String sql = "delete from lecture where id = ?";

    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, id);

    int row = pstmt.executeUpdate();

    return row;
  }

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

  public List<Lecture> selectByName(String criterion, String input) throws SQLException {
    String sql = "SELECT l.id as id, lecture_name, credit, time, lecture_type,"
        + "prof_name, major_name, lt.id as lecture_type_id, m.id as major_id, p.id as prof_id "
        + "FROM lecture l " + "LEFT JOIN lecturetype lt "
        + "ON l.lecture_type_id = lt.id " + "LEFT JOIN professor p "
        + "ON l.prof_id = p.id " + "LEFT JOIN major m "
        + "ON p.major_id = m.id"
        + "WHERE ? LIKE ?";
    
    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
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
    
    String strToSearch = "%" + input + "%";
    pstmt.setString(1, column);
    pstmt.setString(2, strToSearch);
    
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

    System.out.println("강의 목록: " + lectureList);

    return lectureList;   
  }

  public List<Lecture> selectByLectureType(int lectureType) throws SQLException {
    String sql = "SELECT l.id as id, lecture_name, credit, time, lecture_type,"
        + "prof_name, major_name, lt.id as lecture_type_id, m.id as major_id, p.id as prof_id "
        + "FROM lecture l " + "LEFT JOIN lecturetype lt "
        + "ON l.lecture_type_id = lt.id " + "LEFT JOIN professor p "
        + "ON l.prof_id = p.id " + "LEFT JOIN major m "
        + "ON p.major_id = m.id"
        + "WHERE lt.id = ?";
    
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

    System.out.println("강의 목록: " + lectureList);

    return lectureList;
  }

  public List<Lecture> sortLecture(String criterion) throws SQLException {
    String sql = "SELECT l.id as id, lecture_name, credit, time, lecture_type,"
        + "prof_name, major_name, lt.id as lecture_type_id, m.id as major_id, p.id as prof_id "
        + "FROM lecture l " + "LEFT JOIN lecturetype lt "
        + "ON l.lecture_type_id = lt.id " + "LEFT JOIN professor p "
        + "ON l.prof_id = p.id " + "LEFT JOIN major m "
        + "ON p.major_id = m.id"
        + "ORDER BY ? asc";
    
    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    String order = "";
    
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
    
    pstmt.setString(1, order);
    
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

    System.out.println("강의 목록: " + lectureList);

    return lectureList;   
  }
}
