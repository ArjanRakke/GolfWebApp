package gooiseGolfclub.webservices;

public class BaanStatus {
	private String golfbaanBeschikbaar;
	private String qualifying;
	private String zomerOfWintergreens;
	private String bemest;
	private String onderhoud;
	private String aankondiging;
	
	public BaanStatus() {
		
	}
	
	public BaanStatus(String gbBes, String q, String zwg, String b, String o, String a) {
		this.golfbaanBeschikbaar = gbBes;
		this.qualifying = q;
		this.zomerOfWintergreens = zwg;
		this.bemest = b;
		this.onderhoud = o;
		this.aankondiging = a;
	}

	public String getGolfbaanBeschikbaar() {
		return golfbaanBeschikbaar;
	}

	public void setGolfbaanBeschikbaar(String golfbaanBeschikbaar) {
		this.golfbaanBeschikbaar = golfbaanBeschikbaar;
	}

	public String getQualifying() {
		return qualifying;
	}

	public void setQualifying(String qualifying) {
		this.qualifying = qualifying;
	}

	public String getZomerOfWintergreens() {
		return zomerOfWintergreens;
	}

	public void setZomerOfWintergreens(String zomerOfWintergreens) {
		this.zomerOfWintergreens = zomerOfWintergreens;
	}

	public String getBemest() {
		return bemest;
	}

	public void setBemest(String bemest) {
		this.bemest = bemest;
	}

	public String getOnderhoud() {
		return onderhoud;
	}

	public void setOnderhoud(String onderhoud) {
		this.onderhoud = onderhoud;
	}

	public String getAankondiging() {
		return aankondiging;
	}

	public void setAankondiging(String aankondiging) {
		this.aankondiging = aankondiging;
	}
}
