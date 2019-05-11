package kr.co.groot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
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
   * @method Name: selectAll
   * 
   * @date: 2019. 5. 8
   * 
   * @author: 윤종석
   * 
   * @description: 모든 쪽지 데이터를 가져온다
   * 
   * @param spec: none
   * 
   * @return: List<Message>
   */
  public List<Message> selectAll() throws SQLException {
    List<Message> messagelist = new ArrayList<Message>();
    String sql = "select * from message";

    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    rs = pstmt.executeQuery();

    if (rs.next()) {
      Message message = new Message();
      message.setContent(rs.getString("content"));
      message.setTime(rs.getTimestamp("time"));
      message.setStaffname(rs.getString("s.staff_name"));
      message.setSenderId(rs.getInt("sender_id"));
      messagelist.add(message);
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

  /*
   * @method Name: insertMessage
   * 
   * @date: 2019. 5. 10
   * 
   * @author: 정성윤
   * 
   * @수정 : 윤종석
   * 
   * @description: 쪽지를 삽입하기 위해서 사용한다
   * 
   * @param spec: Message message
   * 
   * @return: int
   */
  public int insertMessage(Message message) throws SQLException {
    int row = 0;
    String sql = "insert into message(content, receiver_id, sender_id, time) values(?, ?, ?, NOW())";

    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, message.getContent());
    pstmt.setInt(2, message.getReceiverId());
    pstmt.setInt(3, message.getSenderId());

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
    List<Message> messagelist = new ArrayList<Message>();
    String sql = "select content, time, s.staff_name AS sender from message m "
        + "LEFT JOIN staff s " + "on m.sender_id = s.id";

    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, receiver_id);
    rs = pstmt.executeQuery();

    if (rs.next()) {
      Message message = new Message();
      message.setContent(rs.getString("content"));
      message.setTime(rs.getTimestamp("time"));
      message.setStaffname(rs.getString("s.staff_name"));
      message.setSenderId(rs.getInt("sender_id"));
      messagelist.add(message);
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

  /*
   * @method Name: selectRecentMessage
   * 
   * @date: 2019. 5. 8
   * 
   * @author: 윤종석
   * 
   * @description: 메인화면에 출력할 최근 쪽지 두개를 가져온다
   * 
   * @param spec: none
   * 
   * @return: List<Message>
   */
  public List<Message> selectRecentMessage(int id) throws SQLException {
    List<Message> messagelist = new ArrayList<Message>();
    String sql = "select m.*, s.staff_name, round(time_to_sec(timediff(NOW(), m.time)) / 60) as diff, " + 
        "date_format(m.time, '%m/%d %H:%i') as timeFormat " + 
        "from message m " + 
        "left join staff s on m.sender_id = s.id " + 
        "where receiver_id = ? " + 
        "order by time desc "
        + "limit 2";

    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, id);
    rs = pstmt.executeQuery();

    while (rs.next()) {
      Message message = new Message();
      message.setId(rs.getInt("id"));
      if (rs.getString("content").length() > 20) {
        message.setContent(rs.getString("content").substring(0, 20) + "...");
      } else {
        message.setContent(rs.getString("content"));  
      }
      message.setTime(rs.getTimestamp("time"));
      message.setReceiverId(rs.getInt("receiver_id"));
      message.setSenderId(rs.getInt("sender_id"));
      message.setStaffname(rs.getString("staff_name"));
      message.setDiff(rs.getLong("diff"));
      message.setTimeFormat(rs.getString("timeFormat"));
      messagelist.add(message);
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
  
  public List<Message> selectUserMessage(int userId) {
	 String sql = "select m.* "
	 		+ "from message m"
	 		+ ""
  }
}
