package com.spring.rest.curd.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="employee",schema="test")
public class Employee implements Serializable {
	
@Id
@Column(name="id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;

@Column(name="name")
private String name;

@Column(name="address")
private String address;

@Temporal(TemporalType.DATE)
@Column(name="dob")
private Date dob;

@Column(name="mobile_no")
private String mobile;


public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}

public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public Date getDob() {
	return dob;
}
public void setDob(Date dob) {
	this.dob = dob;
}
public String getMobile() {
	return mobile;
}
public void setMobile(String mobile) {
	this.mobile = mobile;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}

}
