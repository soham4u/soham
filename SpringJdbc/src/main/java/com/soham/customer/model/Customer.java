package com.soham.customer.model;

 
//Drop Table customer;
//DROP TABLE IF EXISTS `customer` ;

//Delete FROM hm.customer where CUST_ID > 0;

//CREATE TABLE `customer` (
//		  `CUST_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
//		  `NAME` varchar(100) NOT NULL,
//		  `AGE` int(10) unsigned NOT NULL,
//		  PRIMARY KEY (`CUST_ID`)
//		) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;



public class Customer 
{
	int custId;
	String name;
	int age;
	
	public Customer( String name, int age) {
		//this.custId = custId;
		this.name = name;
		this.age = age;
	}
	public Customer(int custId, String name, int age) {
		this.custId = custId;
		this.name = name;
		this.age = age;
	}
	
	public int getCustId() {
		return custId;
	}
	public void setCustId(int custId) {
		this.custId = custId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Customer [age=" + age + ", custId=" + custId + ", name=" + name
				+ "]";
	}
	
	
}
