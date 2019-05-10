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
import kr.co.groot.dto.Professor;




public class ProfessorDao {

  private Connection conn;
  private PreparedStatement pstmt;
  private ResultSet rs;
  private DataSource ds;

  public ProfessorDao() throws NamingException {

    Context context = new InitialContext();
    ds = (DataSource) context.lookup("java:comp/env/jdbc/mysql");

  }
  
  /*
   * @method Name: selectAll
   * 
   * @date: 2019. 5. 8
   * 
   * @author: 정성윤
   * 
   * @description: 교수정보를  모두 가져온다
   * 
   * @param spec: none
   * 
   * @return: List<Professor>
   */
  
  public List<Professor> selectAll() throws SQLException {
    String sql = "select * from professor";
    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    rs = pstmt.executeQuery();
    List<Professor> profList = new ArrayList<Professor>();
    while(rs.next()) {
      Professor pf = new Professor();
      pf.setId(rs.getInt(1));
      pf.setProfName(rs.getString(2));
      pf.setMajorId(rs.getInt(3));
      profList.add(pf);
    }
    rs.close();
    pstmt.close();
    conn.close();
    
    return profList;
  }
  
  
}
