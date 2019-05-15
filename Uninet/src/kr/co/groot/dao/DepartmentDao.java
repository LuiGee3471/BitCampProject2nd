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
      System.out.println("DepartmentDao:" + e.getMessage());
    }
  }

  /*
   * @method Name: getDistinctDeptName
   * 
   * @date: 2019. 5. 15
   * 
   * @author: 곽호원
   * 
   * @description: 부서 이름을 중복제거해서 가져온다
   * 
   * @param spec: none
   * 
   * @return: List<String>
   */
  public List<String> getDistinctDeptName() {
    List<String> nameList = new ArrayList<String>();
    String sql = "select distinct dept_name from department";

    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);

      rs = pstmt.executeQuery();
      while (rs.next()) {
        nameList.add(rs.getString(1));
      }
    } catch (SQLException e) {
      System.out.println("getDistinctDeptName:" + e.getMessage());

    } finally {
      try {
        rs.close();
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("getDistinctDeptName:" + e.getMessage());
      }
    }
    return nameList;
  }

  /*
   * @method Name: getDistinctDeptNameList
   * 
   * @date: 2019. 5. 15
   * 
   * @author: 곽호원
   * 
   * @description: 부서 이름과 id를 중복제거해서 가져온다
   * 
   * @param spec: none
   * 
   * @return: List<Department>
   */
  public List<Department> getDistinctDeptNameList() {
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
      System.out.println("getDistinctDeptName:" + e.getMessage());

    } finally {
      try {
        rs.close();
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("getDistinctDeptName:" + e.getMessage());
      }
    }
    return nameList;
  }
}
