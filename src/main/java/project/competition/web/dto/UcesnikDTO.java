package project.competition.web.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

public class UcesnikDTO {
	
	private Long id;
	@Length(min=1, max=40)
	private String naziv;
	private String mesto;
	@Email
	private String email;
	private Integer odigrano;
	private Integer brBodova;
	private Long takmicenjeId;
	private String takmicenjeNaziv;
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
	public Long getTakmicenjeId() {
		return takmicenjeId;
	}
	public void setTakmicenjeId(Long takmicenjeId) {
		this.takmicenjeId = takmicenjeId;
	}
	public String getTakmicenjeNaziv() {
		return takmicenjeNaziv;
	}
	public void setTakmicenjeNaziv(String takmicenjeNaziv) {
		this.takmicenjeNaziv = takmicenjeNaziv;
	}
	
	

}
