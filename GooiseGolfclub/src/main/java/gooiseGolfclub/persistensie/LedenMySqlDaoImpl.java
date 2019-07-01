package gooiseGolfclub.persistensie;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class LedenMySqlDaoImpl implements LedenDao {

	@SuppressWarnings("unchecked")
	@Override
	// Haalt alle leden op van de database
	public List<Leden> findAll() {
		List<Leden> alleLeden = null;
		
		try {
			SessionFactory sessFact = HibernateUtil.getFactory();
			Session session = sessFact.openSession();
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
	// Maakt een nieuw lid in de database aan
	public Leden save(int NGF, String vm, String am, int tel, String email, double h) {
		try { 
			SessionFactory sessFact = HibernateUtil.getFactory();
			Session session = sessFact.openSession();
		    Transaction t = session.beginTransaction();
		    
		    Leden lid = new Leden(NGF, vm, am, tel, email, h);
		    
		    session.save(lid);
		    
		    t.commit();
		    session.close();
		    return lid;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	// Wijzigt een lid in de database
	public Leden update(int NGF, String vm, String am, int tel, String email, double h) {
		try {
			SessionFactory sessFact = HibernateUtil.getFactory();
			Session session = sessFact.openSession();
		    Transaction t = session.beginTransaction();
		    
		    Leden lid = new Leden(NGF, vm, am, tel, email, h);
		    
		    session.update(lid);
		    
		    t.commit();
		    session.close();
			return lid;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	// Verwijderd een lid uit de database
	public boolean delete(int NGF) {
		try {
			SessionFactory sessFact = HibernateUtil.getFactory();
			Session session = sessFact.openSession();
		    Transaction t = session.beginTransaction();
		    
		    Leden lid = new Leden();
		    lid.setNGF(NGF);
		    
		    Query query = session.createQuery("DELETE FROM ClubCommissie WHERE ngf = :NGF");
		    query.setParameter("NGF", lid);
		    query.executeUpdate();
		    
		    session.delete(lid);
		    
		    t.commit();
		    session.close();
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	// Haalt de rol op van een commissielid
	public String findRoleForUser(String username, String password) {
		String role = null;
		
		try {
			SessionFactory sessFact = HibernateUtil.getFactory();
			Session session = sessFact.openSession();
		    Transaction t = session.beginTransaction();
		    
			Query query = session.createQuery("SELECT rol FROM ClubCommissie WHERE Gebruikersnaam = :gbNaam AND Wachtwoord = :wachtW");
			query.setParameter("gbNaam", username);
			query.setParameter("wachtW", password);
			
			role = (String) query.getSingleResult();
			
			t.commit();
			session.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return role;
	}

}
