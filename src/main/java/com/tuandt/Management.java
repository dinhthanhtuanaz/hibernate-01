package com.tuandt;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.tuandt.model.Fresher;
import com.tuandt.model.FresherDetail;

public class Management {

	public static void main(String[] args) {
		createFresher();
		ConnectionUtil.getSessionFactory().close();
	}

	private static void createFresher() {
		FresherDetail fresherDetail = new FresherDetail("Dinh Thanh Tuan", new Date());
		Fresher fresher = new Fresher("Tuandt1", fresherDetail);
		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(fresherDetail);
			session.save(fresher);
			session.getTransaction().commit();
		} catch(Exception e) {
			System.out.println(e.toString());
		}
	}
}
