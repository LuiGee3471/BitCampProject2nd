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
		String sql = "select * from staff s " + "left join department d " + "on dept_id = d.id ";

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

	public Staff selectStaff(String id) throws SQLException {
		String sql = "select * from staff left join department "
				+ "on staff.dept_id = department.id where staff_id = ?";
		Staff staff = new Staff();

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

		rs.close();
		pstmt.close();
		conn.close();
		
		return staff;
	}

	public Staff selectInfo(int id) throws SQLException {
		String sql = "select * from staff left join department "
				+ "on staff.dept_id = department.id where staff.id = ?";
		Staff staff = new Staff();

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
		
		rs.close();
    pstmt.close();
    conn.close();

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
	public int insertStaff(Staff staff) throws SQLException {
		int row = 0;
		String sql = "insert into staff(staff_id, password, staff_name, phonenumber," + "email, birthday, dept_id) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";

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

	/*
	 * @method Name: updateStaff
	 * 
	 * @date: 2019. 5. 7
	 * 
	 * @author: 윤종석
	 * 
	 * @description: 교직원의 정보를 수정한다.
	 * 
	 * @param spec: Staff staff
	 *
	 * @return: int
	 */
	public int updateStaff(Staff staff) throws SQLException {
		String sql = "update staff set staff email = ?, phoneNumber = ?, "
				+ "staff_name = ?, birthday = ?, isAdmin = ?, isManager = ?, " + "dept_id = ? where id = ?";

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
	public int updateInfo(Staff staff) throws SQLException {
		int row = 0;
		String sql = "update staff set email = ?, phoneNumber = ?, staff_name = ? , self_introduce = ? where id =?";
		conn = ds.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, staff.getEmail());
		pstmt.setString(2, staff.getPhoneNumber());
		pstmt.setString(3, staff.getStaffName());
		pstmt.setString(4, staff.getSelfIntroduce());
		pstmt.setInt(5, staff.getId());
		
		System.out.println(">" + staff.getSelfIntroduce() + "<");
		row = pstmt.executeUpdate();

		pstmt.close();
		conn.close();
		return row;
	}

	/*
	 * @method Name: selectByDept
	 * 
	 * @date: 2019. 5. 7
	 * 
	 * @author: 윤종석
	 * 
	 * @description: 부서별로 교직원을 검색한다
	 * 
	 * @param spec: int deptId
	 * 
	 * @return: List<Staff>
	 */
	public List<Staff> selectByDept(int deptId) throws SQLException {
		String sql = "select * from staff s " + "left join department d " + "on dept_id = d.id " + "where dept_id = ?";

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

	/*
	 * @method Name: selectByName
	 * 
	 * @date: 2019. 5. 7
	 * 
	 * @author: 윤종석
	 * 
	 * @description: 입력한 값을 포함하는 이름을 가진 교직원을 불러온다
	 * 
	 * @param spec: String input
	 * 
	 * @return: List<Staff>
	 */
	public List<Staff> selectByName(String select, String input) throws SQLException {
		String fristSQL = "select * from staff s left join department d " + "on dept_id = d.id where ";

		String lastSQL = " LIKE ?";
		String column = "";

		switch (select) {
		case "name":
			column = "staff_name";
			break;
		case "deptName":
			column = "dept_name";
			break;
		default:
			column = "staff_name";
		}

		String sql = fristSQL + column + lastSQL;

		conn = ds.getConnection();
		pstmt = conn.prepareStatement(sql);
		String strToSearch = "%%" + input + "%%";
		pstmt.setString(1, strToSearch);

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
	 * @method Name: selectById
	 * 
	 * @date: 2019. 5. 7
	 * 
	 * @author: 윤종석
	 * 
	 * @description: 입력한 값을 포함하는 아이디를 가진 교직원을 검색한다.
	 * 
	 * @param spec: String input
	 * 
	 * @return: List<Staff>
	 */
	public List<Staff> selectById(String input) throws SQLException {
		String sql = "select * from staff s " + "left join department d " + "on dept_id = d.id "
				+ "where staff_id like ?";

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

	public Staff selectByUniqueId(int id) throws SQLException {
		String sql = "select s.*, d.* from staff s inner join department d on s.dept_id = d.id where s.id = ?";

		conn = ds.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		rs = pstmt.executeQuery();
		Staff staff = new Staff();
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
		}
		rs.close();
		pstmt.close();
		conn.close();

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
	public int updatePwd(Staff staff) throws Exception {
		int row = 0;
		String sql = "update staff set password = ? where id=?";

		conn = ds.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, staff.getPassword());
		pstmt.setInt(2, staff.getId());

		row = pstmt.executeUpdate();

		pstmt.close();
		conn.close();
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
	public int updateImage(Staff staff) throws Exception {
		int row = 0;
		String sql = "update staff set image = ? where id = ?";
		conn = ds.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, staff.getImage());
		pstmt.setInt(2, staff.getId());

		row = pstmt.executeUpdate();

		pstmt.close();
		conn.close();
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
	public int modifyStaff(Staff staff) throws Exception {
		int row = 0;
		String sql = "update staff set phoneNumber = ? , isAdmin = ? , isManager = ? , dept_id = ? where id = ? ";
		conn = ds.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, staff.getPhoneNumber());
		pstmt.setString(2, staff.getIsAdmin());
		pstmt.setString(3, staff.getIsManager());
		pstmt.setInt(4, staff.getDeptId());
		pstmt.setInt(5, staff.getId());

		row = pstmt.executeUpdate();

		pstmt.close();
		conn.close();

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
	public int countHowManyStaff() throws Exception {
		String sql = "select count(*) from staff";

		conn = ds.getConnection();
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();

		rs.next();
		int count = rs.getInt(1);

		rs.close();
		pstmt.close();
		conn.close();

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
	public int countHowManyStaffWithOption(String option, String word) throws Exception {
		String sql1 = "select count(*) from staff s inner join department d on s.dept_id = d.id where ";
		String sql2 = "";
		word = "%" + word + "%";
		conn = ds.getConnection();

		switch (option) {
		case "deptname":
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
		int count = rs.getInt(1);

		rs.close();
		pstmt.close();
		conn.close();

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
	public List<Staff> getStaffByPage(int page) throws Exception{
	  String sql1 = "set @rownum:=0";
	  String sql2 = "select * "
	  		+ "from (select @rownum:=@rownum + 1 as no, s.*, d.dept_name "
	  		+ "from staff s left join department d on s.dept_id = d.id "
	  		+ "order by staff_name asc) q where no > ? limit 20";
	  conn = ds.getConnection();
	  pstmt = conn.prepareStatement(sql1);
	  pstmt.executeQuery();
	  pstmt = conn.prepareStatement(sql2);
	  pstmt.setInt(1, (page -1) * 20);
	  
	  rs = pstmt.executeQuery();
	  
	  List<Staff> list = new ArrayList<Staff>();
	  while(rs.next()) {
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
	  rs.close();
	  pstmt.close();
	  conn.close();

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
	public List<Staff> getStaffByOption(int page, String option, String word) throws Exception {
		word = "%" + word + "%1";
		
		String sql1 = "set @rownum=0";
		String sql2 = "select * from ";
		String column = "";
		switch(column) {
		case "name" :
			column = "staff_name like " + word;
			break;
			
		case "deptname" :
			column = "dept_name like " +word;
			break;
		}
		
		String sql3 = "(select @rownum:=@rownum + 1 as no, s.*, d.dept_name " + 
				"from staff s left join department d on s.dept_id = d.id " + 
				"order by staff_name asc) q";
		String sql4 = " where no > ? "
					+ "limit 20";
		String sql =  sql2+sql3+sql4;
		
		conn = ds.getConnection();
		pstmt = conn.prepareStatement(sql1);
		pstmt.executeUpdate();
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, (page-1)*20);
		rs = pstmt.executeQuery();
		
		List<Staff> list = new ArrayList<Staff>();
		while(rs.next()) {
			Staff staff = new Staff();
			staff.setId(rs.getInt("id"));
			  staff.setStaffId(rs.getString("staff_id"));
			  staff.setStaffName(rs.getString("staff_name"));
			  staff.setPassword(rs.getString("password"));
			  staff.setEmail(rs.getString("email"));
			  staff.setPhoneNumber(rs.getString("phoneNumber"));
			  staff.setBirthday(rs.getTimestamp("birthday"));
			  staff.setDeptName(rs.getString("deptName"));
			  list.add(staff);
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return list;
		
	}
}
