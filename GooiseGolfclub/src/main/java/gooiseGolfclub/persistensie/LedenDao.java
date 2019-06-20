package gooiseGolfclub.persistensie;

import java.util.List;

public interface LedenDao {
	public List<Leden> findAll();
	public Leden save(int NGF, String vm, String am, int tel, String email, double h);
	public Leden update(int NGF, String vm, String am, int tel, String email, double h);
	public boolean delete(int NGF);
}
