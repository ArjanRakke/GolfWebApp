package gooiseGolfclub.webservices;

import gooiseGolfclub.persistensie.LedenDao;
import gooiseGolfclub.persistensie.LedenMySqlDaoImpl;
import gooiseGolfclub.persistensie.Leden;

import java.util.List;

public class LedenService {
	private LedenDao lDao = new LedenMySqlDaoImpl();

	public List<Leden> getAllLeden() {
		return lDao.findAll();
	}
}
