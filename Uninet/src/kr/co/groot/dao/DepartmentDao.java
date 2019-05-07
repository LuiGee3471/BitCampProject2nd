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

public class DepartmentDao {
  private Connection conn;
  private PreparedStatement pstmt;
  private ResultSet rs;
  private DataSource ds;
  
  public DepartmentDao() throws NamingException {
    Context context = new InitialContext();
    ds = (DataSource) context.lookup("java:comp/env/jdbc/mysql");
  }
  
  public List<String> getDistinctDeptName() throws SQLException {
    String sql = "select distinct dept_name from department";
    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    rs = pstmt.executeQuery();
    
    List<String> nameList = new ArrayList<>();
    while (rs.next()) {
      nameList.add(rs.getString(1));
    }
    
    rs.close();
    pstmt.close();
    conn.close();
    
    return nameList;
  }
}
