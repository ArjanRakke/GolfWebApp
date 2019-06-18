package gooiseGolfclub.persistensie;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class BaanStatusMySqlDaoImpl implements BaanStatusDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<BaanStatus> show() {
		List<BaanStatus> baanStatus = null;

		try {
			Session session = HibernateUtil.getFactory().openSession();
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
	public BaanStatus update(BaanStatus status) {
		try {
			Session session = HibernateUtil.getFactory().openSession();
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
