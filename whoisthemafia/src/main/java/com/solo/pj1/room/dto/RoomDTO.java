package com.solo.pj1.room.dto;

import java.util.Date;

public class RoomDTO {

	private int idx, people, user_idx;
	private String rname, pw, start_yn;
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
	public String getStart_yn() {
		return start_yn;
	}
	public void setStart_yn(String start_yn) {
		this.start_yn = start_yn;
	}
	public int getUser_idx() {
		return user_idx;
	}
	public void setUser_idx(int user_idx) {
		this.user_idx = user_idx;
	}
	@Override
	public String toString() {
		return "RoomDTO [idx=" + idx + ", people=" + people + ", user_idx=" + user_idx + ", rname=" + rname + ", pw="
				+ pw + ", start_yn=" + start_yn + ", create_date=" + create_date + "]";
	}
	
}
