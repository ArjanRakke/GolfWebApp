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
	public BaanStatus update(BaanStatus status) {
		try {
			SessionFactory sessFact = HibernateUtil.getFactory();
			Session session = sessFact.openSession();
		    Transaction t = session.beginTransaction();

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
