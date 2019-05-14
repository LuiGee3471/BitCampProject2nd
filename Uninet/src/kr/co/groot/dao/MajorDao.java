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

  public MajorDao() {
    Context context;
    try {
      context = new InitialContext();
      ds = (DataSource) context.lookup("java:comp/env/jdbc/mysql");
    } catch (NamingException e) {
      System.out.println("MajorDao: " + e.getMessage());
    }
  }
  
  /*
   * @method Name: selectAll
   * 
   * @date: 2019. 5. 8
   * 
   * @author: 정성윤
   * 
   * @description: 학과정보를  모두 가져온다
   * 
   * @param spec: none
   * 
   * @return: List<Major>
   */ 
  public List<Major> selectAll() {
    List<Major> majorList = new ArrayList<Major>();
    String sql = "select * from major";
    
    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      
      while(rs.next()) {
        Major m = new Major();
        m.setId(rs.getInt(1));
        m.setMajorName(rs.getString(2));
        majorList.add(m);
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

    return majorList;
  }
}
