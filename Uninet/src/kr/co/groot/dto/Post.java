package kr.co.groot.dto;

import java.sql.Timestamp;

public class Post {
  private int id;
  private String title;
  private String content;
  private int writerId;
  private Timestamp time;
  private int count;
  private int boardType;
  private String staffId;
  private long diff; // 현재와 글 올린 시간 차이
  private String timeFormat; // 올린 시간 문자열 형태

  public String getStaffId() {
    return staffId;
  }

  public void setStaffId(String staffId) {
    this.staffId = staffId;
  }

  public String getTimeFormat() {
    return timeFormat;
  }

  public void setTimeFormat(String timeFormat) {
    this.timeFormat = timeFormat;
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

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public int getWriterId() {
    return writerId;
  }

  public void setWriterId(int writerId) {
    this.writerId = writerId;
  }

  public Timestamp getTime() {
    return time;
  }

  public void setTime(Timestamp time) {
    this.time = time;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public int getBoardType() {
    return boardType;
  }

  public void setBoardType(int boardType) {
    this.boardType = boardType;
  }

}
