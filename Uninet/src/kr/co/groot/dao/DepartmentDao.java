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

import kr.co.groot.dto.Department;

public class DepartmentDao {
  private Connection conn;
  private PreparedStatement pstmt;
  private ResultSet rs;
  private DataSource ds;
  
  public DepartmentDao() {
    Context context;
    try {
      context = new InitialContext();
      ds = (DataSource) context.lookup("java:comp/env/jdbc/mysql");
    } catch (NamingException e) {
      System.out.println("DepartmentDao:"+e.getMessage());
    }
  }
  
  public List<Department> getDistinctDeptName() {
    String sql = "select distinct id, dept_name from department";
    List<Department> nameList = new ArrayList<>();
    
    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);
      
      rs = pstmt.executeQuery();
      while (rs.next()) {
    	  Department department = new Department();
    	  department.setId(rs.getInt(1));
    	  department.setDeptName(rs.getString(2));
    	  nameList.add(department);
      }
    } catch (SQLException e) {
      System.out.println("getDistinctDeptName:"+e.getMessage());
  
    } finally {
      try {
        rs.close();
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("getDistinctDeptName:"+e.getMessage());
      }
    }
    return nameList;
  }
}
