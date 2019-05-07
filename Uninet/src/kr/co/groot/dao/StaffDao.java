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

import kr.co.groot.dto.Staff;

public class StaffDao {
  private Connection conn;
  private PreparedStatement pstmt;
  private ResultSet rs;
  private DataSource ds;

  public StaffDao() throws NamingException {
    Context context = new InitialContext();
    ds = (DataSource) context.lookup("java:comp/env/jdbc/mysql");
  }

  public List<Staff> selectAll() {
    return null;

  }

  public Staff selectStaff() {
    return null;
  }

  public int insertStaff() {
    return 0;
  }

  public int deleteStaff() {
    return 0;
  }

  public int updateStaff(Staff staff) throws SQLException {
    String sql = "update staff set staff email = ?, phoneNumber = ?, "
        + "staff_name = ?, birthday = ?, isAdmin = ?, isManager = ?, "
        + "dept_id = ? where id = ?";

    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, staff.getEmail());
    pstmt.setString(2, staff.getPhoneNumber());
    pstmt.setString(3, staff.getStaffName());
    pstmt.setTimestamp(4, staff.getBirthday());
    pstmt.setString(5, staff.getIsAdmin());
    pstmt.setString(6, staff.getIsManager());
    pstmt.setInt(7, staff.getDeptId());
    pstmt.setInt(8, staff.getId());
    
    int row = pstmt.executeUpdate();
    
    pstmt.close();
    conn.close();
    
    return row;
  }

  public List<Staff> selectByDept(int deptId) throws SQLException {
    String sql = "select * from staff s " + 
        "left join department d " + 
        "on dept_id = d.id " + 
        "where dept_id = ?";
    
    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, deptId);
    
    rs = pstmt.executeQuery();
    
    List<Staff> staffList = new ArrayList<>();
    while (rs.next()) {
      Staff staff = new Staff();
      staff.setId(rs.getInt(1));
      staff.setStaffId(rs.getString("staff_id"));
      staff.setEmail(rs.getString("email"));
      staff.setPhoneNumber(rs.getString("phoneNumber"));
      staff.setStaffName(rs.getString("staff_name"));
      staff.setBirthday(rs.getTimestamp("birthday"));
      staff.setImage(rs.getString("image"));
      staff.setIsAdmin(rs.getString("isAdmin"));
      staff.setIsManager(rs.getString("isManager"));
      staff.setDeptId(rs.getInt("dept_id"));
      staff.setDeptName(rs.getString("dept_name"));
      staffList.add(staff);
    }
    
    rs.close();
    pstmt.close();
    conn.close();
    
    return staffList;
  }

  public List<Staff> selectByName(String input) throws SQLException {
    String sql = "select * from staff s " + 
        "left join department d " + 
        "on dept_id = d.id " + 
        "where staff_name like ?";
    
    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    input = "%" + input + "%";
    pstmt.setString(1, input);
    
    rs = pstmt.executeQuery();
    
    List<Staff> staffList = new ArrayList<>();
    while (rs.next()) {
      Staff staff = new Staff();
      staff.setId(rs.getInt(1));
      staff.setStaffId(rs.getString("staff_id"));
      staff.setEmail(rs.getString("email"));
      staff.setPhoneNumber(rs.getString("phoneNumber"));
      staff.setStaffName(rs.getString("staff_name"));
      staff.setBirthday(rs.getTimestamp("birthday"));
      staff.setImage(rs.getString("image"));
      staff.setIsAdmin(rs.getString("isAdmin"));
      staff.setIsManager(rs.getString("isManager"));
      staff.setDeptId(rs.getInt("dept_id"));
      staff.setDeptName(rs.getString("dept_name"));
      staffList.add(staff);
    }
    
    rs.close();
    pstmt.close();
    conn.close();
    
    return staffList;
  }

  public List<Staff> selectById(String input) throws SQLException {
    String sql = "select * from staff s " + 
        "left join department d " + 
        "on dept_id = d.id " + 
        "where staff_id like ?";
    
    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    input = "%" + input + "%";
    pstmt.setString(1, input);
    
    rs = pstmt.executeQuery();
    
    List<Staff> staffList = new ArrayList<>();
    while (rs.next()) {
      Staff staff = new Staff();
      staff.setId(rs.getInt(1));
      staff.setStaffId(rs.getString("staff_id"));
      staff.setEmail(rs.getString("email"));
      staff.setPhoneNumber(rs.getString("phoneNumber"));
      staff.setStaffName(rs.getString("staff_name"));
      staff.setBirthday(rs.getTimestamp("birthday"));
      staff.setImage(rs.getString("image"));
      staff.setIsAdmin(rs.getString("isAdmin"));
      staff.setIsManager(rs.getString("isManager"));
      staff.setDeptId(rs.getInt("dept_id"));
      staff.setDeptName(rs.getString("dept_name"));
      staffList.add(staff);
    }
    
    rs.close();
    pstmt.close();
    conn.close();
    
    return staffList;
  }
}
