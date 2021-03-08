package com.networky.demo.dtos;

import java.util.ArrayList;
import java.util.List;

import com.networky.demo.entities.Friendship;

public class FriendshipDTO {
	
	
//	List<Friendship> pendingRequests;
	
//	List<Friendship> requests;
	private int id;
	
	private int idSender;
	
	private int idRecipient;
	
	private int status;
	

	public FriendshipDTO() {
	}

	public FriendshipDTO(int idSender, int idRecipient, int status) {
		this.idSender = idSender;
		this.idRecipient = idRecipient;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdSender() {
		return idSender;
	}

	public void setIdSender(int idSender) {
		this.idSender = idSender;
	}

	public int getIdRecipient() {
		return idRecipient;
	}

	public void setIdRecipient(int idRecipient) {
		this.idRecipient = idRecipient;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "FriendshipDTO [id=" + id + ", idSender=" + idSender + ", idRecipient=" + idRecipient + ", status="
				+ status + "]";
	}
	
}
