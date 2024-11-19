package com.example.inventory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.inventory.model.Customer;
import com.example.inventory.model.Product;
import com.example.inventory.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	public Optional<Customer> getCustomerById(Long customerId) {
		return customerRepository.findById(customerId);
	}

	public Customer addCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	public Customer updateCustomer(Long customerId, Customer updatedCustomer) {
		Customer existingCustomer = customerRepository.findById(customerId)
				.orElseThrow(() -> new RuntimeException("Customer not found"));

		// Update fields of the existing customer
		existingCustomer.setName(updatedCustomer.getName());
		existingCustomer.setEmail(updatedCustomer.getEmail());
		existingCustomer.setPhoneNumber(updatedCustomer.getPhoneNumber());
		existingCustomer.setBillingAddress(updatedCustomer.getBillingAddress());
		existingCustomer.setShippingAddress(updatedCustomer.getShippingAddress());
		existingCustomer.setAccountType(updatedCustomer.getAccountType());

		return customerRepository.save(existingCustomer);
	}

	public void deleteCustomer(Long customerId) {
		customerRepository.deleteById(customerId);
	}
}
