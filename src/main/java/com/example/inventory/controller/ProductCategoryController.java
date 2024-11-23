package com.example.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.inventory.service.ProductCategoryService;
import com.example.inventory.dto.ProductCategoryDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-categories")
public class ProductCategoryController {

	@Autowired
	private ProductCategoryService productCategoryService;

	@GetMapping
	public ResponseEntity<List<ProductCategoryDTO>> getAllProductCategories() {
		List<ProductCategoryDTO> productCategories = productCategoryService.getAllCategories();
		return ResponseEntity.ok(productCategories);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductCategoryDTO> getProductCategoryById(@PathVariable Long id) {
		ProductCategoryDTO productCategory = productCategoryService.getCategoryById(id);
		return ResponseEntity.ok(productCategory);
	}

	@PostMapping
	public ResponseEntity<ProductCategoryDTO> createProductCategory(
			@RequestBody ProductCategoryDTO productCategoryDTO) {
		ProductCategoryDTO productCategory = productCategoryService.addCategory(productCategoryDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(productCategory);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductCategoryDTO> updateProductCategory(@PathVariable Long id,
			@RequestBody ProductCategoryDTO productCategoryDTO) {
		ProductCategoryDTO updatedProductCategory = productCategoryService.updateCategory(id, productCategoryDTO);
		return ResponseEntity.ok(updatedProductCategory);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProductCategory(@PathVariable Long id) {
		productCategoryService.deleteCategory(id);
		return ResponseEntity.noContent().build();

	}

}
