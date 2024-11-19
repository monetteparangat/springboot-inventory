package com.example.inventory.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.inventory.dto.CustomerDTO;
import com.example.inventory.model.Customer;
import com.example.inventory.model.Product;
import com.example.inventory.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private ModelMapper modelMapper;

	public List<CustomerDTO> getAllCustomers() {
		List<Customer> customers = customerRepository.findAll();
		List<CustomerDTO> customersDTO = customers.stream()
				.map(customer -> modelMapper.map(customer, CustomerDTO.class)).collect(Collectors.toList());
		return customersDTO;
	}

	public CustomerDTO getCustomerById(Long customerId) {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new RuntimeException("Customer not found"));

		CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
		return customerDTO;
	}

	public CustomerDTO addCustomer(CustomerDTO customerDTO) {
		Customer customer = modelMapper.map(customerDTO, Customer.class);
		Customer saveCustomer = customerRepository.save(customer);
		CustomerDTO savedCustomerDTO = modelMapper.map(saveCustomer, CustomerDTO.class);
		return savedCustomerDTO;
	}

	public CustomerDTO updateCustomer(Long customerId, CustomerDTO updatedCustomerDTO) {
		Customer existingCustomer = customerRepository.findById(customerId)
				.orElseThrow(() -> new RuntimeException("Customer not found"));

		// Update fields of the existing customer
		existingCustomer.setName(updatedCustomerDTO.getName());
		existingCustomer.setEmail(updatedCustomerDTO.getEmail());
		existingCustomer.setPhoneNumber(updatedCustomerDTO.getPhoneNumber());
		existingCustomer.setBillingAddress(updatedCustomerDTO.getBillingAddress());
		existingCustomer.setShippingAddress(updatedCustomerDTO.getShippingAddress());
		existingCustomer.setAccountType(updatedCustomerDTO.getAccountType());

		Customer savedCustomer = customerRepository.save(existingCustomer);
		CustomerDTO savedCustomerDTO = modelMapper.map(savedCustomer, CustomerDTO.class);

		return savedCustomerDTO;
	}

	public void deleteCustomer(Long customerId) {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new RuntimeException("Customer not found"));
		customerRepository.delete(customer);
	}
}
