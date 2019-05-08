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

import kr.co.groot.dto.Major;



public class MajorDao {

  private Connection conn;
  private PreparedStatement pstmt;
  private ResultSet rs;
  private DataSource ds;

  public MajorDao() throws NamingException {

    Context context = new InitialContext();
    ds = (DataSource) context.lookup("java:comp/env/jdbc/mysql");

  }
  
  public List<Major> selectAll() throws SQLException {
    String sql = "select * from major";
    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    rs = pstmt.executeQuery();
    List<Major> majorList = new ArrayList<Major>();
    while(rs.next()) {
      Major m = new Major();
      m.setId(rs.getInt(1));
      m.setMajorName(rs.getString(2));
      majorList.add(m);
    }
    rs.close();
    pstmt.close();
    conn.close();
    
    return majorList;
  }
  
  
}
