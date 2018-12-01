package org.social.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.social.dao.ModuleDAO;
import org.social.dao.TestRunDAO;

public class DatabaseConnector {

	/**
	 * Returns session object for a class
	 * @return a seesion object
	 */
	public Session getSession(Object obj) {
		
		Configuration con = new Configuration().configure().addAnnotatedClass(ModuleDAO.class).addAnnotatedClass(TestRunDAO.class);
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
		SessionFactory sf = con.buildSessionFactory(registry);
		Session session = sf.openSession();
		return session;
	}

}
