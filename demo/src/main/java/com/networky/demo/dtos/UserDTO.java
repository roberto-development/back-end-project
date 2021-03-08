package com.networky.demo.dtos;

import java.util.List;

public class UserDTO {

	
	private int id;
	
	private String nome;
	
	private String newNome;
	
	private String cognome;
	
	private String newCognome;
	
	private String dataDiNascita;
	
	private String newDataDiNascita;
	
	private String country;
	
	private String newCountry;
	
	private List<ImageDTO> image;
	
	public UserDTO() {
	}

	public UserDTO(String nome, String cognome, String dataDiNascita, String country, List<ImageDTO> image) {
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.country = country;
		this.image = image;
	}

	public UserDTO(String nome, String newNome, String cognome, String newCognome, String dataDiNascita,
			String newDataDiNascita, String country, String newCountry, List<ImageDTO> image) {
		this.nome = nome;
		this.newNome = newNome;
		this.cognome = cognome;
		this.newCognome = newCognome;
		this.dataDiNascita = dataDiNascita;
		this.newDataDiNascita = newDataDiNascita;
		this.country = country;
		this.newCountry = newCountry;
		this.image = image;
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

	public String getNewNome() {
		return newNome;
	}

	public void setNewNome(String newNome) {
		this.newNome = newNome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNewCognome() {
		return newCognome;
	}

	public void setNewCognome(String newCognome) {
		this.newCognome = newCognome;
	}

	public String getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(String dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public String getNewDataDiNascita() {
		return newDataDiNascita;
	}

	public void setNewDataDiNascita(String newDataDiNascita) {
		this.newDataDiNascita = newDataDiNascita;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getNewCountry() {
		return newCountry;
	}

	public void setNewCountry(String newCountry) {
		this.newCountry = newCountry;
	}

	public List<ImageDTO> getImage() {
		return image;
	}

	public void setImage(List<ImageDTO> image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", nome=" + nome + ", newNome=" + newNome + ", cognome=" + cognome
				+ ", newCognome=" + newCognome + ", dataDiNascita=" + dataDiNascita + ", newDataDiNascita="
				+ newDataDiNascita + ", country=" + country + ", newCountry=" + newCountry + ", image=" + image + "]";
	}

}