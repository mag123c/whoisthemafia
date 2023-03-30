package com.solo.pj1.room.dto;

import java.util.Date;

public class RoomDTO {

	private int idx, people;
	private String rname, pw;
	private Date create_date;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getPeople() {
		return people;
	}
	public void setPeople(int people) {
		this.people = people;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	
	@Override
	public String toString() {
		return "RoomDTO [idx=" + idx + ", people=" + people + ", rname=" + rname + ", pw=" + pw + ", create_date="
				+ create_date + "]";
	}
		
}
