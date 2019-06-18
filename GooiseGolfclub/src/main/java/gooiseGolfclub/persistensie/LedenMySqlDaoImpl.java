package gooiseGolfclub.persistensie;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class LedenMySqlDaoImpl implements LedenDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Leden> findAll() {
		List<Leden> alleLeden = null;
		
		try {
			Session session = HibernateUtil.getFactory().openSession();
		    Transaction t = session.beginTransaction();
		    
		    alleLeden = (List<Leden>) session.createQuery("FROM Leden").list();
		    
		    t.commit();
		    session.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	    
		return alleLeden;
	}

	@Override
	public Leden save(Leden lid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Leden update(Leden lid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Leden lid) {
		// TODO Auto-generated method stub
		return false;
	}

}
