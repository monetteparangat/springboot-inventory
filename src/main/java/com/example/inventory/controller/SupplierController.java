package com.example.inventory.controller;

import com.example.inventory.dto.SupplierDTO;
import com.example.inventory.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

	@Autowired
	private SupplierService supplierService;

	@GetMapping
	public List<SupplierDTO> getAllSuppliers() {
		return supplierService.getAllSuppliers();
	}

	@GetMapping("/{id}")
	public ResponseEntity<SupplierDTO> getSupplierById(@PathVariable Long id) {
		SupplierDTO supplier = supplierService.getSupplierById(id);
		return new ResponseEntity<>(supplier, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<SupplierDTO> createSupplier(@RequestBody SupplierDTO supplierDTO) {
		SupplierDTO supplier = supplierService.addSupplier(supplierDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(supplier);
	}

	@PutMapping("/{id}")
	public ResponseEntity<SupplierDTO> updateSupplier(@PathVariable Long id, @RequestBody SupplierDTO supplierDTO) {
		SupplierDTO supplier = supplierService.updateSupplier(id, supplierDTO);
		return ResponseEntity.ok(supplier);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
		supplierService.deleteSupplier(id);
		return ResponseEntity.noContent().build();
	}
}
