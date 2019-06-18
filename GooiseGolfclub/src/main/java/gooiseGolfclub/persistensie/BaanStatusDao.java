package gooiseGolfclub.persistensie;

import java.util.List;

public interface BaanStatusDao {
	public List<BaanStatus> show(BaanStatus status);
	public BaanStatus update(BaanStatus status);
}
