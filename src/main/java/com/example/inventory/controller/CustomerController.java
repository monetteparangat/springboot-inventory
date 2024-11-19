package com.example.inventory.controller;

import com.example.inventory.dto.CustomerDTO;
import com.example.inventory.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping
	public List<CustomerDTO> getAllCustomers() {
		List<CustomerDTO> customers = customerService.getAllCustomers();
		return customers;
	}

	@GetMapping("/{id}")
	public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
		CustomerDTO customer = customerService.getCustomerById(id);
		ResponseEntity<CustomerDTO> response = new ResponseEntity<>(customer, HttpStatus.OK);
		return response;
	}

	@PostMapping
	public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
		CustomerDTO customer = customerService.addCustomer(customerDTO);
		ResponseEntity<CustomerDTO> response = ResponseEntity.status(HttpStatus.CREATED).body(customer);
		return response;
	}

	@PutMapping("/{id}")
	public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
		CustomerDTO customer = customerService.updateCustomer(id, customerDTO);
		ResponseEntity<CustomerDTO> response = ResponseEntity.ok(customer);
		return response;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
		customerService.deleteCustomer(id);
		ResponseEntity<Void> response = ResponseEntity.noContent().build();
		return response;
	}
}
