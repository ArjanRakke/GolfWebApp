package gooiseGolfclub.persistensie;

import java.util.List;

public interface WedstrijdSchemaDao {
	public List<WedstrijdSchema> findAll();
	public WedstrijdSchema save(WedstrijdSchema wedstrijd);
	public boolean delete(WedstrijdSchema wedstrijd);
}
