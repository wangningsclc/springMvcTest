package com.gc.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.gc.dataset.User;

public class UserHiberDao {
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public boolean insert(User user){
		boolean result = false;
		Session session =  sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		try{
			session.save(user);
			result = true;
		}catch(Exception e){
			trans.rollback();
		}finally{
			trans.commit();
		}
		return result;
	}
}
