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
	@Column(name="trolleysengolfkarren")
	private String trolleysEnGolfkarren;
	@Column(name="bemest")
	private String bemest;
	@Column(name="onderhoud")
	private String onderhoud;
	@Column(name="aankondiging")
	private String aankondiging;
	
	public BaanStatus() {
		
	}
	
	public BaanStatus(int bId, String gbBes, String qual, String zwg, String trlsGfk, String bem, String ond, String aan) {
		this.baan_id = bId;
		this.golfbaanBeschikbaar = gbBes;
		this.qualifying = qual;
		this.zomerOfWintergreens = zwg;
		this.trolleysEnGolfkarren = trlsGfk;
		this.bemest = bem;
		this.onderhoud = ond;
		this.aankondiging = aan;
	}
	
	public int getBaan_id() {
		return baan_id;
	}
	
	public void setBaan_id(int baan_id) {
		this.baan_id = baan_id;
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
	
	public String getTrolleysEnGolfkarren() {
		return trolleysEnGolfkarren;
	}
	
	public void setTrolleysEngolfkarren(String trolleysEnGolfkarren) {
		this.trolleysEnGolfkarren = trolleysEnGolfkarren;
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
