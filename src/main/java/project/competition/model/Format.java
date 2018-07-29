package project.competition.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Format {
	
	@Id
	@GeneratedValue
	@Column
	private Long id;
	@Column
	private String naziv;
	@Column
	private Integer brUcesnika;
	@Column
	private Integer vrPobeda;
	@Column
	private Integer vrNereseno;
	@Column
	private Integer vrGubitak;
	@OneToMany(mappedBy="format", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Takmicenje> takmicenja = new ArrayList<>();
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
	public Integer getBrUcesnika() {
		return brUcesnika;
	}
	public void setBrUcesnika(Integer brUcesnika) {
		this.brUcesnika = brUcesnika;
	}
	public Integer getVrPobeda() {
		return vrPobeda;
	}
	public void setVrPobeda(Integer vrPobeda) {
		this.vrPobeda = vrPobeda;
	}
	public Integer getVrNereseno() {
		return vrNereseno;
	}
	public void setVrNereseno(Integer vrNereseno) {
		this.vrNereseno = vrNereseno;
	}
	public Integer getVrGubitak() {
		return vrGubitak;
	}
	public void setVrGubitak(Integer vrGubitak) {
		this.vrGubitak = vrGubitak;
	}
	public List<Takmicenje> getTakmicenja() {
		return takmicenja;
	}
	public void setTakmicenja(List<Takmicenje> takmicenja) {
		this.takmicenja = takmicenja;
	}
	
	public void addTakmicenje (Takmicenje takmicenje) {
		this.takmicenja.add(takmicenje);
		
		if (!this.equals(takmicenje.getFormat())) {
			takmicenje.setFormat(this);
		}
	}

}
