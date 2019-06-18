package gooiseGolfclub.persistensie;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="baanstatus")
public class BaanStatus {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="baan_id")
	private int baan_id;
	
	@Column(name="golfbaanbeschikbaar")
	private String golfbaanBeschikbaar;
	@Column(name="Qualifying")
	private String qualifying;
	@Column(name="zomerofwintergreens")
	private String zomerOfWintergreens;
	@Column(name="bemest")
	private String bemest;
	@Column(name="onderhoud")
	private String onderhoud;
	@Column(name="aankondiging")
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
	
	public String toString() {
		return golfbaanBeschikbaar + " " + qualifying + " " + zomerOfWintergreens + " " + bemest + " " + onderhoud + " " + aankondiging;
	}
}
