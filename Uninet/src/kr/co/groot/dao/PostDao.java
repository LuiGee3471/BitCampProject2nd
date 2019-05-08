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
import javax.sql.DataSource;

import kr.co.groot.dto.Comment;
import kr.co.groot.dto.Post;

public class PostDao {
  private Connection conn;
  private PreparedStatement pstmt;
  private ResultSet rs;
  private DataSource ds;

  public PostDao() {
    try {
      Context context = new InitialContext();
      ds = (DataSource) context.lookup("java:comp/env/jdbc/mysql");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  /*
   * @method Name: selectAll
   * 
   * @date: 2019. 5. 7.
   * 
   * @author: 강기훈
   * 
   * @description: 글 목록을 불러온다.
   * 
   * @param spec: 없음
   * 
   * @return: List<Post>
   */
  public List<Post> selectAll() throws SQLException {
    List<Post> list = new ArrayList<>();
    String sql = "select p.title, p.content, p.writer_id, p.time, p.count, p.boardtype_id, p.id, s.staff_id from post p join staff s on p.writer_id = s.id";

    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    rs = pstmt.executeQuery();

    while (rs.next()) {
      Post post = new Post();
      post.setTitle(rs.getString("title"));
      post.setContent(rs.getString("content"));
      post.setWriterId(rs.getInt("writer_id"));
      post.setTime(rs.getTimestamp("time"));
      post.setCount(rs.getInt("count"));
      post.setBoardType(rs.getInt("boardtype_id"));
      post.setId(rs.getInt("id"));
      post.setStaffId(rs.getString("staff_id"));
      list.add(post);
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
   * @method Name: insertPost
   * 
   * @date: 2019. 5. 7.
   * 
   * @author: 강기훈
   * 
   * @description: 게시판에 글을 등록한다.
   * 
   * @param spec: Post post
   * 
   * @return: int
   */
  public int insertPost(Post post) throws SQLException {
    int row = 0;
    String sql = "insert into post (id, title, content, writer_id, time, count, boardtype_id) values (?, ?, ?, ?, ?, ?, ?)";

    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, post.getId());
    pstmt.setString(2, post.getTitle());
    pstmt.setString(3, post.getContent());
    pstmt.setInt(4, post.getWriterId());
    pstmt.setTimestamp(5, post.getTime());
    pstmt.setInt(6, post.getCount());
    pstmt.setInt(7, post.getBoardType());
    row = pstmt.executeUpdate();

    pstmt.close();
    conn.close();

    return row;
  }

  /*
   * @method Name: deletePost
   * 
   * @date: 2019. 5. 7.
   * 
   * @author: 강기훈
   * 
   * @description: 게시판에서 글을 삭제한다.
   * 
   * @param spec: int id
   * 
   * @return: int
   */
  public int deletePost(int id) throws SQLException {
    int row = 0;
    String sql = "delete from post where id = ?";

    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, id);

    row = pstmt.executeUpdate();

    pstmt.close();
    conn.close();

    return row;
  }

  /*
   * @method Name: updatePost
   * 
   * @date: 2019. 5. 7.
   * 
   * @author: 강기훈
   * 
   * @description: 게시판에서 글을 수정한다.
   * 
   * @param spec: Post post
   * 
   * @return: int
   */
  public int updatePost(Post post) throws SQLException {
    int row = 0;
    String sql = "update post set id = ?, title = ?, content = ?, writer_id = ? time = ?, count = ?, boardtype_id = ?";

    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, post.getId());
    pstmt.setString(2, post.getTitle());
    pstmt.setString(3, post.getContent());
    pstmt.setInt(4, post.getWriterId());
    pstmt.setTimestamp(5, post.getTime());
    pstmt.setInt(6, post.getCount());
    pstmt.setInt(7, post.getBoardType());
    row = pstmt.executeUpdate();

    pstmt.close();
    conn.close();

    return row;
  }

  /*
   * @method Name: selectByTitle
   * 
   * @date: 2019. 5. 7.
   * 
   * @author: 강기훈
   * 
   * @description: 제목으로 글을 검색한다.
   * 
   * @param spec: String title
   * 
   * @return: List<Post>
   */
  public List<Post> selectByTitle(String title) throws SQLException {
    List<Post> list = new ArrayList<>();
    String sql = "select * from post where title like ?";

    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    title = "%" + title + "%";
    pstmt.setString(1, title);
    rs = pstmt.executeQuery();

    while (rs.next()) {
      Post post = new Post();
      post.setTitle(rs.getString("title"));
      post.setContent(rs.getString("content"));
      post.setWriterId(rs.getInt("writer_id"));
      post.setTime(rs.getTimestamp("time"));
      post.setCount(rs.getInt("count"));
      post.setBoardType(rs.getInt("boardtype_id"));
      post.setId(rs.getInt("id"));
      list.add(post);
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
   * @method Name: selectByContent
   * 
   * @date: 2019. 5. 7.
   * 
   * @author: 강기훈
   * 
   * @description: 내용으로 글을 검색한다.
   * 
   * @param spec: String content
   * 
   * @return: List<Post>
   */
  public List<Post> selectByContent(String content) throws SQLException {
    List<Post> list = new ArrayList<>();
    String sql = "select * from post where content like ?";

    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    content = "%" + content + "%";
    pstmt.setString(1, content);
    rs = pstmt.executeQuery();

    while (rs.next()) {
      Post post = new Post();
      post.setTitle(rs.getString("title"));
      post.setContent(rs.getString("content"));
      post.setWriterId(rs.getInt("writer_id"));
      post.setTime(rs.getTimestamp("time"));
      post.setCount(rs.getInt("count"));
      post.setBoardType(rs.getInt("boardtype_id"));
      post.setId(rs.getInt("id"));
      list.add(post);
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
   * @method Name: selectByAll
   * 
   * @date: 2019. 5. 7.
   * 
   * @author: 강기훈
   * 
   * @description: 제목과 내용으로 글을 검색한다.
   * 
   * @param spec: String all
   * 
   * @return: List<Post>
   */
  public List<Post> selectByAll(String all) throws SQLException {
    List<Post> list = new ArrayList<>();
    String sql = "select * from post where content like ? or title like ?";

    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    all = "%" + all + "%";
    pstmt.setString(1, all);
    pstmt.setString(2, all);
    rs = pstmt.executeQuery();

    while (rs.next()) {
      Post post = new Post();
      post.setTitle(rs.getString("title"));
      post.setContent(rs.getString("content"));
      post.setWriterId(rs.getInt("writer_id"));
      post.setTime(rs.getTimestamp("time"));
      post.setCount(rs.getInt("count"));
      post.setBoardType(rs.getInt("boardtype_id"));
      post.setId(rs.getInt("id"));
      list.add(post);
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
   * @method Name: selectByWriter
   * 
   * @date: 2019. 5. 7.
   * 
   * @author: 강기훈
   * 
   * @description: 특정 작성자가 쓴 글을 검색한다.
   * 
   * @param spec: int writerId
   * 
   * @return: List<Post>
   */
  public List<Post> selectByWriter(int writerId) throws SQLException {
    List<Post> list = new ArrayList<>();
    String sql = "select * from post where writer_id = ?";

    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, writerId);
    rs = pstmt.executeQuery();

    while (rs.next()) {
      Post post = new Post();
      post.setTitle(rs.getString("title"));
      post.setContent(rs.getString("content"));
      post.setWriterId(rs.getInt("writer_id"));
      post.setTime(rs.getTimestamp("time"));
      post.setCount(rs.getInt("count"));
      post.setBoardType(rs.getInt("boardtype_id"));
      post.setId(rs.getInt("id"));
      list.add(post);
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
   * @method Name: selectByCount
   * 
   * @date: 2019. 5. 7.
   * 
   * @author: 강기훈
   * 
   * @description: 조회수가 높은 순으로 글 목록을 불러온다.
   * 
   * @param spec:
   * 
   * @return: List<Post>
   */
  public List<Post> selectByCount() throws SQLException {
    List<Post> list = new ArrayList<>();
    String sql = "select * from post order by count desc";

    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    rs = pstmt.executeQuery();

    while (rs.next()) {
      Post post = new Post();
      post.setTitle(rs.getString("title"));
      post.setContent(rs.getString("content"));
      post.setWriterId(rs.getInt("writer_id"));
      post.setTime(rs.getTimestamp("time"));
      post.setCount(rs.getInt("count"));
      post.setBoardType(rs.getInt("boardtype_id"));
      post.setId(rs.getInt("id"));
      list.add(post);
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
  
  public Post getContent(int id) throws SQLException {
    Post post = new Post();
    String sql = "select * from post where id = ?";
   
    
    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, id);
    rs = pstmt.executeQuery();
    
    while(rs.next()) {
    String title = rs.getString("title");
    String content = rs.getString("content");
    int writerId = rs.getInt("writer_id");
    Timestamp time = rs.getTimestamp("time");
    int count = rs.getInt("count");
    int boardType = rs.getInt("boardtype_id");
    
    post.setId(id);
    post.setTitle(title);
    post.setContent(content);
    post.setWriterId(writerId);
    post.setTime(time);
    post.setCount(count);
    post.setBoardType(boardType);
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
    return post;
  }
  
  public List<Post> selectRecentNotice() throws SQLException {
    List<Post> list = new ArrayList<>();
    String sql = "select * from post where boardtype_id = 1 order by time desc limit 4";

    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    rs = pstmt.executeQuery();

    while (rs.next()) {
      Post post = new Post();
      if (rs.getString("title").length() > 15) {
        post.setTitle(rs.getString("title").substring(0, 15) + "...");
      } else {
        post.setTitle(rs.getString("title"));
      }
      post.setContent(rs.getString("content"));
      post.setWriterId(rs.getInt("writer_id"));
      post.setTime(rs.getTimestamp("time"));
      post.setCount(rs.getInt("count"));
      post.setBoardType(rs.getInt("boardtype_id"));
      post.setId(rs.getInt("id"));
      list.add(post);
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
  
  public List<Post> selectRecentPost() throws SQLException {
    List<Post> list = new ArrayList<>();
    String sql = "select * from post where boardtype_id = 2 order by time desc limit 4";

    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    rs = pstmt.executeQuery();

    while (rs.next()) {
      Post post = new Post();
      if (rs.getString("title").length() > 15) {
        post.setTitle(rs.getString("title").substring(0, 15) + "...");
      } else {
        post.setTitle(rs.getString("title"));
      }
      post.setContent(rs.getString("content"));
      post.setWriterId(rs.getInt("writer_id"));
      post.setTime(rs.getTimestamp("time"));
      post.setCount(rs.getInt("count"));
      post.setBoardType(rs.getInt("boardtype_id"));
      post.setId(rs.getInt("id"));
      list.add(post);
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
  
  public List<Post> selectByCountForMain() throws SQLException {
    List<Post> list = new ArrayList<>();
    String sql = "select * from post order by count desc limit 4";
    
    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    rs = pstmt.executeQuery();
    
   
    while (rs.next()) {
      Post post = new Post();
      if (rs.getString("title").length() > 15) {
        post.setTitle(rs.getString("title").substring(0, 15) + "...");
      } else {
        post.setTitle(rs.getString("title"));
      }
      post.setContent(rs.getString("content"));
      post.setWriterId(rs.getInt("writer_id"));
      post.setTime(rs.getTimestamp("time"));
      post.setCount(rs.getInt("count"));
      post.setBoardType(rs.getInt("boardtype_id"));
      post.setId(rs.getInt("id"));
      list.add(post);
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
}
