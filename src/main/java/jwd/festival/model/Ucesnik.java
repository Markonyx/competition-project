package jwd.festival.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Ucesnik {
	
	@Id
	@GeneratedValue
	@Column
	private Long id;
	@Column(unique=true, nullable=false)
	private String naziv;
	@Column
	private String mesto;
	@Column(nullable=false)
	private String email;
	@Column
	private Integer odigrano;
	@Column
	private Integer brBodova;
	@ManyToOne(fetch=FetchType.EAGER)
	private Takmicenje takmicenje;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getMesto() {
		return mesto;
	}
	public void setMesto(String mesto) {
		this.mesto = mesto;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getOdigrano() {
		return odigrano;
	}
	public void setOdigrano(Integer odigrano) {
		this.odigrano = odigrano;
	}
	public Integer getBrBodova() {
		return brBodova;
	}
	public void setBrBodova(Integer brBodova) {
		this.brBodova = brBodova;
	}
	public Takmicenje getTakmicenje() {
		return takmicenje;
	}
	public void setTakmicenje(Takmicenje takmicenje) {
		this.takmicenje = takmicenje;
	}
	

}
