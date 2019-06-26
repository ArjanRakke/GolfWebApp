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
	
	public BaanStatus updateBaanStatus(int bId, String gbBes, String qual, String zwg, String trlsGfk, String bem, String ond, String aan) {
		return bDao.update(bId, gbBes, qual, zwg, trlsGfk, bem, ond, aan);
	}
}
