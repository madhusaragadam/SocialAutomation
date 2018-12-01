package org.social.database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.core.MultivaluedMap;

import org.glassfish.jersey.internal.util.collection.MultivaluedStringMap;
import org.hibernate.HibernateException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 * This class is responsible for doing all the database operations.
 */
public class DatabaseOps {

	Session session;

	public DatabaseOps(Object obj) {
		DatabaseConnector connector = new DatabaseConnector();
		session = connector.getSession(obj);
	}

	/**
	 * Inserts the given object into database
	 * 
	 * @param data is the module related data which we have to insert into database
	 */
	public void insertData(Object data) {

		Transaction t = session.beginTransaction();
		session.save(data);
		t.commit();
	}

	/**
	 * This method is responsible for returning the data obtained from the databse
	 * 
	 * @param className  is the table from which we want to extract data
	 * @param primaryKey is the value of a the required row's primary key column
	 * @return it returns an object formed with the data obtained from database
	 */
	public Object fetchData(Class className, Object primaryKey) {

		Transaction t = session.beginTransaction();
		Object data = session.get(className, (Serializable) primaryKey);
		t.commit();
		return data;
	}
	
	/**
	 * This method is responsible for returning the data obtained from the databse
	 * 
	 * @param className  is the table from which we want to extract data
	 * @param primaryKey is the value of a the required row's primary key column
	 * @return it returns an object formed with the data obtained from database
	 */
	public List<Object> fetchDataBasedOnCriteria(Object obj, Map queryParams) {

		Transaction t = session.beginTransaction();
		Class className = obj.getClass();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Object> criteria = builder.createQuery();
        Root<Object> root = (Root<Object>) criteria.from(obj.getClass());
        
        Iterator<String> keys = queryParams.keySet().iterator();
        criteria = criteria.select(root);
        Predicate restrictions = builder.equal(root, 2);
        List<Predicate> predicates = new ArrayList<Predicate>();

        while(keys.hasNext()) {
        	String currentKey = keys.next();
        	predicates.add(builder.equal(root.get(currentKey), queryParams.get(currentKey)));
        }
        criteria.where(predicates.toArray(new Predicate[]{}));
        Query<Object> q=session.createQuery(criteria);
        List<Object> modules=q.getResultList();     
		return modules;
	}
	

	public List<Object> fetchAllData(Object obj) {

		Transaction tx = null;
		List<Object> list = null;
		try {

			tx = session.beginTransaction();
			list = session.createQuery("from "+obj.getClass().getName()).getResultList();
			tx.commit();

		} catch (HibernateException ex) {
			if (tx != null) {
				tx.rollback();
			}
			ex.printStackTrace(System.err);
		} finally {
		}
		return list;
	}
	
}
