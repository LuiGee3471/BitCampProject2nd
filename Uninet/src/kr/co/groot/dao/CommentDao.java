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

import kr.co.groot.dto.Comment;
import kr.co.groot.dto.Post;

public class CommentDao {

  private Connection conn;
  private PreparedStatement pstmt;
  private ResultSet rs;
  private DataSource ds;

  public CommentDao() throws NamingException {
    Context context = new InitialContext();
    ds = (DataSource) context.lookup("java:comp/env/jdbc/mysql");
  }

  public List<Comment> getCommentList(int id) throws SQLException, NamingException {

    String sql = "select comment.*, round(time_to_sec(timediff(NOW(), time)) / 60) as diff, " 
        + "date_format(time, '%m/%d %H:%i') as timeFormat, "
        + "staff.staff_id "
        + "from comment "
        + "left join staff "
        + "on comment.writer_id = staff.id "
        + "where refer = ? "
        + "order by refer_comment asc, time asc";

    List<Comment> list = new ArrayList<>();
    StaffDao staffDao = new StaffDao();
    
    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, id);
    rs = pstmt.executeQuery();

    while (rs.next()) {
      Comment comment = new Comment();
      comment.setId(rs.getInt("id"));
      comment.setWriterId(rs.getInt("writer_id"));
      comment.setContent(rs.getString("content"));
      comment.setTime(rs.getTimestamp("time"));
      comment.setRecomment(rs.getString("recomment"));
      comment.setRefer(rs.getInt("refer"));
      comment.setReferComment(rs.getInt("refer_comment"));
      comment.setWriter(staffDao.selectByUniqueId(rs.getInt("writer_id")));
      comment.setDiff(rs.getLong("diff"));
      comment.setTimeFormat(rs.getString("timeFormat"));
      comment.setRecommentCount(rs.getInt("recomment_count"));
      
      list.add(comment);
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
    return list;
  }
  /*
   * @method Name: selectAll
   * 
   * @date: 2019. 5. 7.
   * 
   * @author: 곽호원
   * 
   * @description: 댓글 전체 검색.
   * 
   * @param spec: void
   * 
   * @return: List<Comment>
   */

  public List<Comment> selectAll(int refer) throws Exception {
    List<Comment> commentList = new ArrayList<Comment>();
    String sql = "select s.staff_id, c.content, c.time from post p LEFT join comment c on p.id = c.refer left join staff s on p.writer_id = s.id where refer =?";

    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, refer);
    rs = pstmt.executeQuery();

