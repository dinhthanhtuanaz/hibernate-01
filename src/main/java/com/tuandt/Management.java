package com.tuandt;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.tuandt.model.Address;
import com.tuandt.model.Fresher;
import com.tuandt.model.FresherDetail;

public class Management {

	public static void main(String[] args) {
		createFresher();
		ConnectionUtil.getSessionFactory().close();
	}

	private static void createFresher() {
		FresherDetail fresherDetail = new FresherDetail("Dinh Thanh Tuan", new Date());
		List<Address> addresses = new ArrayList<>();
		addresses.add(new Address("Gia Vien - Ninh Binh"));
		addresses.add(new Address("Bac Tu Liem - Ha Noi"));
		Fresher fresher = new Fresher("Tuandt1", fresherDetail, addresses);
		List<String> a = new ArrayList<>();
		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(fresherDetail);
			for (Address address : addresses) {
				session.save(address);
			}
			session.save(fresher);
			session.getTransaction().commit();
		} catch(Exception e) {
			System.out.println(e.toString());
		}
	}
}
