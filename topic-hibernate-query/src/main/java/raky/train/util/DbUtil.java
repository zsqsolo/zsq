package raky.train.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DbUtil {
	
	private static final SessionFactory sessionFactory;
	
	static {		
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}
	
	private DbUtil() {}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static Session getSession() {
		return sessionFactory.openSession();
	}

}
