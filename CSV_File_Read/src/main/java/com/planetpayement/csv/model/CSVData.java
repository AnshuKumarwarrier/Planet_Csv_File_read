package com.planetpayement.csv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="orderdata")
public class CSVData {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="ID")
	private Long id;
	
	@Column(name ="PHONE_NUMBER")
	private String phoneNumber;
	
	
	@Column(name ="EMAIL")
	private String email;
	
	
	@Column(name ="PARCEL_WEIGHT")
	private Double parcelWeight;
	
	
	@Column(name ="COUNTRY")
	private String country;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Double getParcelWeight() {
		return parcelWeight;
	}


	public void setParcelWeight(Double parcelWeight) {
		this.parcelWeight = parcelWeight;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public CSVData(Long id, String phoneNumber, String email, Double parcelWeight, String country) {
		super();
		this.id = id;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.parcelWeight = parcelWeight;
		this.country = country;
	}


	public CSVData() {
		super();
	}


	@Override
	public String toString() {
		return "CSVData [id=" + id + ", phoneNumber=" + phoneNumber + ", email=" + email + ", parcelWeight="
				+ parcelWeight + ", country=" + country + "]";
	}
	
	
	

	

}
