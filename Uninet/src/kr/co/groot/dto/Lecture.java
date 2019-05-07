package kr.co.groot.dto;

public class Lecture {
  private int id;
  private String lectureName;
  private int credit;
  private String time;
  private String lectureType;
  private String profName;
  private String majorName;
  private int lectureTypeId;
  private int majorId;
  private int profId;

  public int getProfId() {
    return profId;
  }

  public void setProfId(int profId) {
    this.profId = profId;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getLectureName() {
    return lectureName;
  }

  public void setLectureName(String lectureName) {
    this.lectureName = lectureName;
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

  public String getLectureType() {
    return lectureType;
  }

  public void setLectureType(String lectureType) {
    this.lectureType = lectureType;
  }

  public String getProfName() {
    return profName;
  }

  public void setProfName(String profName) {
    this.profName = profName;
  }

  public String getMajorName() {
    return majorName;
  }

  public void setMajorName(String majorName) {
    this.majorName = majorName;
  }

  public int getLectureTypeId() {
    return lectureTypeId;
  }

  public void setLectureTypeId(int lectureTypeId) {
    this.lectureTypeId = lectureTypeId;
  }

  public int getMajorId() {
    return majorId;
  }

  public void setMajorId(int majorId) {
    this.majorId = majorId;
  }
}
