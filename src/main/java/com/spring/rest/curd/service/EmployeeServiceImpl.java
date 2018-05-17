package com.spring.rest.curd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.rest.curd.dao.EmployeeDaoImpl;
import com.spring.rest.curd.entity.Employee;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDaoImpl daoImpl;
	
	@Override
	public void save(Employee employee) {
		daoImpl.save(employee);
	}

	@Override
	public void update(Employee employee) {
		daoImpl.update(employee);
	}

	@Override
	public void delete(int id) {
		daoImpl.delete(id);
	}

	@Override
	public Employee findById(int id) {
		return daoImpl.findById(id);
	}
	
	@Override
	public Employee findByName(String name) {
		return daoImpl.findByName(name);
	}

	@Override
	public List<Employee> findAll() {
		return daoImpl.findAll();
	}

	@Override
	public boolean isUserExist(Employee candidate) {
		 return daoImpl.findByName(candidate.getName())!=null;
	}
	
	
}
