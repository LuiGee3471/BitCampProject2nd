package kr.co.groot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import kr.co.groot.dto.Message;

public class MessageDao {
  private Connection conn;
  private PreparedStatement pstmt;
  private ResultSet rs;
  private DataSource ds;

  public MessageDao() throws NamingException {

    Context context = new InitialContext();
    ds = (DataSource) context.lookup("java:comp/env/jdbc/mysql");

  }

  /*
   * @method Name: insertMessage
   * 
   * @date: 2019. 5. 7
   * 
   * @author: 정성윤
   * 
   * @description: 쪽지를 삽입하기 위해서 사용한다
   * 
   * @param spec: Message message
   * 
   * @return: int
   */

  public int insertMessage(Message message) throws SQLException {
    int row = 0;
    String sql = "insert into message(content) values(?)";

    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, message.getContent());

    row = pstmt.executeUpdate();

    pstmt.close();
    conn.close();

    return row;
  }

  /*
   * @method Name: deleteMessage
   * 
   * @date: 2019. 5. 7
   * 
   * @author: 정성윤
   * 
   * @description: 쪽지를 삭제하기 위해서 사용한다
   * 
   * @param spec: Message message
   * 
   * @return: int
   */
  public int deleteMessage(int message_id) throws SQLException {
    int row = 0;
    String sql = "delete from message where id = ?";

    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, message_id);

    row = pstmt.executeUpdate();

    pstmt.close();
    conn.close();

    return row;
  }

  /*
   * @method Name: selectByReceiver
   * 
   * @date: 2019. 5. 7
   * 
   * @author: 정성윤
   * 
   * @description: 쪽지 리스트를 보여주기 위해서 사용한다
   * 
   * @param spec: int receiver_id
   * 
   * @return: List<Message>
   */
  public List<Message> selectByReceiver(int receiver_id) throws SQLException {
    List<Message> messagelist = null;
    String sql = "select content, time, s.staff_name AS sender from message m\r\n" + "LEFT JOIN staff s\r\n"
        + "on m.sender_id = s.id";

    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, receiver_id);
    rs = pstmt.executeQuery();

    if (rs.next()) {
      Message message = new Message();
      message.setContent(rs.getString("content"));
      message.setTime(rs.getTimestamp("time"));
      message.setStaffname(rs.getString("s.staff_name"));
      message.setSender_id(rs.getInt("sender_id"));
    }
    if (rs != null) {
      rs.close();
    }
    if (pstmt != null) {
      pstmt.close();
    }
    if (conn != null) {
      conn.close();
    }
    return messagelist;
  }
}
