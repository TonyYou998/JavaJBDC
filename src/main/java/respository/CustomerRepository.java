package respository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.mysql.cj.MysqlConnection;

import cybersoft.java12.jsp.model.Customer;
import dbconnection.MySQLConnection;

public class CustomerRepository {
	
	public List <Customer> findAllCustomer(){
		List<Customer> customers=new LinkedList<Customer>();
		
		
		try {
			Connection connection=MySQLConnection.getConnection();
			String query ="SELECT code,name,address,email FROM customer";
			Statement statement=connection.createStatement();
			ResultSet resultSet= statement.executeQuery(query);//tra ve ket qua
			while(resultSet.next()) {
				Customer customer =new Customer();
				customer.setCode(resultSet.getInt("code"));
				customer.setName(resultSet.getNString("name"));
				customer.setAddress(resultSet.getNString("address"));
				customer.setEmail(resultSet.getString("email"));
				customers.add(customer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("connection has been disconnected");
			
			e.printStackTrace();
		}
		return customers;
	}
	public Customer findCustomerByCode(int code) {
		Customer customer=null;
		try {
			Connection connection=MySQLConnection.getConnection();
			String qurey ="SELECT code,name,address,email FROM customer WHERE code = ?";
			PreparedStatement statement=connection.prepareStatement(qurey);
			statement.setInt(1, code);
			ResultSet resultSet=statement.executeQuery();
			while(resultSet.next()) {
				customer = new Customer();
				customer.setCode(resultSet.getInt("code"));;
				customer.setName(resultSet.getNString("name"));
				customer.setAddress(resultSet.getNString("address"));
				customer.setEmail(resultSet.getString("email"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Connection to database has been disconnected!");
			e.printStackTrace();
		}
		return customer;
		
	}
	
	public int deleteCustomer(Customer cus) {
			try{
				String query="DELETE FROM customer WHERE code=?";
				Connection connection=MySQLConnection.getConnection();
				PreparedStatement statement=connection.prepareStatement(query);
				statement.setInt(1, cus.getCode());
				statement.executeUpdate();
			}
			catch(Exception ex) {
				System.out.println(ex.toString());
			}
		return 1;
		
	}
	public int addNewCustomer(Customer cus) {
		
		try {
			Connection connection=MySQLConnection.getConnection();
			String query="INSERT INTO customer(name,address,email) value(?,?,?)";	
			PreparedStatement statement=connection.prepareStatement(query);
			statement.setString(1,cus.getName());
			statement.setString(2, cus.getAddress());
			statement.setString(3, cus.getEmail());
			statement.executeUpdate();
			
		}
		catch(Exception ex) {
			System.out.println(ex.toString());
		}
		
		return 1;
	}
	public void updateCustomer(Customer cus) {
		try {
			Connection connection=MySQLConnection.getConnection();
			String query="UPDATE customer SET name=?,address=?,email=? WHERE code=?";
			
			PreparedStatement statement=connection.prepareStatement(query);
			statement.setString(1,cus.getName());
			statement.setString(2, cus.getAddress());
			statement.setString(3, cus.getEmail());
			statement.setInt(4, cus.getCode());
			statement.executeUpdate();
			 
		}
		catch(Exception ex) {
			System.out.println(ex.toString());
		}
		
	}
	
}
