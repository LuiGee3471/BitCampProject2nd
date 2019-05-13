package kr.co.groot.dto;

import java.sql.Timestamp;

public class Staff {
  private int id;
  private String staffId;
  private String password;
  private String email;
  private String staffName;
  private String phoneNumber;
  private Timestamp birthday;
  private String image;
  private String isManager;
  private String isAdmin;
  private int deptId;
  private String deptName;
  private String selfIntroduce;

  public String getSelfIntroduce() {
    return selfIntroduce;
  }

  public void setSelfIntroduce(String selfIntroduce) {
    this.selfIntroduce = selfIntroduce;
  }

  public String getStaffName() {
    return staffName;
  }

  public void setStaffName(String staffName) {
    this.staffName = staffName;
  }

  public String getDeptName() {
    return deptName;
  }

  public void setDeptName(String deptName) {
    this.deptName = deptName;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getStaffId() {
    return staffId;
  }

  public void setStaffId(String staffId) {
    this.staffId = staffId;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public Timestamp getBirthday() {
    return birthday;
  }

  public void setBirthday(Timestamp birthday) {
    this.birthday = birthday;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getIsManager() {
    return isManager;
  }

  public void setIsManager(String isManager) {
    this.isManager = isManager;
  }

  public String getIsAdmin() {
    return isAdmin;
  }

  public void setIsAdmin(String isAdmin) {
    this.isAdmin = isAdmin;
  }

  public int getDeptId() {
    return deptId;
  }

  public void setDeptId(int deptId) {
    this.deptId = deptId;
  }
}
