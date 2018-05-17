package com.spring.rest.curd.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.spring.rest.curd.entity.Employee;
import com.spring.rest.curd.service.EmployeeService;


@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	// Employee List
	@RequestMapping(value={"/", "/home"}, method=RequestMethod.GET)
	public ResponseEntity<List<Employee>> home(Map<String,Object> view){
		List<Employee> list=employeeService.findAll();
		if(list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	// View Employee by using name
	@RequestMapping(value="/view/{name}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> viewEmployee(@PathVariable String name, Map<String,Object> view){
		Employee Employee=employeeService.findByName(name);
		 if (Employee == null) {
	            System.out.println("User with fname " + name +  " not found");
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<>(Employee, HttpStatus.OK);
	}
	
	// Delete Employee using id
	@RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Employee> deleteStudent(@PathVariable  int id){
		Employee Employee=employeeService.findById(id);
		if (Employee == null) {
            System.out.println("Unable to delete Employee with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
		employeeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	// Save New Employee
	@RequestMapping(value="/SaveEmployee", method=RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Void> saveEmployee(@RequestBody Employee Employee ,UriComponentsBuilder ucBuilder){
		if (employeeService.isUserExist(Employee)) {
            System.out.println("A User with first name " + Employee.getName() + " already exist");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
		employeeService.save(Employee);
		
		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/SaveEmployee/{id}").buildAndExpand(Employee.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	
	// Update Employee using id
	@RequestMapping(value="/edit/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
	public ResponseEntity<Employee> editStudent(@PathVariable  int id, @RequestBody Employee employee){	
		Employee emp = employeeService.findById(id);
		 if (emp==null) {
	            System.out.println("User with id " + id + " not found");
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }		 	
		 emp.setName(employee.getName());
		 emp.setAddress(employee.getAddress());
		 emp.setDob(employee.getDob());
		 emp.setMobile(employee.getMobile());
	     employeeService.update(emp);
	     return new ResponseEntity<>(emp, HttpStatus.OK);
	}
	
}
