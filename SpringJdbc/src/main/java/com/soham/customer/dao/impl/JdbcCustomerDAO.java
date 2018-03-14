package com.soham.customer.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.soham.customer.dao.CustomerDAO;
import com.soham.customer.model.Customer;

public class JdbcCustomerDAO implements CustomerDAO {
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void insert(Customer customer) {

		String sql = "INSERT INTO CUSTOMER " + "( NAME, AGE) VALUES ( ?, ?)";
		Connection conn = null;

		try {
			System.out.println("Values are :: Name=" + customer.getName() + " Age=" + customer.getAge());
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			// ps.setInt(1, customer.getCustId());
			// ps.setString(2, customer.getName());
			// ps.setInt(3, customer.getAge());

			ps.setString(1, customer.getName());
			ps.setInt(2, customer.getAge());

			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	public Customer findByCustomerId(int custId) {

		String sql = "SELECT * FROM CUSTOMER WHERE CUST_ID = ?";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, custId);
			Customer customer = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				customer = new Customer(rs.getInt("CUST_ID"), rs.getString("NAME"), rs.getInt("Age"));
			}
			rs.close();
			ps.close();
			return customer;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	@SuppressWarnings("finally")
	public List<Customer> findAllCustomers() {
		List<Customer> lstCustomers = new ArrayList<Customer>();
		String sql = "SELECT * FROM CUSTOMER";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			// ps.setInt(1, custId);
			Customer customer = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				customer = new Customer(rs.getInt("CUST_ID"), rs.getString("NAME"), rs.getInt("Age"));
				//System.out.println(customer);
				lstCustomers.add(customer);
			}
			rs.close();
			ps.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
			return lstCustomers;
		}

	}

	public boolean deleteCustomer(Customer customer) {
		System.out.println("in DELETE...." + customer.getCustId());

		boolean flag = false;
		String sql = "DELETE FROM CUSTOMER WHERE CUST_ID = " + customer.getCustId();

		Connection conn = null;
		Statement stmt = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.createStatement();

			stmt.executeUpdate(sql);
			flag = true;

		} catch (SQLException e) {
			// throw new RuntimeException(e);
			flag = false;

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

		return flag;
	}
}
