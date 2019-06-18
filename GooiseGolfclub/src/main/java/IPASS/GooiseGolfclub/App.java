package IPASS.GooiseGolfclub;

import java.sql.SQLException;
import java.text.ParseException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import gooiseGolfclub.persistensie.BaanStatus;
import gooiseGolfclub.persistensie.BaanStatusDao;
import gooiseGolfclub.persistensie.BaanStatusMySqlDaoImpl;
import gooiseGolfclub.persistensie.HibernateUtil;
import gooiseGolfclub.persistensie.Leden;
import gooiseGolfclub.persistensie.LedenDao;
import gooiseGolfclub.persistensie.LedenMySqlDaoImpl;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException, ParseException {
    	/*Session session = HibernateUtil.getFactory().openSession();
        Transaction t = session.beginTransaction();
    	
        BaanStatus status;
        Leden lid;
        
        status = (BaanStatus) session.load(BaanStatus.class, 1);
        if (session != null) {
        	System.out.println("Baanstatus: " + status.toString());
        }
        
        lid = (Leden) session.load(Leden.class, 990399);
        if (session != null) {
        	System.out.println("Lid: " + lid.toString());
        }
        
        t.commit();
        session.close();*/
    	
    	LedenDao lDao = new LedenMySqlDaoImpl();
    	System.out.println(lDao.findAll());
    	BaanStatusDao bDao = new BaanStatusMySqlDaoImpl();
    	System.out.println(bDao.show());
    }
}
