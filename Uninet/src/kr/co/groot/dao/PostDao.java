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

import kr.co.groot.dto.Post;

public class PostDao {
  private Connection conn;
  private PreparedStatement pstmt;
  private ResultSet rs;
  private DataSource ds;

  public PostDao() {
    Context context;
    try {
      context = new InitialContext();
      ds = (DataSource) context.lookup("java:comp/env/jdbc/mysql");
    } catch (NamingException e) {
      System.out.println("PostDao: " + e.getMessage());
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
  public List<Post> selectAll() {
    List<Post> list = new ArrayList<>();
    String sql = "select p.title, p.content, p.writer_id, p.time, p.count, p.boardtype_id, p.id, s.staff_id from post p join staff s on p.writer_id = s.id";

    try {
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
    } catch (SQLException e) {
      System.out.println("selectAll: " + e.getMessage());
    } finally {
      try {
        rs.close();
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("selectAll: " + e.getMessage());
      }
    }

    return list;
  }

  public List<Post> selectPostByBoardType(int boardType) {
    List<Post> list = new ArrayList<>();
    String sql = "select post.*, round(time_to_sec(timediff(NOW(), time)) / 60) as diff, "
        + "date_format(time, '%m/%d %H:%i') as timeFormat, staff.staff_id "
        + "from post " + "left join staff " + "on post.writer_id = staff.id "
        + "where boardtype_id = ? order by time desc limit 20";

    try {
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
    } catch (SQLException e) {
      System.out.println("selectPostByBoardType: " + e.getMessage());
    } finally {
      try {
        rs.close();
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("selectPostByBoardType: " + e.getMessage());
      }
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
  public int insertPost(Post post) {
    int row = 0;
    String sql = "insert into post (title, content, writer_id, time, count, boardtype_id, file_name)"
        + " values (?, ?, ?, NOW(), 0, ?, ?)";

    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, post.getTitle());
      pstmt.setString(2, post.getContent());
      pstmt.setInt(3, post.getWriterId());
      pstmt.setInt(4, post.getBoardType());
      pstmt.setString(5, post.getFileName());
      row = pstmt.executeUpdate();
    } catch (SQLException e) {
      System.out.println("insertPost: " + e.getMessage());
    } finally {
      try {
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("insertPost: " + e.getMessage());
      }
    }

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
  public int deletePost(int id) {
    int row = 0;
    String sql = "delete from post where id = ?";

    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, id);

      row = pstmt.executeUpdate();
    } catch (SQLException e) {
      System.out.println("deletePost: " + e.getMessage());
    } finally {
      try {
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("deletePost: " + e.getMessage());
      }
    }

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
  public int updatePost(Post post) {
    int row = 0;
    String sql = "update post set id = ?, title = ?, content = ?, writer_id = ? time = ?, count = ?, boardtype_id = ?";

    try {
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
    } catch (SQLException e) {
      System.out.println("updatePost: " + e.getMessage());
    } finally {
      try {
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("updatePost: " + e.getMessage());
      }
    }

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
  public List<Post> selectByTitle(String title, int boardType) {
    List<Post> list = new ArrayList<>();
    String sql = "select post.*, round(time_to_sec(timediff(NOW(), time)) / 60) as diff, "
        + "date_format(time, '%m/%d %H:%i') as timeFormat, staff.staff_id "
        + "from post " + "left join staff " + "on post.writer_id = staff.id "
        + "where title like ? "
        + "and boardtype_id = ? order by time desc limit 20";

    try {
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
    } catch (SQLException e) {
      System.out.println("selectByTitle: " + e.getMessage());
    } finally {
      try {
        rs.close();
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("selectByTitle: " + e.getMessage());
      }
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
  public List<Post> selectByContent(String content, int boardType) {
    List<Post> list = new ArrayList<>();
    String sql = "select post.*, round(time_to_sec(timediff(NOW(), time)) / 60) as diff, "
        + "date_format(time, '%m/%d %H:%i') as timeFormat, staff.staff_id "
        + "from post " + "left join staff " + "on post.writer_id = staff.id "
        + "where content like ? "
        + "and boardtype_id = ? order by time desc limit 20";

    try {
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
    } catch (SQLException e) {
      System.out.println("selectByContent: " + e.getMessage());
    } finally {
      try {
        rs.close();
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("selectByContent: " + e.getMessage());
      }
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
  public List<Post> selectByAll(String word, int boardType) {
    List<Post> list = new ArrayList<>();
    String sql = "select post.*, round(time_to_sec(timediff(NOW(), time)) / 60) as diff, "
        + "date_format(time, '%m/%d %H:%i') as timeFormat, staff.staff_id "
        + "from post " + "left join staff " + "on post.writer_id = staff.id "
        + "where title like ? or content like ? "
        + "and boardtype_id = ? order by time desc limit 20";

    try {
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
    } catch (SQLException e) {
      System.out.println("selectByAll: " + e.getMessage());
    } finally {
      try {
        rs.close();
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("selectByAll: " + e.getMessage());
      }
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
  public List<Post> selectByWriter(int writerId) {
    List<Post> list = new ArrayList<>();
    String sql = "select post.*,"
        + "date_format(time, '%m/%d %H:%i') as timeFormat from post"
        + " where writer_id = ?" + " order by time desc";

    try {
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
    } catch (SQLException e) {
      System.out.println("selectByWriter: " + e.getMessage());
    } finally {
      try {
        rs.close();
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("selectByWriter: " + e.getMessage());
      }
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
  public List<Post> selectByCount(int boardType) {
    List<Post> list = new ArrayList<>();
    String sql = "select post.*, round(time_to_sec(timediff(NOW(), time)) / 60) as diff, "
        + "date_format(time, '%m/%d %H:%i') as timeFormat, staff.staff_id "
        + "from post " + "left join staff " + "on post.writer_id = staff.id "
        + "where boardtype_id = ? " + "order by count desc limit 20";

    try {
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
    } catch (SQLException e) {
      System.out.println("selectByCount: " + e.getMessage());
    } finally {
      try {
        rs.close();
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("selectByCount: " + e.getMessage());
      }
    }

    return list;
  }

  public Post getPost(int id) {
    Post post = new Post();
    String sql = "select post.*, round(time_to_sec(timediff(NOW(), time)) / 60) as diff, "
        + "date_format(time, '%m/%d %H:%i') as timeFormat, staff.staff_id "
        + "from post " + "left join staff " + "on post.writer_id = staff.id "
        + "where post.id = ?";

    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, id);
      rs = pstmt.executeQuery();

      if (rs.next()) {
        post.setTitle(rs.getString("title"));
        post.setContent(rs.getString("content"));
        post.setWriterId(rs.getInt("writer_id"));
        post.setTime(rs.getTimestamp("time"));
        post.setCount(rs.getInt("count"));
        post.setBoardType(rs.getInt("boardtype_id"));
        post.setId(rs.getInt("id"));
        post.setDiff(rs.getLong("diff"));
        post.setTimeFormat(rs.getString("timeFormat"));
        post.setFileName(rs.getString("file_name"));
        post.setStaffId(rs.getString("staff_id"));
      }
    } catch (SQLException e) {
      System.out.println("getPost: " + e.getMessage());
    } finally {
      try {
        rs.close();
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("getPost: " + e.getMessage());
      }
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
  public List<Post> selectRecentNotice() {
    List<Post> list = new ArrayList<>();
    String sql = "select *, round(time_to_sec(timediff(NOW(), time)) / 60) as diff, "
        + "date_format(time, '%m/%d %H:%i') as timeFormat "
        + "from post where boardtype_id = 1 order by time desc limit 4";

    try {
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
    } catch (SQLException e) {
      System.out.println("selectRecentNotice: " + e.getMessage());
    } finally {
      try {
        rs.close();
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("selectRecentNotice: " + e.getMessage());
      }
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
  public List<Post> selectRecentPost() {
    List<Post> list = new ArrayList<>();
    String sql = "select *, round(time_to_sec(timediff(NOW(), time)) / 60) as diff, "
        + "date_format(time, '%m/%d %H:%i') as timeFormat "
        + "from post where boardtype_id = 2 order by time desc limit 4";

    try {
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
    } catch (SQLException e) {
      System.out.println("selectRecentPost: " + e.getMessage());
    } finally {
      try {
        rs.close();
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("selectRecentPost: " + e.getMessage());
      }
    }

    return list;
  }

  /*
   * @method Name: selectByCountForMain
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
  public List<Post> selectByCountForMain() {
    List<Post> list = new ArrayList<>();
    String sql = "select *, round(time_to_sec(timediff(NOW(), time)) / 60) as diff,"
        + " date_format(time, '%m/%d %H:%i') as timeFormat "
        + "from post order by count desc limit 4";

    try {
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
    } catch (SQLException e) {
      System.out.println("selectByCountForMain: " + e.getMessage());
    } finally {
      try {
        rs.close();
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("selectByCountForMain: " + e.getMessage());
      }
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
  public void addCount(int id) {
    String sql = "Update post set count = count + 1 where id = ?";

    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, id);
      pstmt.executeUpdate();
    } catch (SQLException e) {
      System.out.println("addCount: " + e.getMessage());
    } finally {
      try {
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("addCount: " + e.getMessage());
      }
    }
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
  public int countHowManyPost(int boardType) {
    int count = 0;
    String sql = "select count(*) from post where boardtype_id = ?";

    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, boardType);
      rs = pstmt.executeQuery();

      rs.next();
      count = rs.getInt(1);
    } catch (SQLException e) {
      System.out.println("countHowManyPost: " + e.getMessage());
    } finally {
      try {
        rs.close();
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("countHowManyPost: " + e.getMessage());
      }
    }

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
      int boardType) {
    int count = 0;
    String sql1 = "select count(*) from post where ";
    String sql2 = "";
    String sql3 = "and boardtype_id = ?";
    word = "%" + word + "%";

    try {
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
      count = rs.getInt(1);
    } catch (SQLException e) {
      System.out.println("countHowManyPostWithOption: " + e.getMessage());
    } finally {
      try {
        rs.close();
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("countHowManyPostWithOption: " + e.getMessage());
      }
    }

    return count;
  }

  public List<Post> getPostByPage(int page, int boardType) {
    List<Post> list = new ArrayList<Post>();

    String sql1 = "set @rownum:=0";
    String sql2 = "select * " + "from (select " + "@rownum:=@rownum + 1 as no, "
        + "p.*, " + "round(time_to_sec(timediff(NOW(), time)) / 60) as diff, "
        + "date_format(time, '%m/%d %H:%i') as timeFormat, " + "s.staff_id "
        + "from post p " + "left join staff s " + "on p.writer_id = s.id "
        + "where boardtype_id = ? " + "order by time desc) q " + "where no > ? "
        + "limit 20";

    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql1);
      pstmt.executeUpdate();
      pstmt = conn.prepareStatement(sql2);
      pstmt.setInt(1, boardType);
      pstmt.setInt(2, (page - 1) * 20);

      rs = pstmt.executeQuery();
      CommentDao commentDao = new CommentDao();

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
        post.setFileName(rs.getString("file_name"));
        post.setCommentCount(commentDao.getCommentCount(post.getId()));
        list.add(post);
      }
    } catch (SQLException e) {
      System.out.println("getPostByPage: " + e.getMessage());
    } finally {
      try {
        rs.close();
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("getPostByPage: " + e.getMessage());
      }
    }

    return list;
  }

  public List<Post> getPostByOption(int page, int boardType, String option,
      String word) {
    List<Post> list = new ArrayList<Post>();
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
        + "s.staff_id from post p " + "left join staff s on p.writer_id = s.id "
        + "where boardtype_id = " + boardType + " and " + column
        + " order by time desc) q";

    String sql4 = " where no > ? " + "limit 20";

    String sql = sql2 + sql3 + sql4;

    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql1);
      pstmt.executeUpdate();

      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, (page - 1) * 20);
      rs = pstmt.executeQuery();

      CommentDao commentDao = new CommentDao();

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
    } catch (SQLException e) {
      System.out.println("getPostByOption: " + e.getMessage());
    } finally {
      try {
        rs.close();
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("getPostByOption: " + e.getMessage());
      }
    }

    return list;
  }

  public int countHowManyMyComment(int id) {
    int result = 0;
    String sql = "select count(*) " + "from " + "(select distinct title "
        + "from post p " + "left join comment c " + "on p.id = c.refer "
        + "where c.writer_id = ?) q";

    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, id);
      rs = pstmt.executeQuery();

      rs.next();

      result = rs.getInt(1);
    } catch (SQLException e) {
      System.out.println("countHowManyMyComment: " + e.getMessage());
    } finally {
      try {
        rs.close();
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("countHowManyMyComment: " + e.getMessage());
      }
    }

    return result;
  }

  public int countHowManyMyPost(int id) {
    int result = 0;
    String sql = "select count(*) as count " + " from post p"
        + " where writer_id = ?";

    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, id);
      rs = pstmt.executeQuery();

      rs.next();

      result = rs.getInt(1);
    } catch (SQLException e) {
      System.out.println("countHowManyMyPost: " + e.getMessage());
    } finally {
      try {
        rs.close();
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("countHowManyMyPost: " + e.getMessage());
      }
    }

    return result;
  }

  public int countHowManyMyCommentWithOption(String option, String word,
      int id) {
    int count = 0;
    String sql1 = "select count(*) from post p" + "LEFT JOIN comment c "
        + "on p.id = c.refer " + "where ";
    String sql2 = "";
    String sql3 = "and c.writer_id = ?";
    word = "%" + word + "%";

    try {
      conn = ds.getConnection();
      switch (option) {
      case "title":
        sql2 = "title LIKE ? ";
        pstmt = conn.prepareStatement(sql1 + sql2 + sql3);
        System.out.println(sql1 + sql2 + sql3);
        pstmt.setString(1, word);
        pstmt.setInt(2, id);
        break;
      case "content":
        sql2 = "content LIKE ? ";
        pstmt = conn.prepareStatement(sql1 + sql2 + sql3);
        System.out.println(sql1 + sql2 + sql3);
        pstmt.setString(1, word);
        pstmt.setInt(2, id);
        break;
      case "all":
        sql2 = "(title LIKE ? OR content LIKE ?) ";
        pstmt = conn.prepareStatement(sql1 + sql2 + sql3);
        System.out.println(sql1 + sql2 + sql3);
        pstmt.setString(1, word);
        pstmt.setString(2, word);
        pstmt.setInt(3, id);
        break;
      }

      rs = pstmt.executeQuery();

      rs.next();
      count = rs.getInt(1);
    } catch (SQLException e) {
      System.out.println("countHowManyMyCommentWithOption: " + e.getMessage());
    } finally {
      try {
        rs.close();
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out
            .println("countHowManyMyCommentWithOption: " + e.getMessage());
      }
    }

    return count;
  }

  public int countHowManyMyPostWithOption(String option, String word, int id) {
    int count = 0;
    String sql1 = "select count(*) from post " + "where ";
    String sql2 = "";
    String sql3 = "and writer_id = ?";
    word = "%" + word + "%";

    try {
      conn = ds.getConnection();
      switch (option) {
      case "title":
        sql2 = "title LIKE ? ";
        pstmt = conn.prepareStatement(sql1 + sql2 + sql3);
        System.out.println(sql1 + sql2 + sql3);
        pstmt.setString(1, word);
        pstmt.setInt(2, id);
        break;
      case "content":
        sql2 = "content LIKE ? ";
        pstmt = conn.prepareStatement(sql1 + sql2 + sql3);
        System.out.println(sql1 + sql2 + sql3);
        pstmt.setString(1, word);
        pstmt.setInt(2, id);
        break;
      case "all":
        sql2 = "(title LIKE ? OR content LIKE ?) ";
        pstmt = conn.prepareStatement(sql1 + sql2 + sql3);
        System.out.println(sql1 + sql2 + sql3);
        pstmt.setString(1, word);
        pstmt.setString(2, word);
        pstmt.setInt(3, id);
        break;
      }

      rs = pstmt.executeQuery();

      rs.next();
      count = rs.getInt(1);
    } catch (SQLException e) {
      System.out.println("countHowManyMyPostWithOption: " + e.getMessage());
    } finally {
      try {
        rs.close();
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("countHowManyMyPostWithOption: " + e.getMessage());
      }
    }

    return count;
  }

  public List<Post> getMyCommentByPage(int page, int id) {
    List<Post> list = new ArrayList<Post>();
    String sql1 = "set @rownum:=0";
    String sql2 = "select * " + "from "
        + "(select @rownum:=@rownum + 1 as no, q.* " + "from " + "(select "
        + "distinct p.id, s.staff_id, date_format(p.time, '%m/%d %H:%i') as timeFormat, "
        + "p.boardtype_id, p.title, p.content, p.writer_id, p.time, p.count "
        + "from post p " + "LEFT join comment c on p.id = c.refer "
        + "left join staff s on p.writer_id = s.id "
        + "where c.writer_id = ?) q) q2 " + "where no > ? " + "limit 20";

    try {
      CommentDao commentDao = new CommentDao();
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql1);
      pstmt.executeUpdate();
      pstmt = conn.prepareStatement(sql2);
      pstmt.setInt(1, id);
      pstmt.setInt(2, (page - 1) * 20);

      rs = pstmt.executeQuery();

      while (rs.next()) {
        Post post = new Post();
        post.setId(rs.getInt("id"));
        post.setTitle(rs.getString("title"));
        post.setContent(rs.getString("content"));
        post.setWriterId(rs.getInt("writer_id"));
        post.setTime(rs.getTimestamp("time"));
        post.setStaffId(rs.getString("staff_id"));
        post.setCount(rs.getInt("count"));
        post.setBoardType(rs.getInt("boardtype_id"));
        post.setTimeFormat(rs.getString("timeFormat"));
        post.setCommentCount(commentDao.getCommentCount(post.getId()));
        list.add(post);
      }
    } catch (SQLException e) {
      System.out.println("getMyCommentByPage: " + e.getMessage());
    } finally {
      try {
        rs.close();
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("getMyCommentByPage: " + e.getMessage());
      }
    }

    return list;
  }

  public List<Post> getMyCommentByOption(int page, int id, String option,
      String word) {
    List<Post> list = new ArrayList<Post>();
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

    String sql3 = "(select @rownum:=@rownum + 1 as no, q.* " + "from "
        + "(select "
        + "distinct p.id, s.staff_id, date_format(p.time, '%m/%d %H:%i') as timeFormat, "
        + "p.boardtype_id, p.title, p.content, p.writer_id, p.time, p.count "
        + "from post p " + "LEFT join comment c on p.id = c.refer "
        + "left join staff s on p.writer_id = s.id " + "where " + column
        + " and c.writer_id = ?) q) q2 ";

    String sql4 = " where no > ? " + "limit 20";

    String sql = sql2 + sql3 + sql4;

    try {
      CommentDao commentDao = new CommentDao();
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql1);
      pstmt.executeUpdate();

      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, id);
      pstmt.setInt(2, (page - 1) * 20);
      rs = pstmt.executeQuery();

      while (rs.next()) {
        Post post = new Post();
        post.setId(rs.getInt("id"));
        post.setTitle(rs.getString("title"));
        post.setContent(rs.getString("content"));
        post.setWriterId(rs.getInt("writer_id"));
        post.setTime(rs.getTimestamp("time"));
        post.setCount(rs.getInt("count"));
        post.setBoardType(rs.getInt("boardtype_id"));
        post.setTimeFormat(rs.getString("timeFormat"));
        post.setStaffId(rs.getString("staff_id"));
        post.setCommentCount(commentDao.getCommentCount(post.getId()));
        list.add(post);
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      try {
        rs.close();
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("getMyCommentByOption: " + e.getMessage());
      }
    }

    return list;
  }

  public List<Post> getMyPostByPage(int page, int id) {
    List<Post> list = new ArrayList<Post>();

    String sql1 = "set @rownum:=0";
    String sql2 = "select * from " + "(select @rownum:=@rownum + 1 as no, q.* "
        + "from " + "(select *, date_format(time, '%m/%d %H:%i') as timeFormat "
        + "from post " + "where writer_id = ?) q) q2 " + "where no > ? "
        + "limit 20";

    try {
      CommentDao commentDao = new CommentDao();
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql1);
      pstmt.executeUpdate();
      pstmt = conn.prepareStatement(sql2);
      pstmt.setInt(1, id);
      pstmt.setInt(2, (page - 1) * 20);

      rs = pstmt.executeQuery();

      while (rs.next()) {
        Post post = new Post();
        post.setId(rs.getInt("id"));
        post.setTitle(rs.getString("title"));
        post.setContent(rs.getString("content"));
        post.setWriterId(rs.getInt("writer_id"));
        post.setTime(rs.getTimestamp("time"));
        post.setCount(rs.getInt("count"));
        post.setBoardType(rs.getInt("boardtype_id"));
        post.setTimeFormat(rs.getString("timeFormat"));
        post.setCommentCount(commentDao.getCommentCount(post.getId()));
        list.add(post);
      }
    } catch (SQLException e) {
      System.out.println("getMyPostByPage: " + e.getMessage());
    } finally {
      try {
        rs.close();
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("getMyPostByPage: " + e.getMessage());
      }
    }

    return list;
  }

  public List<Post> getMyPostByOption(int page, int id, String option,
      String word) {
    List<Post> list = new ArrayList<Post>();
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

    String sql3 = "(select @rownum:=@rownum + 1 as no, q.* " + "from "
        + "(select *, date_format(time, '%m/%d %H:%i') as timeFormat "
        + "from post " + " where " + column + " and writer_id = ?) q) q2";

    String sql4 = " where no > ? " + "limit 20";

    String sql = sql2 + sql3 + sql4;

    try {
      CommentDao commentDao = new CommentDao();
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql1);
      pstmt.executeUpdate();

      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, id);
      pstmt.setInt(2, (page - 1) * 20);
      rs = pstmt.executeQuery();


      while (rs.next()) {
        Post post = new Post();
        post.setId(rs.getInt("id"));
        post.setTitle(rs.getString("title"));
        post.setContent(rs.getString("content"));
        post.setWriterId(rs.getInt("writer_id"));
        post.setTime(rs.getTimestamp("time"));
        post.setCount(rs.getInt("count"));
        post.setBoardType(rs.getInt("boardtype_id"));
        post.setTimeFormat(rs.getString("timeFormat"));
        post.setCommentCount(commentDao.getCommentCount(post.getId()));
        list.add(post);
      }
    } catch (SQLException e) {
      System.out.println("getMyPostByOption: " + e.getMessage());
    } finally {
      try {
        rs.close();
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("getMyPostByOption: " + e.getMessage());
      }
    }

    return list;
  }
}
