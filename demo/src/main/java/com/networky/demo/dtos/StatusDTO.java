package com.networky.demo.dtos;

import java.util.List;

import com.networky.demo.entities.Friendship;

public class StatusDTO {
	
	private int id;
	
	private String typeStatus;
	
	private List<Friendship> friendship;
	
	private String codiceStatus;

	public StatusDTO() {
	}

	public StatusDTO(String typeStatus, List<Friendship> friendship, String codiceStatus) {
		this.typeStatus = typeStatus;
		this.friendship = friendship;
		this.codiceStatus = codiceStatus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTypeStatus() {
		return typeStatus;
	}

	public void setTypeStatus(String typeStatus) {
		this.typeStatus = typeStatus;
	}

	public List<Friendship> getFriendship() {
		return friendship;
	}

	public void setFriendship(List<Friendship> friendship) {
		this.friendship = friendship;
	}
	
	public String getCodiceStatus() {
		return codiceStatus;
	}

	public void setCodiceStatus(String codiceStatus) {
		this.codiceStatus = codiceStatus;
	}

	@Override
	public String toString() {
		return "StatusDTO [id=" + id + ", typeStatus=" + typeStatus + ", friendship=" + friendship + "]";
	}
	
}
