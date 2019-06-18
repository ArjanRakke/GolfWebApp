package gooiseGolfclub.persistensie;

import java.util.List;

public interface LedenDao {
	public List<Leden> findAll();
	public Leden save(Leden lid);
	public Leden update(Leden lid);
	public boolean delete(Leden lid);
}
