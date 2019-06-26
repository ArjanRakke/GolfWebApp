package gooiseGolfclub.persistensie;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class BaanStatusMySqlDaoImpl implements BaanStatusDao {

	@SuppressWarnings("unchecked")
	@Override
	//zorgt ervoor dat de baanstatus word opgehaald
	public List<BaanStatus> show() {
		List<BaanStatus> baanStatus = null;

		try {
			SessionFactory sessFact = HibernateUtil.getFactory();
			Session session = sessFact.openSession();
		    Transaction t = session.beginTransaction();

			baanStatus = (List<BaanStatus>) session.createQuery("FROM BaanStatus").list();

			t.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return baanStatus;
	}

	@Override
	//zorgt voor het wijzigen van de baanstatus
	public BaanStatus update(int bId, String gbBes, String qual, String zwg, String trlsGfk, String bem, String ond, String aan) {
		try {
			SessionFactory sessFact = HibernateUtil.getFactory();
			Session session = sessFact.openSession();
		    Transaction t = session.beginTransaction();

		    //BaanStatus bStatus;
		    
		    /*bStatus = (BaanStatus)session.load(BaanStatus.class, 1);
		    //bStatus.setBaan_id(bId);
		    bStatus.setGolfbaanBeschikbaar(gbBes);
		    bStatus.setQualifying(qual);
		    bStatus.setZomerOfWintergreens(zwg);
		    bStatus.setTrolleysEngolfkarren(trlsGfk);
		    bStatus.setBemest(bem);
		    bStatus.setOnderhoud(ond);
		    bStatus.setAankondiging(aan);*/
		    
		    BaanStatus status = new BaanStatus(bId, gbBes, qual, zwg, trlsGfk, bem, ond, aan);
		    
			session.update(status);

			t.commit();
			session.close();
			return status;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
