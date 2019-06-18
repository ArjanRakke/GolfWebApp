package gooiseGolfclub.persistensie;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static SessionFactory factory;
	
	static {
		try {
	        factory = new Configuration().configure().buildSessionFactory();
	      } catch (Throwable ex) {
	        System.err.println("Failed to create sessionFactory object." + ex);
	        throw new ExceptionInInitializerError(ex);
	      }
	}
	
	public static SessionFactory getFactory() {
		return factory;
	}
}
