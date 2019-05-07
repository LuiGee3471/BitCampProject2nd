package kr.co.groot.dto;

import java.sql.Timestamp;

public class Message {
  private int id;
  private String content;
  private int receiver_id;
  private int sender_id;
  private Timestamp time;
  private String staffname;

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

  public int getReceiver_id() {
    return receiver_id;
  }

  public void setReceiver_id(int receiver_id) {
    this.receiver_id = receiver_id;
  }

  public int getSender_id() {
    return sender_id;
  }

  public void setSender_id(int sender_id) {
    this.sender_id = sender_id;
  }

  public Timestamp getTime() {
    return time;
  }

  public void setTime(Timestamp time) {
    this.time = time;
  }


  
}
