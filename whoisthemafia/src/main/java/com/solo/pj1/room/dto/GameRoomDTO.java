package com.solo.pj1.room.dto;

import java.util.Date;

public class GameRoomDTO {

	private int room_idx, user_idx;
	private String role_idx, dead_yn;
	private Date dead_time;
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
	public String getRole_idx() {
		return role_idx;
	}
	public void setRole_idx(String role_idx) {
		this.role_idx = role_idx;
	}
	public String getDead_yn() {
		return dead_yn;
	}
	public void setDead_yn(String dead_yn) {
		this.dead_yn = dead_yn;
	}
	public Date getDead_time() {
		return dead_time;
	}
	public void setDead_time(Date dead_time) {
		this.dead_time = dead_time;
	}
	@Override
	public String toString() {
		return "GameRoomDTO [room_idx=" + room_idx + ", user_idx=" + user_idx + ", role_idx=" + role_idx + ", dead_yn="
				+ dead_yn + ", dead_time=" + dead_time + "]";
	}	
}
