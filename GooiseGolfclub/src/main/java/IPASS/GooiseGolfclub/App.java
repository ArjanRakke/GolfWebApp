package IPASS.GooiseGolfclub;

import java.sql.SQLException;
import java.text.ParseException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import gooiseGolfclub.persistensie.BaanStatus;
import gooiseGolfclub.persistensie.HibernateUtil;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException, ParseException {
    	Session session = HibernateUtil.getFactory().openSession();
        Transaction t = session.beginTransaction();
    	
        BaanStatus status;
        
        status = (BaanStatus) session.load(BaanStatus.class, 1);
        if (session != null) {
        	System.out.println("Baanstatus: " + status.toString());
        }
        
        t.commit();
        session.close();
        System.out.println( "Hello World!" );
    }
}
