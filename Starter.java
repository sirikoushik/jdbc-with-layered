package com.cg.crud.ui;

import java.util.List;
import java.util.Scanner;

import com.cg.crud.bean.Employee;
import com.cg.crud.exception.EmployeeException;
import com.cg.crud.service.EmployeeServiceImpl;

public class Starter {
	static EmployeeServiceImpl service = new EmployeeServiceImpl();
	public static void showMenu() {
		System.out.println("01. Add an Employee");
		System.out.println("02. Retrieve Employees");
		System.out.println("03. Update an Employee");
		System.out.println("04. Delete an Employee");
		System.out.println("05. Exit");
		System.out.print("Enter Your Choice : ");
	}
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int choice;
		while (true) {
			showMenu();
			choice = scanner.nextInt();
			switch (choice) {
			case 1:
				try {
					System.out.println("Enter Employee details:");
					System.out.println("Enter employee id");
					int eid = scanner.nextInt();
					scanner.nextLine();
					System.out.println("Enter employee name");
					String name = scanner.nextLine();
					System.out.println("Enter employee city");
					String city = scanner.nextLine();
					System.out.println("Enter employee salary");
					int salary = scanner.nextInt();
					//scanner.nextLine();
					Employee emp = new Employee();
					emp.setEmpId(eid);
					emp.setEmpName(name);
					emp.setCity(city);
					emp.setSalary(salary);
					service.addEmployee(emp);
					System.out.println("employee added successfully");
				}
				catch(EmployeeException e) {
					System.out.println("Error : "+e.getMessage());
				}
				break;

			case 2:
				try {
					System.out.println("Employee Details Are:");
					List<Employee> list=service.getEmployees();					
					for (Employee employee : list) 
						System.out.println( employee.getEmpId()+ " -- "+employee.getEmpName()+" --"+employee.getCity());

				}catch(EmployeeException e) {
					System.out.println("Error : "+e.getMessage());
				}
				break;
			case 3:
				try {
					System.out.println("Update Employee city for an employee:");
					System.out.println("Enter employee id");
					int empid = scanner.nextInt();
					scanner.nextLine();
					System.out.println("Enter new city");
					String newCity = scanner.nextLine();
					service.updateEmployee(empid, newCity);
					System.out.println("city details updated successfully");
				}catch(EmployeeException e) {
					System.out.println("Error : "+e.getMessage());
				}
				break;
			case 4:
				try{System.out.println("Deleting an Employee");
				System.out.println("Enter employee id to be deleted");
				int id = scanner.nextInt();				
				service.deleteEmployee(id);
				System.out.println("deleted successfully");
				}catch(Exception e) {
					System.out.println("Error : "+e.getMessage());
				}
				break;
			case 5:
				scanner.close();
				System.exit(0);

			default:
				System.out.println("Wrong Entry");

			}

		}
	}
}




