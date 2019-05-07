package kr.co.groot.dto;

import java.sql.Timestamp;

public class Comment {
  private int id;
  private String title;
  private int writerId;
  private String content;
  private Timestamp date;
  private int count;
  private int refer;
  private int referComment;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getWriterId() {
    return writerId;
  }

  public void setWriterId(int writerId) {
    this.writerId = writerId;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Timestamp getDate() {
    return date;
  }

  public void setDate(Timestamp date) {
    this.date = date;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public int getRefer() {
    return refer;
  }

  public void setRefer(int refer) {
    this.refer = refer;
  }

  public int getReferComment() {
    return referComment;
  }

  public void setReferComment(int referComment) {
    this.referComment = referComment;
  }

}
