package gooiseGolfclub.persistensie;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="wedstrijdschema")
public class WedstrijdSchema {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="wedstrijd_id")
	private int wedstrijd_id;

	@Column(name="Naam")
	private String naam;
	@Column(name="Type")
	private String type;
	@Column(name="Holes")
	private int holes;
	@Column(name="Begindatum")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date begindatum;
	
	public WedstrijdSchema() {
		
	}
	
	public WedstrijdSchema(String nm, String tp, int h, Date bgDatum) {
		this.naam = nm;
		this.type = tp;
		this.holes = h;
		this.begindatum = bgDatum;
	}

	public int getWedstrijd_id() {
		return wedstrijd_id;
	}

	public void setWedstrijd_id(int wedstrijd_id) {
		this.wedstrijd_id = wedstrijd_id;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getHoles() {
		return holes;
	}

	public void setHoles(int holes) {
		this.holes = holes;
	}

	public Date getBegindatum() {
		return begindatum;
	}

	public void setBegindatum(Date begindatum) {
		this.begindatum = begindatum;
	}
}
