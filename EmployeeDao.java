package com.cg.crud.dao;

import java.util.List;

import com.cg.crud.bean.Employee;
import com.cg.crud.exception.EmployeeException;

public interface EmployeeDao {
	public void addEmployee(Employee employee)throws EmployeeException;
	public List<Employee> getEmployees() throws EmployeeException;
	public int updateEmployee(Integer empId,String city) throws EmployeeException;
	public int deleteEmployee(Integer empId) throws EmployeeException;

}
