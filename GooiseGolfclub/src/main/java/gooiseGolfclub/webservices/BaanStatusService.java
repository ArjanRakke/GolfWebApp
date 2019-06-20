package gooiseGolfclub.webservices;

import java.util.List;

import gooiseGolfclub.persistensie.BaanStatus;
import gooiseGolfclub.persistensie.BaanStatusDao;
import gooiseGolfclub.persistensie.BaanStatusMySqlDaoImpl;

public class BaanStatusService {
	private BaanStatusDao bDao = new BaanStatusMySqlDaoImpl();
	
	public List<BaanStatus> getBaanStatus() {
		return bDao.show();
	}
}
