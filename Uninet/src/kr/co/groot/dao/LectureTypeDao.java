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

  public LectureTypeDao() throws NamingException {

    Context context = new InitialContext();
    ds = (DataSource) context.lookup("java:comp/env/jdbc/mysql");

  }
  
  public List<LectureType> selectAll() throws SQLException {
    String sql = "select * from lecturetype";
    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    rs = pstmt.executeQuery();
    List<LectureType> lectureTypeList = new ArrayList<LectureType>();
    while(rs.next()) {
      LectureType lt = new LectureType();
      lt.setId(rs.getInt(1));
      lt.setLectureType(rs.getString(2));
      lectureTypeList.add(lt);
    }
    rs.close();
    pstmt.close();
    conn.close();
    
    return lectureTypeList;
  }
  
  
}
