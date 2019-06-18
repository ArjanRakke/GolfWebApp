package gooiseGolfclub.persistensie;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="leden")
public class Leden {
	@Id
	@Column(name="NGF")
	private int NGF;
	
	@Column(name="Voornaam")
	private String voornaam;
	@Column(name="Achternaam")
	private String achternaam;
	@Column(name="Telefoonnummer")
	private int telefoonnummer;
	@Column(name="Emailadres")
	private String emailadres;
	@Column(name="Handicap")
	private double handicap;
	
	public Leden() {
		
	}
	
	public Leden(int ngf, String vn, String an, int tel, String email, double hcap) {
		this.NGF = ngf;
		this.voornaam = vn;
		this.achternaam = an;
		this.telefoonnummer = tel;
		this.emailadres = email;
		this.handicap = hcap;
	}

	public int getNGF() {
		return NGF;
	}

	public void setNGF(int nGF) {
		NGF = nGF;
	}

	public String getVoornaam() {
		return voornaam;
	}

	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

	public String getAchternaam() {
		return achternaam;
	}

	public void setAchternaam(String achternaam) {
		this.achternaam = achternaam;
	}

	public int getTelefoonnummer() {
		return telefoonnummer;
	}

	public void setTelefoonnummer(int telefoonnummer) {
		this.telefoonnummer = telefoonnummer;
	}

	public String getEmailadres() {
		return emailadres;
	}

	public void setEmailadres(String emailadres) {
		this.emailadres = emailadres;
	}

	public double getHandicap() {
		return handicap;
	}

	public void setHandicap(double handicap) {
		this.handicap = handicap;
	}
}
