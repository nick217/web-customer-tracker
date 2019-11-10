package com.practice.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.practice.springdemo.entity.Customer;

@Repository // For Spring to component scan and find this repository
			// Also handle exception translation for us: converts checked exceptions into unchecked ones for us
			// Always applied to DAO implementations
public class CustomerDAOImpl implements CustomerDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional // Makes spring handles transaction start and commit
	public List<Customer> getCustomers() {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query
		Query<Customer> query = currentSession.createQuery("from Customer", Customer.class);

		// execute the query and get result list
		List<Customer> customers = query.getResultList();

		// return result list
		return customers;

	}

}
