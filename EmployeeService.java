package com.cg.crud.service;

import java.util.List;

import com.cg.crud.bean.Employee;
import com.cg.crud.exception.EmployeeException;

public interface EmployeeService {
	
	public void addEmployee(Employee employee) throws EmployeeException;
	public List<Employee> getEmployees() throws EmployeeException;
	public void updateEmployee(Integer empId,String city) throws EmployeeException;
	public void deleteEmployee(Integer empId) throws EmployeeException;
}
