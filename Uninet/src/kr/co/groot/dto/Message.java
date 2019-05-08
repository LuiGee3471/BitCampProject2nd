package kr.co.groot.dto;

import java.sql.Timestamp;

public class Message {
  private int id;
  private String content;
  private int receiverId;
  private int senderId;
  private Timestamp time;
  private String staffname;
  private long diff;
  private String timeFormat;

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

  public String getStaffname() {
    return staffname;
  }

  public void setStaffname(String staffname) {
    this.staffname = staffname;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public int getReceiverId() {
    return receiverId;
  }

  public void setReceiverId(int receiverId) {
    this.receiverId = receiverId;
  }

  public int getSenderId() {
    return senderId;
  }

  public void setSenderId(int senderId) {
    this.senderId = senderId;
  }

  public Timestamp getTime() {
    return time;
  }

  public void setTime(Timestamp time) {
    this.time = time;
  }

}
