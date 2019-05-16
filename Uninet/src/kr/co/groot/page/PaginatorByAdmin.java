package kr.co.groot.page;

import kr.co.groot.dao.StaffDao;

public class PaginatorByAdmin {
	private StaffDao staffDao;
	
	/*
   * @method Name: getStaffPageNumber
   * 
   * @date: 2019. 5. 11.
   * 
   * @author: 곽호원
   * 
   * @description: 회원 목록의 페이지 수를 구한다
   * 
   * @param spec: none
   *
   * @return: int
   */
  public int getStaffPageNumber() {
	  staffDao = new StaffDao();
	  
	  int staffes = staffDao.countHowManyStaff();  
	  int page = (staffes % 20 == 0) ? (staffes / 20) : (staffes / 20 + 1);
	  
	  return page;
	}
	
  /*
   * @method Name: getStaffPageNumberByOption
   * 
   * @date: 2019. 5. 11.
   * 
   * @author: 곽호원
   * 
   * @description: 검색했을 때 회원 목록의 페이지 수를 구한다
   * 
   * @param spec: none
   *
   * @return: int
   */
	public int getStaffPageNumberByOption(String option, String word) {
	  staffDao = new StaffDao();
	  
	  int staffes = staffDao.countHowManyStaffWithOption(option, word);
	  int page = (staffes % 20 == 0) ? (staffes / 20) : (staffes / 20 + 1);
    return page;
	  
	}
}
