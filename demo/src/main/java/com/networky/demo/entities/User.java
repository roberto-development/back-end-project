package com.networky.demo.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="users")
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="nome")
	private String nome;

	@Column(name="cognome")
	private String cognome;

	@Column(name="data_di_nascita")
	private String dataDiNascita;

	@Column(name="country")
	private String country;
	
	@JsonIgnore
	@OneToMany(mappedBy = "idUser", cascade = CascadeType.ALL)
	private List<Post> post;
	
	@JsonIgnore
	@OneToMany(mappedBy = "usersId", cascade = CascadeType.ALL)
	private List<Image> image;
	
	@JsonIgnore
	@OneToMany(mappedBy = "idSender", cascade = CascadeType.ALL)
	private List<Friendship> friendshipSender;
	
//	@OneToMany(mappedBy = "status")
//	private List<Status> friendshipStatus;

	@JsonIgnore
	@OneToMany(mappedBy = "idRecipient", cascade = CascadeType.ALL)
	private List<Friendship> friendshipRecipient;
	
	public User() { }
	
	public User(String nome, String cognome, String dataDiNascita, String country) {
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.country = country;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(String dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	
//	public List<Post> getPost() {
//		return post;
//	}
//
//	public void setPost(List<Post> post) {
//		this.post = post;
//	}
//
	public List<Image> getImage() {
		return image;
	}

	public void setImage(List<Image> image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", dataDiNascita=" + dataDiNascita
				+ ", country=" + country + ", post=" + post + ", image=" + image + ", friendshipSender="
				+ friendshipSender + ", friendshipRecipient=" + friendshipRecipient + "]";
	}
	
	
}
