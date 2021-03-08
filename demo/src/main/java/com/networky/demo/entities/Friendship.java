package com.networky.demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "friendship")
public class Friendship {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
//	ManytoMany dirett. con User/Account
	
//	@Column(name = "id_user_sender")
	@JsonIgnore
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "id_user_sender")
	private User idSender;
	
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "id_user_recipient")
	private User idRecipient;
	
	@ManyToOne(targetEntity = Status.class)
	@JoinColumn(name = "status")
	private Status status;

	public Friendship() {
	}

	public Friendship(User idSender, User idRecipient, Status status) {
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

	public User getIdSender() {
		return idSender;
	}

	public void setIdSender(User idSender) {
		this.idSender = idSender;
	}

	public User getIdRecipient() {
		return idRecipient;
	}

	public void setIdRecipient(User idRecipient) {
		this.idRecipient = idRecipient;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Friendship [id=" + id + ", idSender=" + idSender + ", idRecipient=" + idRecipient + ", status=" + status
				+ "]";
	}

}
