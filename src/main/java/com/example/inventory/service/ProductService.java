package com.example.inventory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.inventory.model.Product;
import com.example.inventory.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public Optional<Product> getProductById(Long id) {
		return productRepository.findById(id);
	}

	public Product addProduct(Product product) {
		Product savedProduct = productRepository.save(product);
		return savedProduct;
	}

	public Product updateProduct(Long id, Product updatedProduct) {
		Product existingProduct = productRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Product not found"));

		// put validation here later id id does not exist
		existingProduct.setName(updatedProduct.getName());
		existingProduct.setDescription(updatedProduct.getDescription());
		existingProduct.setCategory(updatedProduct.getCategory());
		existingProduct.setSku(updatedProduct.getSku());
		existingProduct.setBrand(updatedProduct.getBrand());
		existingProduct.setQuantity(updatedProduct.getQuantity());
		existingProduct.setUnitPrice(updatedProduct.getUnitPrice());
		existingProduct.setSellingPrice(updatedProduct.getSellingPrice());
		existingProduct.setReorderLevel(updatedProduct.getReorderLevel());
		existingProduct.setSupplier(updatedProduct.getSupplier());
		existingProduct.setBarcode(updatedProduct.getBarcode());
		existingProduct.setLocation(updatedProduct.getLocation());
		existingProduct.setLastUpdated(updatedProduct.getLastUpdated());

		return productRepository.save(existingProduct);
	}

	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}

}
