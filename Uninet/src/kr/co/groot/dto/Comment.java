package kr.co.groot.dto;

import java.sql.Timestamp;

public class Comment {
  private int id;
  private int writerId;
  private String content;
  private Timestamp time;
  private String recomment;
  private int refer;
  private int referComment;
  private long diff;
  private String timeFormat; // 올린 시간 문자열 형태
  private Staff writer;
  private String deleteId = "(삭제)";

  public String getDeleteId() {
    return deleteId;
  }

  public void setDeleteId(String deleteId) {
    this.deleteId = deleteId;
  }

  public String getTimeFormat() {
    return timeFormat;
  }

  public void setTimeFormat(String timeFormat) {
    this.timeFormat = timeFormat;
  }

  public Staff getWriter() {
    return writer;
  }

  public void setWriter(Staff writer) {
    this.writer = writer;
  }

  public long getDiff() {
    return diff;
  }

  public void setDiff(long diff) {
    this.diff = diff;
  }

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

  public String getRecomment() {
    return recomment;
  }

  public void setRecomment(String recomment) {
    this.recomment = recomment;
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
