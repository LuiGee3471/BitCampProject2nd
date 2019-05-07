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
