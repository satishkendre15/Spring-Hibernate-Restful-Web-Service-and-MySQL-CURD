package com.spring.rest.curd.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.rest.curd.entity.Employee;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void save(Employee employee) {
		sessionFactory.getCurrentSession().save(employee);		
	}

	@Override
	public void update(Employee employee) {
		sessionFactory.getCurrentSession().update(employee);		
	}

	@Override
	public void delete(int id) {
		sessionFactory.getCurrentSession().createQuery("delete from Employee where id="+id).executeUpdate();		
	}

	@Override
	public Employee findById(int id) {		
		return sessionFactory.getCurrentSession().get(Employee.class,id);
	}

	
	@Override
	public Employee findByName(String name) {
		return (Employee) sessionFactory.getCurrentSession().createQuery("from Employee where name='"+name+"'").uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> findAll() {
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Employee.class);
		criteria.addOrder(org.hibernate.criterion.Order.asc("name"));
		return (List<Employee>) criteria.list();
//		return (List<Candidate>)sessionFactory.getCurrentSession().createCriteria(Candidate.class).addOrder(Order.asc("fname")).list();
	}

}
