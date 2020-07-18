package com.manufactry.surya.AutoServiceProvider.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class NewUser {
	
	private long rollnumber;
	private String name;
	private String branch;
	private float average_percentage;
	private String address;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getRollnumber() {
		return rollnumber;
	}
	public void setRollnumber(long rollnumber) {
		this.rollnumber = rollnumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public float getAverage_percentage() {
		return average_percentage;
	}
	public void setAverage_percentage(float average_percentage) {
		this.average_percentage = average_percentage;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	

}
