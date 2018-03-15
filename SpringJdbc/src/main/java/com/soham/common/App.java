package com.soham.common;

import java.security.SecureRandom;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.soham.customer.dao.CustomerDAO;
import com.soham.customer.model.Customer;

public class App 
{
    public static void main( String[] args )
    {
    	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
    	int id = -1;
    	 
        CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");
        Customer customer = new Customer( "soham "+(char)((Math.random()*100)+65),(int)(Math.random()*100));
        customerDAO.insert(customer);
    	
        
        //Creating and inserting multiple Customers Randomly
        System.out.println("Creating RANDOM Users ==>>");
        SecureRandom random = new SecureRandom();
        String str = "";
        for(int i=0; i<10;i++){
        	str = "";
        	for(byte b: random.generateSeed(2)){
        		str = str+Byte.valueOf(b);
        	}
        	 
            customer = new Customer( "soham_"+str,(int)(Math.random()*100));
            customerDAO.insert(customer);
            System.out.println("User "+i+" customer="+customer);
        }
		
        
        
        
//        Customer customer1 = customerDAO.findByCustomerId(1);
//        System.out.println("\nCustimer Id: 1");
//        System.out.println(customer1);
        
        System.out.println("\nAll customers =\n");
        List<Customer> lstCustomers= customerDAO.findAllCustomers();
        for (Customer cust: lstCustomers) {
        	id = cust.getCustId();//always last element in the list
        	   System.out.println(cust);
		}
        
        
        System.out.println("\nDelete Custimer Id: "+id);
      //  customerDAO.deleteCustomer(customerDAO.findByCustomerId(id));
        
        System.out.println("\n==================================================== \n Now All customers after Delete =\n");
        List<Customer> lstCustomers1= customerDAO.findAllCustomers();
        for (Customer cust: lstCustomers1) {
        	   System.out.println(cust);
		}
    }
}
