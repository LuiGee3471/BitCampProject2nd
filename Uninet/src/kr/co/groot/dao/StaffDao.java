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

  /*
   * @method Name: selectAll
   * 
   * @date: 2019. 5. 7
   * 
   * @author: 정성윤
   * 
   * @description: 부서별 관리자의 데이터를 모두 가져오기 위해서 사용한다
   * 
   * @param spec: none
   * 
   * @return: List<Staff>
   */
  
  public List<Staff> selectAll() throws SQLException {
    String sql = "select * from staff s " + 
        "left join department d " + 
        "on dept_id = d.id ";
    
    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    
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
  
  /*
   * @method Name: insertStaff
   * 
   * @date: 2019. 5. 7
   * 
   * @author: 정성윤
   * 
   * @description: 부서별 관리자의 데이터를 삽입하기 위해서 사용한다
   * 
   * @param spec: Staff staff
   * 
   * @return: int
   */
  public int insertStaff(Staff staff) throws SQLException {
    int row = 0;
    String sql = "insert into staff(staff_id, password, staff_name, phone_number," 
                + "email, birthday, dept_id, image, isManager, isAdmin) "
        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, staff.getStaffId());
    pstmt.setString(2, staff.getPassword());
    pstmt.setString(3, staff.getStaffName());
    pstmt.setString(4, staff.getPhoneNumber());
    pstmt.setString(5, staff.getEmail());
    pstmt.setTimestamp(6, staff.getBirthday());
    pstmt.setInt(7, staff.getDeptId());
    pstmt.setString(8, staff.getImage());
    pstmt.setString(9, staff.getIsManager());
    pstmt.setString(10, staff.getIsAdmin());
    
    row = pstmt.executeUpdate();
    pstmt.close();
    conn.close();
    
    return row;
  }

  /*
   * @method Name: deleteStaff
   * 
   * @date: 2019. 5. 7
   * 
   * @author: 정성윤
   * 
   * @description: 부서별 관리자를 삭제하기 위해 사용한다
   * 
   * @param spec: int id
   * 
   * @return: int
   */
  
  public int deleteStaff(int id) throws SQLException {
    int row = 0;
    String sql = "delete from staff where id = ?";
    
    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, id);
    
    row = pstmt.executeUpdate();
    pstmt.close();
    conn.close();
    
    return row;
  }

  public int updateStaff() {
    return 0;
  }

  public List<Staff> selectByDept() {
    return null;
  }

  public List<Staff> selectByName() {
    return null;
  }

  public Staff selectById() {
    return null;
  }
}
