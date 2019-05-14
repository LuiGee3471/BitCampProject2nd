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

  public CommentDao() {
    Context context;
    try {
      context = new InitialContext();
      ds = (DataSource) context.lookup("java:comp/env/jdbc/mysql");
    } catch (NamingException e) {
      System.out.println("CommentDao: " + e.getMessage());
    }
  }

  public List<Comment> getCommentList(int id) {

    String sql = "select comment.*, round(time_to_sec(timediff(NOW(), time)) / 60) as diff, "
        + "date_format(time, '%m/%d %H:%i') as timeFormat, " + "staff.staff_id " + "from comment " + "left join staff "
        + "on comment.writer_id = staff.id " + "where refer = ? " + "order by refer_comment asc, time asc";

    List<Comment> list = new ArrayList<>();

    try {
      StaffDao staffDao = null;
      staffDao = new StaffDao();
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
    } catch (SQLException e) {
      System.out.println("getCommentList:" + e.getMessage());
    } finally {
      try {
        rs.close();
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println(e.getMessage());
      }
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

  public List<Comment> selectAll(int refer) {
    List<Comment> commentList = new ArrayList<Comment>();
    String sql = "select s.staff_id, c.content, c.time from post p LEFT join comment c on p.id = c.refer left join staff s on p.writer_id = s.id where refer =?";

    try {
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
    } catch (SQLException e) {
      System.out.println("selectAll:" + e.getMessage());
    } finally {
      try {
        rs.close();
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("selectAll:" + e.getMessage());
      }

    }
    return commentList;
  }

  public Comment selectCommentById(int id) {
    String sql = "select * from comment where id = ?";
    Comment comment = null;
    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, id);
      rs = pstmt.executeQuery();

      rs.next();

      comment = new Comment();
      comment.setId(rs.getInt("id"));
      comment.setWriterId(rs.getInt("writer_id"));
      comment.setTime(rs.getTimestamp("time"));
      comment.setRefer(rs.getInt("refer"));
      comment.setRecomment(rs.getString("recomment"));
      comment.setReferComment(rs.getInt("refer_comment"));
      comment.setRecommentCount(rs.getInt("recomment_count"));

    } catch (SQLException e) {
      System.out.println("selectCommentById:" + e.getMessage());
    } finally {
      try {
        rs.close();
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("selectCommentById:" + e.getMessage());
      }
    }
    return comment;
  }

  public Comment selectMostRecentComment() {
    String sql = "select * " + "from comment " + "where id = " + "(select max(id) " + "from comment)";
    Comment comment = null;
    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();

      rs.next();

      comment = new Comment();
      comment.setId(rs.getInt("id"));
      comment.setWriterId(rs.getInt("writer_id"));
      comment.setTime(rs.getTimestamp("time"));
      comment.setRefer(rs.getInt("refer"));
      comment.setRecomment(rs.getString("recomment"));
      comment.setReferComment(rs.getInt("refer_comment"));
      comment.setRecommentCount(rs.getInt("recomment_count"));
    } catch (SQLException e) {
      System.out.println("selectMostRecentComment:" + e.getMessage());
    } finally {
      try {
        rs.close();
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("selectMostRecentComment:" + e.getMessage());
      }

    }
    return comment;
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
  public int insertComment(Comment comment) {
    int row = 0;
    String sql = "insert into comment(content, writer_id, time, refer) values (?,?,NOW(),?)";
    String sql2 = "update comment " + "set refer_comment = (select max(id) from (select * from comment) q) "
        + "where id = (select max(id) from (select * from comment) q)";

    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);

      pstmt.setString(1, comment.getContent());
      pstmt.setInt(2, comment.getWriterId());
      pstmt.setInt(3, comment.getRefer());

      row = pstmt.executeUpdate();

      pstmt = conn.prepareStatement(sql2);
      pstmt.executeUpdate();
    } catch (SQLException e) {
      System.out.println("insertComment:" + e.getMessage());
    } finally {
      try {
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("insertComment:" + e.getMessage());
      }
    }
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
  public int updateComment(Comment comment) {
    int row = 0;
    String sql = "update comment set content= ? where writer_id = ?";
    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, comment.getContent());
      pstmt.setInt(2, comment.getWriterId());
      row = pstmt.executeUpdate();
    } catch (SQLException e) {
      System.out.println("updateComment:" + e.getMessage());
    } finally {
      try {
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("updateComment:" + e.getMessage());
      }
    }
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
  // 
  public int deleteComment(int id) {
    int row = 0;
    String sql = "update comment set content='삭제된 댓글입니다.' where id= ?";
    
    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, id);
      row = pstmt.executeUpdate();
    } catch (SQLException e) {
      System.out.println("deleteComment:" + e.getMessage());
    } finally {
      try {
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("deleteComment:" + e.getMessage());
      }
    }
    return row;
  }

  public int countRecomment(int id) {
    int result = 0;
    int row = 0;
    String sql = "select count(*) as count from comment where refer_comment = ? and content!='삭제된 댓글입니다.'";
    String sql2 = "update comment set recomment_count = ? where id = ?";
    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, id);
      rs = pstmt.executeQuery();

      while (rs.next()) {
        result = rs.getInt("count");
      }
      pstmt = conn.prepareStatement(sql2);
      pstmt.setInt(1, result);
      pstmt.setInt(2, id);
      row = pstmt.executeUpdate();
    } catch (SQLException e) {
      System.out.println("countRecomment:" + e.getMessage());
    } finally {
      try {
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("countRecomment:" + e.getMessage());
      }
    }
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
  public List<Post> selectByWriter(int writerId) {
    List<Post> postList = new ArrayList<Post>();
    String sql = "select distinct p.id,date_format(p.time, '%m/%d %H:%i') as timeFormat,p.boardtype_id, p.title, p.content, p.writer_id, p.time, p.count from post p"
        + " LEFT join comment c" + " on p.id = c.refer" + " where c.writer_id = ?";
    CommentDao dao = null;

    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, writerId);
      rs = pstmt.executeQuery();
      dao = new CommentDao();
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

    return postList;
  }

  public int getCommentCount(int id) {
    String sql = "select count(*) as count from comment where refer = ? and content!='삭제된 댓글입니다.'";
    int row = 0;
    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, id);

      rs = pstmt.executeQuery();
      rs.next();

      row = rs.getInt(1);
    } catch (SQLException e) {
      System.out.println("getCommentCount:" + e.getMessage());
    } finally {
      try {
        rs.close();
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("getCommentCount:" + e.getMessage());
      }

    }
    return row;
  }

  public int insertRecomment(Comment comment) {
    int row = 0;
    String sql = "insert into comment(content, writer_id, time, refer,recomment, refer_comment) values (?,?,NOW(),?,'Y',?)";
    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, comment.getContent());
      pstmt.setInt(2, comment.getWriterId());
      pstmt.setInt(3, comment.getRefer());
      pstmt.setInt(4, comment.getReferComment());

      row = pstmt.executeUpdate();
    } catch (SQLException e) {
      System.out.println("insertRecomment:" + e.getMessage());
    } finally {
      try {
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("insertRecomment:" + e.getMessage());
      }
    }
    return row;
  }
}