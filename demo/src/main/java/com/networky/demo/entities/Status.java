package com.networky.demo.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "status")
public class Status {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "type_status")
	private String typeStatus;
	
	@JsonIgnore
	@OneToMany(mappedBy = "status", cascade = CascadeType.ALL)
	private List<Friendship> friendship;
	
	@Column(name = "codice_status")
	private String codiceStatus;
	
//	@ManyToMany(targetEntity = User.class)
//	private List<User> userRelat;

	public Status() {
	}

	public Status(String typeStatus, List<Friendship> friendship, String codiceStatus) {
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
		return "Status [id=" + id + ", typeStatus=" + typeStatus + ", friendship=" + friendship + "]";
	}
}
