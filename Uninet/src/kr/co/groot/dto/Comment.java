package kr.co.groot.dto;

import java.sql.Timestamp;

public class Comment {
  private int id;
  private int writerId;
  private String content;
  private Timestamp time;
  private int refer;
  private int referComment;
  private String recomment;
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
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
  public Timestamp getTime() {
    return time;
  }
  public void setTime(Timestamp time) {
    this.time = time;
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
  public String getRecomment() {
    return recomment;
  }
  public void setRecomment(String recomment) {
    this.recomment = recomment;
  }

  
}
