package kr.co.groot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
  
	public List<Staff> selectAll(){
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
	
	public int updateStaff(Staff staff) {
	  
	  
		return row;
	}
	
	public List<Staff> selectByDept(){
		return null;
	}
	
	public List<Staff> selectByName(){
		return null;
	}
	
	public Staff selectById() {
		return null;
	}
}
