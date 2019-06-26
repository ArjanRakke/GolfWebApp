package gooiseGolfclub.persistensie;

import java.util.List;

public interface BaanStatusDao {
	public List<BaanStatus> show();
	public BaanStatus update(int bId, String gbBes, String qual, String zwg, String trlsGfk, String bem, String ond, String aan);
}
