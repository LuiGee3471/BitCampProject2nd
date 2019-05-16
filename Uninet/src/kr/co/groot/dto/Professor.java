package kr.co.groot.dto;

public class Professor {
  private int id;
  private String profName;
  private int majorId;
  private String majorName;

  public String getMajorName() {
    return majorName;
  }

  public void setMajorName(String majorName) {
    this.majorName = majorName;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getProfName() {
    return profName;
  }

  public void setProfName(String profName) {
    this.profName = profName;
  }

  public int getMajorId() {
    return majorId;
  }

  public void setMajorId(int majorId) {
    this.majorId = majorId;
  }
}
