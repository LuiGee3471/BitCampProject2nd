package kr.co.groot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.co.groot.dto.Comment;
import kr.co.groot.dto.Post;

public class CommentDao {

  private Connection conn;
  private PreparedStatement pstmt;
  private ResultSet rs;
  private DataSource ds;

  public CommentDao() {
    try {
      Context context = new InitialContext();
      ds = (DataSource) context.lookup("java:comp/env/jdbc/mysql");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
  /*
   * @method Name:  selectAll
   * @date: 2019. 5. 7.
   * @author: 곽호원 
   * @description: 댓글 전체 검색. 
   * @param spec: void 
   * @return: List<Comment> 
   */

  public List<Comment> selectAll() throws Exception {
    List<Comment> commentList = new ArrayList<Comment>();
    String sql = "select * from comment";

    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    rs = pstmt.executeQuery();

    while (rs.next()) {
      Comment comment = new Comment();
      comment.setId(rs.getInt("id"));
      comment.setTitle(rs.getString("title"));
      comment.setContent(rs.getString("content"));
      comment.setWriterId(rs.getInt("writerId"));
      comment.setDate(rs.getTimestamp("date"));
      comment.setCount(rs.getInt("count"));
      comment.setRefer(rs.getInt("refer"));
      comment.setReferComment(rs.getInt("referComment"));
      commentList.add(comment);
    }
    rs.close();
    pstmt.close();
    conn.close();
    return commentList;
  }

  /*
   * @method Name:  selectAll
   * @date: 2019. 5. 7.
   * @author: 곽호원 
   * @description: 댓글 추가. 
   * @param spec: Comment comment 
   * @return: int 
   */
  public int insertComment(Comment comment) throws Exception {
    int row = 0;
    String sql = "insert into comment(title, content, writerId, date, count, refer, referComment) values (?,?,?,?,?,?,?)";
    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, comment.getTitle());
    pstmt.setString(2, comment.getContent());
    pstmt.setInt(3, comment.getWriterId());
    pstmt.setTimestamp(4, comment.getDate());
    pstmt.setInt(5, comment.getCount());
    pstmt.setInt(6, comment.getRefer());
    pstmt.setInt(7, comment.getReferComment());
    row = pstmt.executeUpdate();
    pstmt.close();
    conn.close();
    return row;
  }
  /*
   * @method Name:  selectAll
   * @date: 2019. 5. 7.
   * @author: 곽호원 
   * @description: 댓글 수정. 
   * @param spec: Comment comment 
   * @return: int 
   */
  public int updateComment(Comment comment) throws Exception {
    int row = 0;
    String sql = "update comment set title= ?, content= ? where writerId= ?";
    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, comment.getTitle());
    pstmt.setString(2, comment.getContent());
    pstmt.setInt(3, comment.getWriterId());
    row = pstmt.executeUpdate();
    pstmt.close();
    conn.close();
    return row;
  }

  /*
   * @method Name:  selectAll
   * @date: 2019. 5. 7.
   * @author: 곽호원 
   * @description: 댓글 삭제. 
   * @param spec: int writerId 
   * @return: int 
   */
  public int deleteComment(int writerId) throws Exception {
    int row = 0;
    String sql = "delete from comment where writerId= ?";
    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, writerId);
    pstmt.close();
    conn.close();
    return row;
  }

  /*
   * @method Name:  selectAll
   * @date: 2019. 5. 7.
   * @author: 곽호원 
   * @description: 내가 쓴 댓글 검색 
   * @param spec: int writerId 
   * @return: List<Post> 
   */
  public List<Post> selectByWriter(int writerId) throws Exception {
    List<Post> postList = new ArrayList<Post>();
    String sql = "select p.id, title, p.content, p.writer_id, p.time, p.count from post p" + "LEFT join comment c"
        + "on p.id = c.refer" + "where c.writer_id = ?";
    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, writerId);
    rs = pstmt.executeQuery();
    while (rs.next()) {
      Post post = new Post();
      post.setId(rs.getInt("id"));
      post.setTitle(rs.getString("title"));
      post.setContent(rs.getString("content"));
      post.setWriterId(rs.getInt("writerId"));
      post.setTime(rs.getTimestamp("time"));
      post.setCount(rs.getInt("count"));
      postList.add(post);

    }
    rs.close();
    pstmt.close();
    conn.close();
    return postList;
  }
}
