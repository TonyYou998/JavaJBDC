package dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
	private static String url="jdbc:mysql://localhost:3306/customerdb";
	private static String username="root";
	private static String password="1234";
	private static Connection connection=null;
	public static Connection getConnection() {
		if(connection !=null)
			return connection;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection= DriverManager.getConnection(url, username, password); //thanh cong tra ve connection
			
		
		}
		
		catch(ClassNotFoundException ex) {
			ex.printStackTrace();
			System.out.println("not have driver");
			
		}
		catch(SQLException ex) {
			ex.printStackTrace();
			System.out.println("invalid url");
			
		}
		return connection;
	}
}
