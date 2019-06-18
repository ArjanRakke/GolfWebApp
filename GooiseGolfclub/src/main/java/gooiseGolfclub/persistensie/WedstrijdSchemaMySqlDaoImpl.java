package gooiseGolfclub.persistensie;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class WedstrijdSchemaMySqlDaoImpl implements WedstrijdSchemaDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<WedstrijdSchema> findAll() {
		List<WedstrijdSchema> alleWedstrijden = null;

		try {
			Session session = HibernateUtil.getFactory().openSession();
			Transaction t = session.beginTransaction();

			alleWedstrijden = (List<WedstrijdSchema>) session.createQuery("FROM WedstrijdSchema").list();

			t.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return alleWedstrijden;
	}

	@Override
	public WedstrijdSchema save(WedstrijdSchema wedstrijd) {
		try { 
			Session session = HibernateUtil.getFactory().openSession();
		    Transaction t = session.beginTransaction();
		    
		    session.save(wedstrijd);
		    
		    t.commit();
		    session.close();
		    return wedstrijd;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean delete(WedstrijdSchema wedstrijd) {
		try {
			Session session = HibernateUtil.getFactory().openSession();
		    Transaction t = session.beginTransaction();
		    
		    session.delete(wedstrijd);
		    
		    t.commit();
		    session.close();
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
