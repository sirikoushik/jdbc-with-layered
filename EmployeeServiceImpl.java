package com.cg.crud.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cg.crud.bean.Employee;
import com.cg.crud.dao.EmployeeDao;
import com.cg.crud.dao.EmployeeDaoImpl;
import com.cg.crud.exception.EmployeeException;

public class EmployeeServiceImpl implements EmployeeService {
	
	
	//------------------------ 1. Sample Application for Layered Architecture --------------------------
		/*******************************************************************************************************
		 - Function Name	:	addEmployee
		 - Input Parameters	:	Employee object
		 - Return Type		:	void
		 - Throws			:  	EmployeeException
		 - Author			:	CAPGEMINI
		 - Creation Date	:	10/07/2019
		 - Description		:	Validates the given employee object 
		  						if valid, invokes dao method addEmployee(employee) to add into repository
		 ********************************************************************************************************/
	@Override
	public void addEmployee(Employee employee) throws EmployeeException {
		
		EmployeeDao dao = new EmployeeDaoImpl();
		validateEmployee(employee);
		dao.addEmployee(employee);
		
	}

	@Override
	public List<Employee> getEmployees() throws EmployeeException{
		// TODO Auto-generated method stub
		EmployeeDao dao = new EmployeeDaoImpl();
		List<Employee>list= dao.getEmployees();
		if(list==null || list.size()==0)
			throw new EmployeeException("No Employee details found");
		return list;
	}

	@Override
	public void updateEmployee(Integer empId, String city) throws EmployeeException{
		// TODO Auto-generated method stub
		EmployeeDao dao = new EmployeeDaoImpl();
		int result=dao.updateEmployee(empId, city);
		if(result==0)
		{				
			throw new EmployeeException("No employee found for the given empid");
		}	
		
	}

	@Override
	public void deleteEmployee(Integer empId)throws EmployeeException {
		EmployeeDao dao = new EmployeeDaoImpl();
		int result=dao.deleteEmployee(empId);
		if(result==0) {
			throw new EmployeeException("No employee found for the given empid");
		}
		
	}
	
	/*******************************************************************************************************
	 - Function Name	: validateEmployee(Employee employee)
	 - Input Parameters	: Employee employee
	 - Return Type		: void
	 - Throws		    : EmployeeException
	 - Author	      	: CAPGEMINI
	 - Creation Date	: 10/07/2019
	 - Description		: validates the Employee object
	 ********************************************************************************************************/
	
	public void validateEmployee(Employee employee) throws EmployeeException
	{
		List<String> validationErrors = new ArrayList<String>();

		//Validating employee name
		if(!(isValidEmpName(employee.getEmpName()))) {
			validationErrors.add("\n Employee Name Should Be In Alphabets and minimum 3 characters long ! \n");
		}
		//Validating city
		if(!(isValidCity(employee.getCity()))){
			validationErrors.add("\n City Should Be In Alphabets and minimum 3 characters long ! \n");
		}
		
		
		if(!validationErrors.isEmpty())
			throw new EmployeeException(validationErrors +"");
	}

	public boolean isValidEmpName(String empName){
		Pattern namePattern=Pattern.compile("[A-Za-z]{3,}");
		Matcher nameMatcher=namePattern.matcher(empName);
		return nameMatcher.matches();
	}
	public boolean isValidCity(String city){
		Pattern namePattern=Pattern.compile("[A-Za-z]{3,}");
		Matcher nameMatcher=namePattern.matcher(city);
		return nameMatcher.matches();
	}
	
}
