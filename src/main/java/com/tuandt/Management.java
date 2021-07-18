package com.tuandt;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.tuandt.model.Fresher;

public class Management {

	public static void main(String[] args) {
		Fresher fresher = new Fresher(12, "TuanDT 12");
		System.out.println(fresher.getName());
		Configuration configuration = new Configuration();
		configuration.configure();
		ServiceRegistry registry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties())
				.build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
//		session.save(fresher);
//		session.getTransaction().commit();
		
		//Get data from db
		//session.get sẽ sử dụng constructor ko tham số rồi gắn qua fresher1
		Fresher fresher1 = (Fresher) session.get(Fresher.class, 10);
		System.out.println(fresher1.getName());
		sessionFactory.close();
	}
}
