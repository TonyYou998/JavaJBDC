package cybersoft.java12.jsp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbconnection.MySQLConnection;

@WebServlet(name="healthcheckservlet",urlPatterns = "/health")
public class HealthCheckServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection testConnection =MySQLConnection.getConnection();
		if(testConnection !=null)
			resp.getWriter().append("successful");
		else {
			resp.getWriter().append("unsuccessful");
		}
	}
}
