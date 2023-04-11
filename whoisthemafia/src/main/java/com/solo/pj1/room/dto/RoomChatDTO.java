package com.solo.pj1.room.dto;

import java.util.Date;

public class RoomChatDTO {

	private int room_idx, user_idx;
	private String user_id, msg;
	private Date create_date;
	public int getRoom_idx() {
		return room_idx;
	}
	public void setRoom_idx(int room_idx) {
		this.room_idx = room_idx;
	}
	public int getUser_idx() {
		return user_idx;
	}
	public void setUser_idx(int user_idx) {
		this.user_idx = user_idx;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	@Override
	public String toString() {
		return "RoomChatDTO [room_idx=" + room_idx + ", user_idx=" + user_idx + ", user_id=" + user_id + ", msg=" + msg
				+ ", create_date=" + create_date + "]";
	}	
	
}
