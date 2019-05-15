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

import kr.co.groot.dto.Professor;
import kr.co.groot.dto.Staff;

public class StaffDao {
  private Connection conn;
  private PreparedStatement pstmt;
  private ResultSet rs;
  private DataSource ds;

  public StaffDao() {
    Context context;
    try {
      context = new InitialContext();
      ds = (DataSource) context.lookup("java:comp/env/jdbc/mysql");
    } catch (NamingException e) {
      System.out.println("StaffDao: " + e.getMessage());
    }
  }
  
  /*
   * @method Name: selectStaff
   * 
   * @date: 2019. 5. 10
   * 
   * @author: 정성윤
   * 
   * @description: 교직원을 가져오는 함수
   * 
   * @param spec: String id
   * 
   * @return: Staff
   */
  public Staff selectStaff(String id) {
    String sql = "select * from staff left join department "
        + "on staff.dept_id = department.id where binary staff_id = ?";
    Staff staff = new Staff();

    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, id);
      rs = pstmt.executeQuery();

      if (rs.next()) {
        staff.setId(rs.getInt("id"));
        staff.setStaffId(rs.getString(2));
        staff.setPassword(rs.getString(3));
        staff.setEmail(rs.getString(4));
        staff.setPhoneNumber(rs.getString(5));
        staff.setStaffName(rs.getString(6));
        staff.setBirthday(rs.getTimestamp(7));
        staff.setImage(rs.getString(8));
        staff.setIsAdmin(rs.getString(9));
        staff.setIsManager(rs.getString(10));
        staff.setDeptId(rs.getInt(11));
        staff.setDeptName(rs.getString("dept_name"));
        staff.setSelfIntroduce(rs.getString("self_introduce"));
      }
    } catch (SQLException e) {
      System.out.println("selectStaff: " + e.getMessage());
    } finally {
      try {
        rs.close();
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("selectStaff: " + e.getMessage());
      }
    }

    return staff;
  }

  /*
   * @method Name: selectInfo
   * 
   * @date: 2019. 5. 10
   * 
   * @author: 정성윤
   * 
   * @description: 해당 교직원의 부서이름을 받아오는 함수
   * 
   * @param spec: int id
   * 
   * @return: Staff
   */
  
  public Staff selectInfo(int id) {
    String sql = "select * from staff left join department "
        + "on staff.dept_id = department.id where staff.id = ?";
    Staff staff = new Staff();

    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, id);
      rs = pstmt.executeQuery();

      if (rs.next()) {
        staff.setId(rs.getInt("id"));
        staff.setStaffId(rs.getString(2));
        staff.setPassword(rs.getString(3));
        staff.setEmail(rs.getString(4));
        staff.setPhoneNumber(rs.getString(5));
        staff.setStaffName(rs.getString(6));
        staff.setBirthday(rs.getTimestamp(7));
        staff.setImage(rs.getString(8));
        staff.setIsAdmin(rs.getString(9));
        staff.setIsManager(rs.getString(10));
        staff.setDeptId(rs.getInt(11));
        staff.setDeptName(rs.getString("dept_name"));
      }
    } catch (SQLException e) {
      System.out.println("selectInfo: " + e.getMessage());
    } finally {
      try {
        rs.close();
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("selectInfo: " + e.getMessage());
      }
    }

    return staff;
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
  public int insertStaff(Staff staff) {
    int row = 0;
    String sql = "insert into staff(staff_id, password, staff_name, phonenumber,"
        + "email, birthday, dept_id) " + "VALUES (?, ?, ?, ?, ?, ?, ?)";

    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, staff.getStaffId());
      pstmt.setString(2, staff.getPassword());
      pstmt.setString(3, staff.getStaffName());
      pstmt.setString(4, staff.getPhoneNumber());
      pstmt.setString(5, staff.getEmail());
      pstmt.setTimestamp(6, staff.getBirthday());
      pstmt.setInt(7, staff.getDeptId());

      row = pstmt.executeUpdate();
    } catch (SQLException e) {
      System.out.println("insertStaff: " + e.getMessage());
    } finally {
      try {
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("insertStaff: " + e.getMessage());
      }
    }

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

  public int deleteStaff(int id) {
    int row = 0;
    String sql = "delete from staff where id = ?";

    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, id);

      row = pstmt.executeUpdate();
    } catch (SQLException e) {
      System.out.println("deleteStaff: " + e.getMessage());
    } finally {
      try {
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("deleteStaff: " + e.getMessage());
      }
    }

    return row;
  }

  /*
   * @method Name: updateInfo
   * 
   * @date: 2019. 5. 9
   * 
   * @author: 곽호원
   * 
   * @description: 마이페이지에서 개인 정보 수정.
   * 
   * @param spec: Staff staff
   *
   * @return: int
   */
  public int updateInfo(Staff staff) {
    int row = 0;
    String sql = "update staff set email = ?, phoneNumber = ?, staff_name = ? , self_introduce = ? where id =?";

    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, staff.getEmail());
      pstmt.setString(2, staff.getPhoneNumber());
      pstmt.setString(3, staff.getStaffName());
      pstmt.setString(4, staff.getSelfIntroduce());
      pstmt.setInt(5, staff.getId());

      row = pstmt.executeUpdate();
    } catch (SQLException e) {
      System.out.println("updateInfo: " + e.getMessage());
    } finally {
      try {
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("updateInfo: " + e.getMessage());
      }
    }

    return row;
  }

  public Staff selectByUniqueId(int id) {
    Staff staff = new Staff();
    String sql = "select s.*, d.* from staff s inner join department d on s.dept_id = d.id where s.id = ?";

    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, id);
      rs = pstmt.executeQuery();

      if (rs.next()) {
        staff.setId(rs.getInt("id"));
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
        staff.setSelfIntroduce(rs.getString("self_introduce"));
      }
    } catch (SQLException e) {
      System.out.println("selectByUniqueId: " + e.getMessage());
    } finally {
      try {
        rs.close();
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("selectByUniqueId: " + e.getMessage());
      }
    }

    return staff;
  }

  /*
   * @method Name: updatePwd
   * 
   * @date: 2019. 5. 9
   * 
   * @author: 곽호원
   * 
   * @description: 마이페이지에서 비밀번호를 수정한다.
   * 
   * @param spec: Staff staff
   *
   * @return: int
   */
  public int updatePwd(Staff staff) {
    int row = 0;
    String sql = "update staff set password = ? where id=?";

    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, staff.getPassword());
      pstmt.setInt(2, staff.getId());

      row = pstmt.executeUpdate();
    } catch (SQLException e) {
      System.out.println("updatePwd: " + e.getMessage());
    } finally {
      try {
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("updatePwd: " + e.getMessage());
      }
    }

    return row;
  }

  /*
   * @method Name: updateImage
   * 
   * @date: 2019. 5. 9
   * 
   * @author: 곽호원
   * 
   * @description: 마이페이지에서 프로필 이미지를 변경.
   * 
   * @param spec: Staff staff
   *
   * @return: int
   */
  public int updateImage(Staff staff) {
    int row = 0;
    String sql = "update staff set image = ? where id = ?";

    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, staff.getImage());
      pstmt.setInt(2, staff.getId());

      row = pstmt.executeUpdate();
    } catch (SQLException e) {
      System.out.println("updateImage: " + e.getMessage());
    } finally {
      try {
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("updateImage: " + e.getMessage());
      }
    }

    return row;
  }

  /*
   * @method Name: modifyStaff
   * 
   * @date: 2019. 5. 10
   * 
   * @author: 곽호원
   * 
   * @description: 관리자페이지에서 직원 정보 변경.
   * 
   * @param spec: Staff staff
   *
   * @return: int
   */
  public int modifyStaff(Staff staff) {
    int row = 0;
    String sql = "update staff set phoneNumber = ? , isAdmin = ? , isManager = ? , dept_id = ? where id = ? ";
    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, staff.getPhoneNumber());
      pstmt.setString(2, staff.getIsAdmin());
      pstmt.setString(3, staff.getIsManager());
      pstmt.setInt(4, staff.getDeptId());
      pstmt.setInt(5, staff.getId());

      row = pstmt.executeUpdate();
    } catch (SQLException e) {
      System.out.println("modifyStaff: " + e.getMessage());
    } finally {
      try {
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("modifyStaff: " + e.getMessage());
      }
    }

    return row;
  }

  /*
   * @method Name: countHowManyStaff
   * 
   * @date: 2019. 5. 11
   * 
   * @author: 곽호원
   * 
   * @description: 직원의 수를 구한다
   * 
   * @param spec: x
   * 
   * @return: int
   */
  public int countHowManyStaff() {
    String sql = "select count(*) from staff";
    int count = 0;

    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();

      rs.next();
      count = rs.getInt(1);
    } catch (SQLException e) {
      System.out.println("countHowManyStaff: " + e.getMessage());
    } finally {
      try {
        rs.close();
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("countHowManyStaff: " + e.getMessage());
      }
    }

    return count;
  }

  /*
   * @method Name: countHowManyStaffWithOption
   * 
   * @date: 2019. 5. 11
   * 
   * @author: 곽호원
   * 
   * @description:특정 조건 하에서 직원의 수를 구한다
   * 
   * @param spec: String option , String word
   * 
   * @return: int
   */
  public int countHowManyStaffWithOption(String option, String word) {
    int count = 0;

    String sql1 = "select count(*) from staff s inner join department d on s.dept_id = d.id where ";
    String sql2 = "";
    word = "%" + word + "%";

    try {
      conn = ds.getConnection();

      switch (option) {
      case "deptName":
        sql2 = "d.dept_name like ? ";
        pstmt = conn.prepareStatement(sql1 + sql2);
        pstmt.setString(1, word);
        break;

      case "name":
        sql2 = "s.staff_name like ? ";
        pstmt = conn.prepareStatement(sql1 + sql2);
        pstmt.setString(1, word);
        break;
      }
      rs = pstmt.executeQuery();

      rs.next();
      count = rs.getInt(1);
    } catch (SQLException e) {
      System.out.println("countHowManyStaffWithOption: " + e.getMessage());
    } finally {
      try {
        rs.close();
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("countHowManyStaffWithOption: " + e.getMessage());
      }
    }

    return count;
  }

  /*
   * @method Name: getStaffByPage
   * 
   * @date: 2019. 5. 11
   * 
   * @author: 곽호원
   * 
   * @description: 관리자 페이지에서 직원의 리스트를 페이징 처리하여 출력한다
   * 
   * @param spec: int page
   * 
   * @return: list
   */
  public List<Staff> getStaffByPage(int page) {
    List<Staff> list = new ArrayList<Staff>();
    String sql1 = "set @rownum:=0";
    String sql2 = "select * "
        + "from (select @rownum:=@rownum + 1 as no, s.*, d.dept_name "
        + "from staff s left join department d on s.dept_id = d.id "
        + "order by staff_name asc) q where no > ? limit 20";

    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql1);
      pstmt.executeQuery();
      pstmt = conn.prepareStatement(sql2);
      pstmt.setInt(1, (page - 1) * 20);

      rs = pstmt.executeQuery();

      while (rs.next()) {
        Staff staff = new Staff();
        staff.setId(rs.getInt("id"));
        staff.setStaffId(rs.getString("staff_id"));
        staff.setStaffName(rs.getString("staff_name"));
        staff.setPassword(rs.getString("password"));
        staff.setEmail(rs.getString("email"));
        staff.setPhoneNumber(rs.getString("phoneNumber"));
        staff.setBirthday(rs.getTimestamp("birthday"));
        staff.setDeptName(rs.getString("dept_name"));
        list.add(staff);
      }
    } catch (SQLException e) {
      System.out.println("getStaffByPage: " + e.getMessage());
    } finally {
      try {
        rs.close();
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("getStaffByPage: " + e.getMessage());
      }
    }

    return list;
  }

  /*
   * @method Name: getStaffByOption
   * 
   * @date: 2019. 5. 11
   * 
   * @author: 곽호원
   * 
   * @description:특정 조건 하에서 관리자 페이지에서 직원의 리스트를 페이징 처리하여 출력한다
   * 
   * @param spec: int page String option , String word
   * 
   * @return: list
   */
  public List<Staff> getStaffByOption(int page, String option, String word) {
    List<Staff> list = new ArrayList<Staff>();

    word = "%" + word + "%";

    String sql1 = "set @rownum=0";
    String sql2 = "select * from ";
    String column = "";
    switch (option) {
    case "name":
      column = "staff_name like ?";
      break;
    case "deptName":
      column = "dept_name like ?";
      break;
    }
    

    String sql3 = "(select @rownum:=@rownum + 1 as no, s.*, d.dept_name "
        + "from staff s "
        + "left join department d "
        + "on s.dept_id = d.id "
        + "where "
        + column
        + " order by staff_name asc) q";
    String sql4 = " where no > ? " + "limit 20";
    String sql = sql2 + sql3 + sql4;

    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql1);
      pstmt.executeUpdate();

      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, word);
      pstmt.setInt(2, (page - 1) * 20);
      rs = pstmt.executeQuery();

      while (rs.next()) {
        Staff staff = new Staff();
        staff.setId(rs.getInt("id"));
        staff.setStaffId(rs.getString("staff_id"));
        staff.setStaffName(rs.getString("staff_name"));
        staff.setPassword(rs.getString("password"));
        staff.setEmail(rs.getString("email"));
        staff.setPhoneNumber(rs.getString("phoneNumber"));
        staff.setBirthday(rs.getTimestamp("birthday"));
        staff.setDeptName(rs.getString("dept_name"));
        list.add(staff);
      }
    } catch (SQLException e) {
      System.out.println("getStaffByOption: " + e.getMessage());
    } finally {
      try {
        rs.close();
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("getStaffByOption: " + e.getMessage());
      }
    }

    return list;
  }
}
