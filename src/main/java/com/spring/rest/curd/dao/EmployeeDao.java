package com.spring.rest.curd.dao;

import java.util.List;

import com.spring.rest.curd.entity.Employee;

public interface EmployeeDao {

	public void save(Employee employee);
	public void update(Employee employee);
	public void delete(int id);
	public Employee findById(int id);
	public Employee findByName(String name);
	public List<Employee> findAll();
}
