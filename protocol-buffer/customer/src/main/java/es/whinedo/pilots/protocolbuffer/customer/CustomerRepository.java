package es.whinedo.pilots.protocolbuffer.customer;

import java.util.List;


import es.whinedo.pilots.protocolbuffer.customer.model.CustomerProto.Customer;

public class CustomerRepository {

	private List<Customer> customers;
	
	public CustomerRepository(List<Customer> customers) {
		this.customers = customers;
	}
	
	public Customer findById(int id) {
		return customers.stream().filter(it -> it.getId() ==id).findFirst().get();
	}
	
	public Customer findByPesel(String pesel) {
		return customers.stream().filter(it -> it.getPesel().equals(pesel)).findFirst().get();		
	}
	
	public List<Customer> findAll() {
		return customers;
	}
	
}
