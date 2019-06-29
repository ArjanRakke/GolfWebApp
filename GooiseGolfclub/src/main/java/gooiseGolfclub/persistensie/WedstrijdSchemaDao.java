package gooiseGolfclub.persistensie;

import java.sql.Date;
import java.util.List;

public interface WedstrijdSchemaDao {
	public List<WedstrijdSchema> findAll();
	public WedstrijdSchema save(int wId, String nm, String tp, int holes, Date bgDatum);
	public WedstrijdSchema update(int wId, String nm, String tp, int holes, Date bgDatum);
	public boolean delete(int wId);
}
