package com.cg.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cg.crud.bean.Employee;
import com.cg.crud.exception.EmployeeException;
import com.cg.crud.util.DBConnection;

public class EmployeeDaoImpl implements EmployeeDao{

	@Override
	public void addEmployee(Employee employee) throws EmployeeException{
		// TODO Auto-generated method stub

		Connection connection = null; 

		PreparedStatement preparedStatement=null;		
		ResultSet resultSet = null;


		int queryResult=0;
		try
		{		
			connection = DBConnection.getConnection();	
			preparedStatement=connection.prepareStatement("insert into employee values (?,?,?,?)");

			preparedStatement.setInt(1,employee.getEmpId());			
			preparedStatement.setString(2,employee.getEmpName());
			preparedStatement.setInt(3,employee.getSalary());
			preparedStatement.setString(4, employee.getCity());

			queryResult=preparedStatement.executeUpdate();

			if(queryResult==0)
			{				
				throw new EmployeeException("Inserting employee details failed ");
			}		

		}
		catch(SQLException sqlException)
		{		sqlException.printStackTrace();	
			throw new EmployeeException("Tehnical problem occured");
		}

		finally
		{
			try 
			{
				if(connection!=null) {
					preparedStatement.close();
					connection.close();
				}
			}
			catch (SQLException sqlException) 
			{
				throw new EmployeeException("Error in closing db connection");

			}
		}		

	}

	@Override
	public List<Employee> getEmployees() throws EmployeeException{
		// TODO Auto-generated method stub
		Connection con=null;

		PreparedStatement ps=null;
		ResultSet resultset = null;

		List<Employee> empList=new ArrayList<>();
		try
		{
			con=DBConnection.getConnection();
			ps=con.prepareStatement("select * from employee");
			resultset=ps.executeQuery();

			while(resultset.next())
			{	
				Employee bean=new Employee();
				bean.setEmpId(resultset.getInt("empid"));
				bean.setEmpName(resultset.getString("empname"));
				bean.setSalary(resultset.getInt("salary"));
				bean.setCity(resultset.getString("city"));
				empList.add(bean);			

			}			

		} catch (SQLException sqlException) {

			throw new EmployeeException("Tehnical problem occured. Refer log");
		}

		finally
		{
			try 
			{
				if(con!=null) {
					resultset.close();
					ps.close();
					con.close();
				}
			} 
			catch (SQLException e) 
			{			
				throw new EmployeeException("Error in closing db connection");

			}
		}

		return empList;
	}


	@Override
	public int updateEmployee(Integer empId, String city) throws EmployeeException {
		Connection connection = null; 

		PreparedStatement preparedStatement=null;		
		ResultSet resultSet = null;	

		int queryResult=0;
		try
		{		
			connection = DBConnection.getConnection();	
			preparedStatement=connection.prepareStatement("update employee set city=? where empid=?");

			preparedStatement.setString(1,city);
			preparedStatement.setInt(2,empId);

			queryResult=preparedStatement.executeUpdate();



		}
		catch(SQLException sqlException)
		{			
			throw new EmployeeException("Tehnical problem occured");
		}

		finally
		{
			try 
			{
				if(connection!=null) {
					preparedStatement.close();
					connection.close();
				}
			}
			catch (SQLException sqlException) 
			{

				throw new EmployeeException("Error in closing db connection");

			}
		}
		return queryResult;

	}

	@Override
	public int deleteEmployee(Integer empId) throws EmployeeException {
		// TODO Auto-generated method stub
		Connection connection = null; 

		PreparedStatement preparedStatement=null;		
		ResultSet resultSet = null;	

		int queryResult=0;
		try
		{		
			connection = DBConnection.getConnection();	
			preparedStatement=connection.prepareStatement("delete from employee where empid=?");

			preparedStatement.setInt(1,empId);

			queryResult=preparedStatement.executeUpdate();


		}
		catch(SQLException sqlException)
		{			
			throw new EmployeeException("Tehnical problem occured");
		}

		finally
		{
			try 
			{
				if(connection!=null) {
					preparedStatement.close();
					connection.close();
				}
			}
			catch (SQLException sqlException) 
			{

				throw new EmployeeException("Error in closing db connection");

			}
		}
		return queryResult;
	}

}