    while (rs.next()) {
      Comment comment = new Comment();
      comment.setContent(rs.getString("content"));
      comment.setWriterId(rs.getInt("writerId"));
      comment.setTime(rs.getTimestamp("time"));
      comment.setRefer(rs.getInt("refer"));
      comment.setReferComment(rs.getInt("referComment"));
      comment.setRecomment(rs.getString("recomment"));
      commentList.add(comment);
    }
    rs.close();
    pstmt.close();
    conn.close();
    return commentList;
  }

  /*
   * @method Name: insertComment
   * 
   * @date: 2019. 5. 7.
   * 
   * @author: 곽호원
   * 
   * @description: 댓글 추가.
   * 
   * @param spec: Comment comment
   * 
   * @return: int
   */
  public int insertComment(Comment comment) throws Exception {
    int row = 0;
    String sql = "insert into comment(content, writer_id, time, refer) values (?,?,NOW(),?)";
    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, comment.getContent());
    pstmt.setInt(2, comment.getWriterId());
    pstmt.setInt(3, comment.getRefer());
    row = pstmt.executeUpdate();
    pstmt.close();
    conn.close();
    return row;
  }

  /*
   * @method Name: updateComment
   * 
   * @date: 2019. 5. 7.
   * 
   * @author: 곽호원
   * 
   * @description: 댓글 수정.
   * 
   * @param spec: Comment comment
   * 
   * @return: int
   */
  public int updateComment(Comment comment) throws Exception {
    int row = 0;
    String sql = "update comment set content= ? where writer_id = ?";
    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, comment.getContent());
    pstmt.setInt(2, comment.getWriterId());
    row = pstmt.executeUpdate();
    pstmt.close();
    conn.close();
    return row;
  }

  /*
   * @method Name: deleteComment
   * 
   * @date: 2019. 5. 7.
   * 
   * @author: 곽호원
   * 
   * @description: 댓글 삭제.
   * 
   * @param spec: int writerId
   * 
   * @return: int
   */
  // refer
  public int deleteComment(int id) throws Exception {
    int row = 0;
    String sql = "update comment set content='삭제된 댓글입니다.' where id= ?";
    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, id);
    row =pstmt.executeUpdate();
    System.out.println("row"+row);
   

    pstmt.close();
    conn.close();
    return row;
  }
  
  public int countRecomment(int id) throws Exception {
    int result = 0;
    int row = 0;
    String sql = "select count(*) as count from comment where refer_comment = ?";
    String sql2 = "update comment set recomment_count = ? where id = ?";
    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, id);
    rs = pstmt.executeQuery();
    
    while(rs.next()) {
      result = rs.getInt("count");
    }
    System.out.println("result:"+result);
    pstmt = conn.prepareStatement(sql2);
    pstmt.setInt(1,result);
    pstmt.setInt(2, id);
    row = pstmt.executeUpdate();
    
    pstmt.close();
    conn.close();
    return row;
  }
  

  /*
   * @method Name: selectByWriter
   * 
   * @date: 2019. 5. 7.
   * 
   * @author: 곽호원
   * 
   * @description: 내가 쓴 댓글 검색
   * 
   * @param spec: int writerId
   * 
   * @return: List<Post>
   */
  public List<Post> selectByWriter(int writerId) throws Exception {
    List<Post> postList = new ArrayList<Post>();
    String sql = "select distinct p.id,date_format(p.time, '%m/%d %H:%i') as timeFormat,p.boardtype_id, p.title, p.content, p.writer_id, p.time, p.count from post p" + " LEFT join comment c"
        + " on p.id = c.refer" + " where c.writer_id = ?";
    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, writerId);
    rs = pstmt.executeQuery();
    CommentDao dao = new CommentDao();
    while (rs.next()) {
      Post post = new Post();
      post.setId(rs.getInt("id"));
      post.setTitle(rs.getString("title"));
      post.setBoardType(rs.getInt("boardtype_id"));
      post.setTimeFormat(rs.getString("timeFormat"));
      post.setContent(rs.getString("content"));
      post.setWriterId(rs.getInt("writer_id"));
      post.setTime(rs.getTimestamp("time"));
      post.setCount(rs.getInt("count"));
      post.setCommentCount(dao.getCommentCount(post.getId()));
      postList.add(post);
    }
    rs.close();
    pstmt.close();
    conn.close();
    return postList;
  }
  
  public int getCommentCount(int id) throws SQLException {
    String sql = "select count(*) as count from comment where refer = ? and content!='삭제된 댓글입니다.'";
    
    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, id);
    
    rs = pstmt.executeQuery();
    rs.next();
    
    int row = rs.getInt(1);
    
    rs.close();
    pstmt.close();
    conn.close();
    
    return row;
  }
  
  public int insertRecomment(Comment comment) throws Exception {
    int row = 0;
    String sql = "insert into comment(content, writer_id, time, refer,recomment, refer_comment) values (?,?,NOW(),?,'Y',?)";
    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, comment.getContent());
    pstmt.setInt(2, comment.getWriterId());
    pstmt.setInt(3, comment.getRefer());
    pstmt.setInt(4, comment.getReferComment());
    
    row = pstmt.executeUpdate();
    pstmt.close();
    conn.close();
    return row;
  }
}