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
	
	public Leden save(int NGF, String vm, String am, int tel, String email, double h) {
		return lDao.save(NGF, vm, am, tel, email, h);
	}
	
	public Leden updateLid(int NGF, String vm, String am, int tel, String email, double h) {
		return lDao.update(NGF, vm, am, tel, email, h);
	}
	
	public boolean deleteLid(int NGF) {
		return lDao.delete(NGF);
	}


	
}
