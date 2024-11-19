package com.example.inventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.inventory.dto.OrderDTO;
import com.example.inventory.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping
	public List<OrderDTO> getAllOrders() {
		List<OrderDTO> orders = orderService.getAllOrders();
		return orders;
	}

	@GetMapping("/{id}")
	public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id, @RequestBody OrderDTO orderDTO) {
		OrderDTO order = orderService.getOrderById(id);
		ResponseEntity<OrderDTO> response = new ResponseEntity<>(order, HttpStatus.OK);
		return response;
	}

	@PostMapping
	public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
		OrderDTO order = orderService.addOrder(orderDTO);
		ResponseEntity<OrderDTO> response = ResponseEntity.status(201).body(order);
		return response;
	}

	@PutMapping("/{id}")
	public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long id, @RequestBody OrderDTO orderDTO) {
		OrderDTO order = orderService.updateOrder(id, orderDTO);
		ResponseEntity<OrderDTO> response = ResponseEntity.ok(order);
		return response;
	}

	@DeleteMapping
	public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
		orderService.deleteOrder(id);
		ResponseEntity<Void> response = ResponseEntity.noContent().build();
		return response;
	}
}
