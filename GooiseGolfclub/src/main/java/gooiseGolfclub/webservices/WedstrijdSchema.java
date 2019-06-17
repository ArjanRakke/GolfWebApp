package gooiseGolfclub.webservices;

import java.sql.Date;

public class WedstrijdSchema {
	private int wedstrijd_id;
	private String naam;
	private String type;
	private int holes;
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
