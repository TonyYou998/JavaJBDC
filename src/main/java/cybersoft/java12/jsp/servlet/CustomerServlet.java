package cybersoft.java12.jsp.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cybersoft.java12.jsp.model.Customer;
import cybersoft.java12.jsp.service.CustomerService;
import cybersoft.java12.jsp.util.PathUtils;
import cybersoft.java12.jsp.util.UrlUtils;

@WebServlet(name = "customerServlet", urlPatterns = {
		UrlUtils.CUSTOMER_DASHBOARD,
		UrlUtils.CUSTOMER_ADD,
		UrlUtils.CUSTOMER_UPDATE,
		UrlUtils.CUSTOMER_DELETE
})
public class CustomerServlet extends HttpServlet {
	private CustomerService service;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String path = req.getServletPath();//lấy url nhưng ko bao gồm querystring
		
		switch (path) {
		case UrlUtils.CUSTOMER_DASHBOARD: 
			List<Customer> customers = service.findAllCustomers();
			
			
			req.setAttribute("customers", customers);
			
			req.getRequestDispatcher(PathUtils.CUSTOMER_DASHBOARD)
				.forward(req, resp);
			break;
			
		case UrlUtils.CUSTOMER_ADD:
			req.getRequestDispatcher(PathUtils.CUSTOMER_ADD)
			.forward(req, resp);
			break;
		
		case UrlUtils.CUSTOMER_UPDATE:
			int codeToUpdate=Integer.parseInt(req.getParameter("code"));
			Customer cus=service.findCustomerByCode(codeToUpdate);
			req.setAttribute("customer", cus);
			
			req.getRequestDispatcher(PathUtils.CUSTOMER_UPDATE)
			.forward(req, resp);
			break;
			
		case UrlUtils.CUSTOMER_DELETE:
			int codeToBeDeleted=Integer.parseInt(req.getParameter("code"));
			req.setAttribute("code", codeToBeDeleted);
				service.deleteCustomer(codeToBeDeleted);
				resp.sendRedirect(req.getContextPath()+UrlUtils.CUSTOMER_DASHBOARD);
			break;
		
		default:
			
		}
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = req.getServletPath();
		switch (path) {
		case UrlUtils.CUSTOMER_ADD:
			Customer newCustomer=new Customer();
			newCustomer.setCode(Integer.parseInt(req.getParameter("code")));
			
			newCustomer.setName(req.getParameter("name"));
			newCustomer.setAddress(req.getParameter("address"));
			newCustomer.setEmail(req.getParameter("email"));
			service.addNewCustomer(newCustomer);
			resp.sendRedirect(req.getContextPath()+UrlUtils.CUSTOMER_DASHBOARD);
			break;
		case UrlUtils.CUSTOMER_UPDATE:
			int code=Integer.parseInt(req.getParameter("code"));
			Customer cus=service.findCustomerByCode(code);
			cus.setName(req.getParameter("name"));
			cus.setAddress(req.getParameter("address"));
			cus.setEmail(req.getParameter("email"));
			
			service.updateCustomer(cus);
			
		/*	Customer cus=service.findCustomerByCode(code);
			cus.setName(req.getParameter("name"));
			cus.setEmail(req.getParameter("email"));
			cus.setAddress(req.getParameter("address"));*/
			
			resp.sendRedirect(req.getContextPath()+UrlUtils.CUSTOMER_DASHBOARD);
			break;
		}
	
		}
	
	@Override
	public void init() throws ServletException {
		super.init();
		service = new CustomerService();
	}
}
