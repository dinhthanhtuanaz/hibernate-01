package com.tuandt.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class Fresher {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "name", unique = true)
	private String name;
	
	@OneToOne
	private FresherDetail fresherDetail;
	
	@OneToMany
	private List<Address> addresses = new ArrayList<>();
	
	public Fresher() {
	}
	
	public Fresher(String name, FresherDetail fresherDetail) {
		super();
		this.name = name;
		this.fresherDetail = fresherDetail;
	}
	
	public Fresher(String name, FresherDetail fresherDetail, List<Address> addresses) {
		super();
		this.name = name;
		this.fresherDetail = fresherDetail;
		this.addresses = addresses;
	}

	public Fresher(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FresherDetail getFresherDetail() {
		return fresherDetail;
	}

	public void setFresherDetail(FresherDetail fresherDetail) {
		this.fresherDetail = fresherDetail;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
}
