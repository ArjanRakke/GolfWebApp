package gooiseGolfclub.webservices;

import java.util.List;

import gooiseGolfclub.persistensie.WedstrijdSchema;
import gooiseGolfclub.persistensie.WedstrijdSchemaDao;
import gooiseGolfclub.persistensie.WedstrijdSchemaMySqlDaoImpl;

public class WedstrijdSchemaService {
	WedstrijdSchemaDao wDao = new WedstrijdSchemaMySqlDaoImpl();
	
	public List<WedstrijdSchema> getAllWedstrijden() {
		return wDao.findAll();
	}
}
