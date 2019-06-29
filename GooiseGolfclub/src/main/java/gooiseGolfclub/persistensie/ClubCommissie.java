package gooiseGolfclub.persistensie;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="clubcommissie")
public class ClubCommissie {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="commissie_id")
	private int commissie_id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="NGF")
	private Leden ngf;
	@Column(name="Gebruikersnaam")
	private String gebruikersnaam;
	@Column(name="Wachtwoord")
	private String wachtwoord;
	@Column(name="Rol")
	private String rol;
	
	public ClubCommissie() {
		
	}

	public int getCommissie_id() {
		return commissie_id;
	}

	public void setCommissie_id(int commissie_id) {
		this.commissie_id = commissie_id;
	}

	public Leden getNgf() {
		return ngf;
	}

	public void setNgf(Leden ngf) {
		this.ngf = ngf;
	}

	public String getGebruikersnaam() {
		return gebruikersnaam;
	}

	public void setGebruikersnaam(String gebruikersnaam) {
		this.gebruikersnaam = gebruikersnaam;
	}

	public String getWachtwoord() {
		return wachtwoord;
	}

	public void setWachtwoord(String wachtwoord) {
		this.wachtwoord = wachtwoord;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
}
