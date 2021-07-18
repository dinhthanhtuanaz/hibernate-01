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
		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
//			Fresher fresher2 = new Fresher("New Fresher 3");
//			session.save(fresher2);
//			session.getTransaction().commit();
			
			//Get data from db
			//session.get sẽ sử dụng constructor ko tham số rồi gắn qua fresher1
//			Fresher fresher1 = (Fresher) session.get(Fresher.class, 11);
//			System.out.println(fresher1.getName());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		//dù có lỗi thì bắn lỗi vào catch & sessionFactory vẫn phải được close.
		ConnectionUtil.getSessionFactory().close();
	}
}
