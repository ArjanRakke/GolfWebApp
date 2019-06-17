package gooiseGolfclub.webservices;

public class ClubCommissie {
	private int commissie_id;
	private Leden ngf;
	private String gebruikersnaam;
	private String wachtwoord;
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
