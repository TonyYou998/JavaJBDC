package cybersoft.java12.jsp.service;


import java.util.LinkedList;
import java.util.List;

import cybersoft.java12.jsp.model.Customer;
import respository.CustomerRepository;

public class CustomerService {
	private List<Customer> customers;
	private CustomerRepository respository;
	public CustomerService() {
		respository=new CustomerRepository();
		
		/*customers = new LinkedList<Customer>();
		
		customers.add(new Customer(1, "VIP CUSTOMER", "police@gov.kt", "ISIS HQ"));
		customers.add(new Customer(2, "VIP CUSTOMER", "police@gov.kt", "ISIS HQ"));
		customers.add(new Customer(3, "VIP CUSTOMER", "police@gov.kt", "ISIS HQ"));
		customers.add(new Customer(4, "VIP CUSTOMER", "police@gov.kt", "ISIS HQ"));
		customers.add(new Customer(5, "VIP CUSTOMER", "police@gov.kt", "ISIS HQ"));
		customers.add(new Customer(6, "VIP CUSTOMER", "police@gov.kt", "ISIS HQ"));
		customers.add(new Customer(7, "VIP CUSTOMER", "police@gov.kt", "ISIS HQ"));
		customers.add(new Customer(8, "VIP CUSTOMER", "police@gov.kt", "ISIS HQ"));
		customers.add(new Customer(9, "VIP CUSTOMER", "police@gov.kt", "ISIS HQ"));*/
		
	}
	

	public List<Customer> findAllCustomers(){
		return respository.findAllCustomer();
	}
	/*public List<Customer> findAllCustomers(){
		return customers;
	}*/
	
	public Customer findCustomerByCode(int code) {
		
			return respository.findCustomerByCode(code);
			
				
		
		
		
	}
	
	public void deleteCustomer(int code) {
			
			respository.deleteCustomer(respository.findCustomerByCode(code));
		
	
	}
	
	public void updateCustomer(Customer cus) {
		respository.updateCustomer(cus);
		
	}
	public void addNewCustomer(Customer customer) {
		
		respository.addNewCustomer(customer);
	
		//customers.add(customer);
	}
}
