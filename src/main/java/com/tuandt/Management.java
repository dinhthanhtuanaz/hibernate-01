package com.tuandt;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.tuandt.model.Address;
import com.tuandt.model.Course;
import com.tuandt.model.Fresher;
import com.tuandt.model.FresherDetail;

public class Management {
	public static void main(String[] args) {
		//createFresher();
		createFresherAndCourse();
		readCourseWithHQL();
		ConnectionUtil.getSessionFactory().close();
	}

	private static void readCourseWithHQL() {
		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			//course này trùng với @Entity defined => Chứ ko cần trùng với @Table
			//chỗ này để course là ko đúng => Sẽ bị course is not mapped
			//phải để đúng là Course (Vì mapped với Entity mà)
			
			
			/* Condition Binding 
			String queryStr = "FROM Course WHERE id = ? AND name like ?";
			Query query = session.createQuery(queryStr);
			query.setInteger(0, 1);
			query.setString(1, "%ourse%");
			*/
			
			// Named Query Parameters
			String queryStr = "SELECT name FROM Course WHERE id = :id AND name like :name";
			Query query = session.createQuery(queryStr);
			query.setParameter("id", 1);
			query.setParameter("name", "%ourse%");
			List<Course> courses = (List<Course>)query.list();
			System.out.println(courses);
		} catch(Exception e) {
			System.out.println(e.toString());
		}
	}

	/*
	 * - Sinh ra 2 bảng join table đó là bảng course_fresher, fresher_course
	 * ===> Thừa bảng quá ko nhỉ?
	 * */
	private static void createFresherAndCourse() {
		Fresher fresher1 = new Fresher();
		Fresher fresher2 = new Fresher();
		Set<Fresher> freshers = new HashSet<>();
		freshers.add(fresher1);
		freshers.add(fresher2);
		fresher1.setName("Fresher 1");
		fresher2.setName("Fresher 2");
		
		Course course1 = new Course("Course 1");
		Course course2 = new Course("Course 2");
		Set<Course> courses = new HashSet<>();
		courses.add(course1);
		courses.add(course2);
		
		fresher1.setCourses(courses);
		fresher2.setCourses(courses);
		
		course1.setFreshers(freshers);
		course2.setFreshers(freshers);
		
		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(fresher1);
			session.save(fresher2);
			session.save(course1);
			session.save(course2);
			session.getTransaction().commit();
		} catch(Exception e) {
			System.out.println(e.toString());
		}
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
