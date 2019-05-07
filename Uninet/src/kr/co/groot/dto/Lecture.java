package kr.co.groot.dto;

public class Lecture {
  private int id;
  private int prof_id;
  private String lecture_name;
  private int credit;
  private String time;
  private int class_id;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getProf_id() {
    return prof_id;
  }

  public void setProf_id(int prof_id) {
    this.prof_id = prof_id;
  }

  public String getLecture_name() {
    return lecture_name;
  }

  public void setLecture_name(String lecture_name) {
    this.lecture_name = lecture_name;
  }

  public int getCredit() {
    return credit;
  }

  public void setCredit(int credit) {
    this.credit = credit;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public int getClass_id() {
    return class_id;
  }

  public void setClass_id(int class_id) {
    this.class_id = class_id;
  }
}
