package com.soham.customer.dao;

import java.util.List;

import com.soham.customer.model.Customer;

public interface CustomerDAO 
{
	public void insert(Customer customer);
	public Customer findByCustomerId(int custId);
	public List<Customer> findAllCustomers();
	public boolean deleteCustomer(Customer customer);
}




