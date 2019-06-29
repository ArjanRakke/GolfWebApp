package gooiseGolfclub.webservices;

import java.sql.Date;
import java.util.List;

import gooiseGolfclub.persistensie.WedstrijdSchema;
import gooiseGolfclub.persistensie.WedstrijdSchemaDao;
import gooiseGolfclub.persistensie.WedstrijdSchemaMySqlDaoImpl;

public class WedstrijdSchemaService {
	WedstrijdSchemaDao wDao = new WedstrijdSchemaMySqlDaoImpl();
	
	public List<WedstrijdSchema> getAllWedstrijden() {
		return wDao.findAll();
	}
	
	public WedstrijdSchema save(int wId, String nm, String tp, int holes, Date bgDatum) {
		return wDao.save(wId, nm, tp, holes, bgDatum);
	}
	
	public WedstrijdSchema update(int wId, String nm, String tp, int holes, Date bgDatum) {
		return wDao.update(wId, nm, tp, holes, bgDatum);
	}
	
	public boolean delete(int wId) {
		return wDao.delete(wId);
	}
}
