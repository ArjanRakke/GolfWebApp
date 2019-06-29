package gooiseGolfclub.persistensie;

import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
	public WedstrijdSchema save(int wId, String nm, String tp, int holes, Date bgDatum) {
		try { 
			Session session = HibernateUtil.getFactory().openSession();
		    Transaction t = session.beginTransaction();
		    
		    WedstrijdSchema wedstrijd = new WedstrijdSchema(wId, nm, tp, holes, bgDatum);
		    
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
	public WedstrijdSchema update(int wId, String nm, String tp, int holes, Date bgDatum) {
		try {
			SessionFactory sessFact = HibernateUtil.getFactory();
			Session session = sessFact.openSession();
		    Transaction t = session.beginTransaction();
		    
		    WedstrijdSchema wedstrijd = new WedstrijdSchema(wId, nm, tp, holes, bgDatum);
		    
		    session.update(wedstrijd);
		    
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
	public boolean delete(int wedstrijd_id) {
		try {
			Session session = HibernateUtil.getFactory().openSession();
		    Transaction t = session.beginTransaction();
		    
		    WedstrijdSchema wedstrijd = new WedstrijdSchema();
		    wedstrijd.setWedstrijd_id(wedstrijd_id);
		    
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
