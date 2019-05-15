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

import kr.co.groot.dto.LectureType;

public class LectureTypeDao {
  private Connection conn;
  private PreparedStatement pstmt;
  private ResultSet rs;
  private DataSource ds;

  public LectureTypeDao() {
    Context context;
    try {
      context = new InitialContext();
      ds = (DataSource) context.lookup("java:comp/env/jdbc/mysql");
    } catch (NamingException e) {
      System.out.println("LectureTypeDao: " + e.getMessage());
    }
  }

  /*
   * @method Name: selectAll
   * 
   * @date: 2019. 5. 8
   * 
   * @author: 정성윤
   * 
   * @description: 종별정보를 모두 가져온다
   * 
   * @param spec: none
   * 
   * @return: List<LectureType>
   */
  public List<LectureType> selectAll() {
    String sql = "select * from lecturetype";
    List<LectureType> lectureTypeList = new ArrayList<LectureType>();

    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();

      while (rs.next()) {
        LectureType lt = new LectureType();
        lt.setId(rs.getInt(1));
        lt.setLectureType(rs.getString(2));
        lectureTypeList.add(lt);
      }
    } catch (SQLException e) {
      System.out.println("selectAll: " + e.getMessage());
    } finally {
      try {
        rs.close();
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("selectAll: " + e.getMessage());
      }
    }

    return lectureTypeList;
  }

}
