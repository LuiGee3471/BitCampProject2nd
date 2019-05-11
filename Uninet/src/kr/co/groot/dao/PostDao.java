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

import kr.co.groot.dto.Comment;
import kr.co.groot.dto.Post;

public class PostDao {
  private Connection conn;
  private PreparedStatement pstmt;
  private ResultSet rs;
  private DataSource ds;

  public PostDao() throws NamingException {
    Context context = new InitialContext();
    ds = (DataSource) context.lookup("java:comp/env/jdbc/mysql");
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

  public List<Post> selectPostByBoardType(int boardType) throws SQLException {
    List<Post> list = new ArrayList<>();
    String sql = "select post.*, round(time_to_sec(timediff(NOW(), time)) / 60) as diff, "
        + "date_format(time, '%m/%d %H:%i') as timeFormat, staff.staff_id "
        + "from post " + "left join staff " + "on post.writer_id = staff.id "
        + "where boardtype_id = ? order by time desc limit 20";

    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, boardType);
    rs = pstmt.executeQuery();

    while (rs.next()) {
      Post post = new Post();
      if (rs.getString("title").length() > 30) {
        post.setTitle(rs.getString("title").substring(0, 30) + "...");
      } else {
        post.setTitle(rs.getString("title"));
      }

      if (rs.getString("title").length() > 40) {
        post.setContent(rs.getString("content").substring(0, 40) + "...");
      } else {
        post.setContent(rs.getString("content"));
      }
      post.setWriterId(rs.getInt("writer_id"));
      post.setTime(rs.getTimestamp("time"));
      post.setCount(rs.getInt("count"));
      post.setBoardType(rs.getInt("boardtype_id"));
      post.setId(rs.getInt("id"));
      post.setDiff(rs.getLong("diff"));
      post.setTimeFormat(rs.getString("timeFormat"));
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
    String sql = "insert into post (title, content, writer_id, time, count, boardtype_id)"
        + " values (?, ?, ?, NOW(), 0, ?)";

    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, post.getTitle());
    pstmt.setString(2, post.getContent());
    pstmt.setInt(3, post.getWriterId());
    pstmt.setInt(4, post.getBoardType());
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
  public List<Post> selectByTitle(String title, int boardType)
      throws SQLException {
    List<Post> list = new ArrayList<>();
    String sql = "select post.*, round(time_to_sec(timediff(NOW(), time)) / 60) as diff, "
        + "date_format(time, '%m/%d %H:%i') as timeFormat, staff.staff_id "
        + "from post " + "left join staff " + "on post.writer_id = staff.id "
        + "where title like ? "
        + "and boardtype_id = ? order by time desc limit 20";

    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    title = "%" + title + "%";
    pstmt.setString(1, title);
    pstmt.setInt(2, boardType);
    rs = pstmt.executeQuery();

    while (rs.next()) {
      Post post = new Post();
      post.setTitle(rs.getString("title"));
      post.setContent(rs.getString("content"));
      post.setWriterId(rs.getInt("writer_id"));
      post.setTime(rs.getTimestamp("time"));
      post.setCount(rs.getInt("count"));
      post.setBoardType(rs.getInt("boardtype_id"));
      post.setDiff(rs.getLong("diff"));
      post.setStaffId(rs.getString("staff_id"));
      post.setId(rs.getInt("id"));
      post.setTimeFormat(rs.getString("timeFormat"));
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
  public List<Post> selectByContent(String content, int boardType)
      throws SQLException {
    List<Post> list = new ArrayList<>();
    String sql = "select post.*, round(time_to_sec(timediff(NOW(), time)) / 60) as diff, "
        + "date_format(time, '%m/%d %H:%i') as timeFormat, staff.staff_id "
        + "from post " + "left join staff " + "on post.writer_id = staff.id "
        + "where content like ? "
        + "and boardtype_id = ? order by time desc limit 20";

    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    content = "%" + content + "%";
    pstmt.setString(1, content);
    pstmt.setInt(2, boardType);
    rs = pstmt.executeQuery();

    while (rs.next()) {
      Post post = new Post();
      post.setTitle(rs.getString("title"));
      post.setContent(rs.getString("content"));
      post.setWriterId(rs.getInt("writer_id"));
      post.setTime(rs.getTimestamp("time"));
      post.setCount(rs.getInt("count"));
      post.setBoardType(rs.getInt("boardtype_id"));
      post.setDiff(rs.getLong("diff"));
      post.setStaffId(rs.getString("staff_id"));
      post.setId(rs.getInt("id"));
      post.setTimeFormat(rs.getString("timeFormat"));
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
  public List<Post> selectByAll(String word, int boardType)
      throws SQLException {
    List<Post> list = new ArrayList<>();
    String sql = "select post.*, round(time_to_sec(timediff(NOW(), time)) / 60) as diff, "
        + "date_format(time, '%m/%d %H:%i') as timeFormat, staff.staff_id "
        + "from post " + "left join staff " + "on post.writer_id = staff.id "
        + "where title like ? or content like ? "
        + "and boardtype_id = ? order by time desc limit 20";

    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    word = "%" + word + "%";
    pstmt.setString(1, word);
    pstmt.setString(2, word);
    pstmt.setInt(3, boardType);
    rs = pstmt.executeQuery();

    while (rs.next()) {
      Post post = new Post();
      post.setTitle(rs.getString("title"));
      post.setContent(rs.getString("content"));
      post.setWriterId(rs.getInt("writer_id"));
      post.setTime(rs.getTimestamp("time"));
      post.setCount(rs.getInt("count"));
      post.setBoardType(rs.getInt("boardtype_id"));
      post.setDiff(rs.getLong("diff"));
      post.setStaffId(rs.getString("staff_id"));
      post.setId(rs.getInt("id"));
      post.setTimeFormat(rs.getString("timeFormat"));
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
  // 시간, 게시판 타입, 글 제목, 내용, 조회수, 댓글수
  public List<Post> selectByWriter(int writerId) throws SQLException, NamingException {
    List<Post> list = new ArrayList<>();
    String sql = "select post.*,"
        + "date_format(time, '%m/%d %H:%i') as timeFormat from post"
        + " where writer_id = ?"
        + " order by time desc";
     System.out.println(sql);
    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, writerId);
    rs = pstmt.executeQuery();
    CommentDao dao = new CommentDao();
    while (rs.next()) {
      Post post = new Post();
      post.setTitle(rs.getString("title"));
      post.setContent(rs.getString("content"));
      post.setWriterId(rs.getInt("writer_id"));
      post.setTime(rs.getTimestamp("time"));
      post.setCount(rs.getInt("count"));
      post.setBoardType(rs.getInt("boardtype_id"));
      post.setTimeFormat(rs.getString("timeFormat"));
      post.setId(rs.getInt("id"));
      post.setCommentCount(dao.getCommentCount(post.getId()));
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
  public List<Post> selectByCount(int boardType) throws SQLException {
    List<Post> list = new ArrayList<>();
    String sql = "select post.*, round(time_to_sec(timediff(NOW(), time)) / 60) as diff, "
        + "date_format(time, '%m/%d %H:%i') as timeFormat, staff.staff_id "
        + "from post " + "left join staff " + "on post.writer_id = staff.id "
        + "where boardtype_id = ? " + "order by count desc limit 20";

    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, boardType);
    rs = pstmt.executeQuery();

    while (rs.next()) {
      Post post = new Post();
      post.setTitle(rs.getString("title"));
      post.setContent(rs.getString("content"));
      post.setWriterId(rs.getInt("writer_id"));
      post.setTime(rs.getTimestamp("time"));
      post.setCount(rs.getInt("count"));
      post.setBoardType(rs.getInt("boardtype_id"));
      post.setDiff(rs.getLong("diff"));
      post.setStaffId(rs.getString("staff_id"));
      post.setId(rs.getInt("id"));
      post.setTimeFormat(rs.getString("timeFormat"));
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

  public Post getPost(int id) throws SQLException {
    String sql = "select post.*, round(time_to_sec(timediff(NOW(), time)) / 60) as diff, "
        + "date_format(time, '%m/%d %H:%i') as timeFormat, staff.staff_id "
        + "from post " + "left join staff " + "on post.writer_id = staff.id "
        + "where post.id = ?";

    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, id);
    rs = pstmt.executeQuery();

    Post post = null;
    if (rs.next()) {
      post = new Post();
      post.setTitle(rs.getString("title"));
      post.setContent(rs.getString("content"));
      post.setWriterId(rs.getInt("writer_id"));
      post.setTime(rs.getTimestamp("time"));
      post.setCount(rs.getInt("count"));
      post.setBoardType(rs.getInt("boardtype_id"));
      post.setId(rs.getInt("id"));
      post.setDiff(rs.getLong("diff"));
      post.setTimeFormat(rs.getString("timeFormat"));
      post.setStaffId(rs.getString("staff_id"));
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

  /*
   * @method Name: selectRecentNotice
   * 
   * @date: 2019. 5. 8
   * 
   * @author: 윤종석
   * 
   * @description: 메인화면에 출력할 최근 공지 4개를 가져온다
   * 
   * @param spec: none
   * 
   * @return: List<Post>
   */
  public List<Post> selectRecentNotice() throws SQLException {
    List<Post> list = new ArrayList<>();
    String sql = "select *, round(time_to_sec(timediff(NOW(), time)) / 60) as diff, "
        + "date_format(time, '%m/%d %H:%i') as timeFormat "
        + "from post where boardtype_id = 1 order by time desc limit 4";

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
      post.setDiff(rs.getLong("diff"));
      post.setTimeFormat(rs.getString("timeFormat"));
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
   * @method Name: selectRecentPost
   * 
   * @date: 2019. 5. 8
   * 
   * @author: 윤종석
   * 
   * @description: 메인화면에 출력할 최근 자유게시판 글 4개를 가져온다
   * 
   * @param spec: none
   * 
   * @return: List<Post>
   */
  public List<Post> selectRecentPost() throws SQLException {
    List<Post> list = new ArrayList<>();
    String sql = "select *, round(time_to_sec(timediff(NOW(), time)) / 60) as diff, "
        + "date_format(time, '%m/%d %H:%i') as timeFormat "
        + "from post where boardtype_id = 2 order by time desc limit 4";

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
      post.setDiff(rs.getLong("diff"));
      post.setTimeFormat(rs.getString("timeFormat"));
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
   * @method Name: selectRecentNotice
   * 
   * @date: 2019. 5. 8
   * 
   * @author: 윤종석
   * 
   * @description: 메인화면에 출력할 조회수가 가장 높은 글 4개를 가져온다
   * 
   * @param spec: none
   * 
   * @return: List<Post>
   */
  public List<Post> selectByCountForMain() throws SQLException {
    List<Post> list = new ArrayList<>();
    String sql = "select *, round(time_to_sec(timediff(NOW(), time)) / 60) as diff,"
        + " date_format(time, '%m/%d %H:%i') as timeFormat "
        + "from post order by count desc limit 4";

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
      post.setDiff(rs.getLong("diff"));
      post.setTimeFormat(rs.getString("timeFormat"));
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
   * @method Name: addCount
   * 
   * @date: 2019. 5. 9
   * 
   * @author: 강기훈
   * 
   * @description: 글의 조회수를 높인다.
   * 
   * @param spec: int id
   * 
   * @return: void
   */
  public void addCount(int id) throws SQLException {
    String sql = "Update post set count = count + 1 where id = ?";

    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, id);
    pstmt.executeUpdate();

    pstmt.close();
    conn.close();
  }

  /*
   * @method Name: countHowManyPost
   * 
   * @date: 2019. 5. 10
   * 
   * @author: 윤종석
   * 
   * @description: 게시판의 글 개수를 구한다
   * 
   * @param spec: int boardType
   * 
   * @return: int
   */
  public int countHowManyPost(int boardType) throws SQLException {
    String sql = "select count(*) from post where boardtype_id = ?";

    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, boardType);
    rs = pstmt.executeQuery();

    rs.next();
    int count = rs.getInt(1);

    rs.close();
    pstmt.close();
    conn.close();

    return count;
  }

  /*
   * @method Name: countHowManyPostWithOption
   * 
   * @date: 2019. 5. 10
   * 
   * @author: 윤종석
   * 
   * @description: 특정 조건 하에서 게시판 글 개수를 구한다
   * 
   * @param spec: String option, String word, int boardType
   * 
   * @return: int
   */
  public int countHowManyPostWithOption(String option, String word,
      int boardType) throws SQLException {
    String sql1 = "select count(*) from post where ";
    String sql2 = "";
    String sql3 = "and boardtype_id = ?";
    word = "%" + word + "%";

    conn = ds.getConnection();

    switch (option) {
    case "title":
      sql2 = "title LIKE ? ";
      pstmt = conn.prepareStatement(sql1 + sql2 + sql3);
      System.out.println(sql1 + sql2 + sql3);
      pstmt.setString(1, word);
      pstmt.setInt(2, boardType);
      break;
    case "content":
      sql2 = "content LIKE ? ";
      pstmt = conn.prepareStatement(sql1 + sql2 + sql3);
      System.out.println(sql1 + sql2 + sql3);
      pstmt.setString(1, word);
      pstmt.setInt(2, boardType);
      break;
    case "all":
      sql2 = "(title LIKE ? OR content LIKE ?) ";
      pstmt = conn.prepareStatement(sql1 + sql2 + sql3);
      System.out.println(sql1 + sql2 + sql3);
      pstmt.setString(1, word);
      pstmt.setString(2, word);
      pstmt.setInt(3, boardType);
      break;
    }

    rs = pstmt.executeQuery();

    rs.next();
    int count = rs.getInt(1);
    
    System.out.println("post count: " + count);

    rs.close();
    pstmt.close();
    conn.close();

    return count;
  }

  public List<Post> getPostByPage(int page, int boardType)
      throws SQLException, NamingException {
    CommentDao commentDao = new CommentDao();
    String sql1 = "set @rownum:=0";
    String sql2 = "select * " + "from (select " + "@rownum:=@rownum + 1 as no, "
        + "p.*, " + "round(time_to_sec(timediff(NOW(), time)) / 60) as diff, "
        + "date_format(time, '%m/%d %H:%i') as timeFormat, " + "s.staff_id "
        + "from post p " + "left join staff s " + "on p.writer_id = s.id "
        + "where boardtype_id = ? " + "order by time desc) q " + "where no > ? "
        + "limit 20";

    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql1);
    pstmt.executeUpdate();
    pstmt = conn.prepareStatement(sql2);
    pstmt.setInt(1, boardType);
    pstmt.setInt(2, (page - 1) * 20);

    rs = pstmt.executeQuery();

    List<Post> list = new ArrayList<Post>();
    while (rs.next()) {
      Post post = new Post();
      post.setId(rs.getInt("id"));
      post.setTitle(rs.getString("title"));
      post.setContent(rs.getString("content"));
      post.setWriterId(rs.getInt("writer_id"));
      post.setTime(rs.getTimestamp("time"));
      post.setCount(rs.getInt("count"));
      post.setBoardType(rs.getInt("boardtype_id"));
      post.setDiff(rs.getLong("diff"));
      post.setTimeFormat(rs.getString("timeFormat"));
      post.setStaffId(rs.getString("staff_id"));
      post.setCommentCount(commentDao.getCommentCount(post.getId()));
      list.add(post);
    }

    rs.close();
    pstmt.close();
    conn.close();

    return list;
  }

  public List<Post> getPostByOption(int page, int boardType, String option, String word) throws NamingException, SQLException {
    CommentDao commentDao = new CommentDao();
    word = "'%" + word + "%'";
    
    String sql1 = "set @rownum:=0";
    String sql2 = "select * from ";
    String column = "";
    switch (option) {
    case "title":
      column = "title like " + word;
      break;
    case "content":
      column = "content like " + word;
      break;
    case "all":
      column = "title like " + word + " or content like " + word;
      break;
    }
    
    String sql3 = "(select @rownum:=@rownum + 1 as no, p.*, "
        + "round(time_to_sec(timediff(NOW(), time)) / 60) as diff, "
        + "date_format(time, '%m/%d %H:%i') as timeFormat, "
        + "s.staff_id from post p "
        + "left join staff s on p.writer_id = s.id "
        + "where boardtype_id = "
        + boardType
        + " and "
        + column
        + " order by time desc) q";

   
    String sql4 = " where no > ? " 
        + "limit 20";
    
    String sql = sql2 + sql3 + sql4;
    
    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql1);
    pstmt.executeUpdate();
    
    pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, (page - 1) * 20);
    rs = pstmt.executeQuery();
    
    List<Post> list = new ArrayList<Post>();
    while (rs.next()) {
      Post post = new Post();
      post.setId(rs.getInt("id"));
      post.setTitle(rs.getString("title"));
      post.setContent(rs.getString("content"));
      post.setWriterId(rs.getInt("writer_id"));
      post.setTime(rs.getTimestamp("time"));
      post.setCount(rs.getInt("count"));
      post.setBoardType(rs.getInt("boardtype_id"));
      post.setDiff(rs.getLong("diff"));
      post.setTimeFormat(rs.getString("timeFormat"));
      post.setStaffId(rs.getString("staff_id"));
      post.setCommentCount(commentDao.getCommentCount(post.getId()));
      list.add(post);
    }
    
    rs.close();
    pstmt.close();
    conn.close();
    
    return list;
  }
}
