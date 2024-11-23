package com.example.inventory.service;

import java.util.List;
import java.util.Locale.Category;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.inventory.dto.ProductDTO;
import com.example.inventory.model.Product;
import com.example.inventory.model.ProductCategory;
import com.example.inventory.model.Supplier;
import com.example.inventory.repository.ProductCategoryRepository;
import com.example.inventory.repository.ProductRepository;
import com.example.inventory.repository.SupplierRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private SupplierRepository supplierRepository;
	@Autowired
	private ProductCategoryRepository productCategoryRepository;
	@Autowired
	private ModelMapper modelMapper;

	public List<ProductDTO> getAllProducts() {
		List<ProductDTO> productsDTO = productRepository.findAll().stream()
				.map(product -> modelMapper.map(product, ProductDTO.class)).collect(Collectors.toList());
		return productsDTO;
	}

	public ProductDTO getProductById(Long id) {
		Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
		ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
		return productDTO;
	}

	public ProductDTO addProduct(ProductDTO productDTO) {
		Product product = modelMapper.map(productDTO, Product.class);
		Product savedProduct = productRepository.save(product);
		ProductDTO savedProductDTO = modelMapper.map(savedProduct, ProductDTO.class);
		return savedProductDTO;
	}

	public ProductDTO updateProduct(Long id, ProductDTO updatedProduct) {
		Product existingProduct = productRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Product not found"));

		// put validation here later id id does not exist
		Supplier supplier = null;
		ProductCategory productCategory = null;
		Long supplierId = updatedProduct.getSupplierId();
		Long categoryId = updatedProduct.getCategoryId();

		if (supplierId != null) {
			supplier = supplierRepository.findById(supplierId)
					.orElseThrow(() -> new RuntimeException("Supplier not found"));
		}

		if (categoryId != null) {
			productCategory = productCategoryRepository.findById(categoryId)
					.orElseThrow(() -> new RuntimeException("Category not found"));
		}

		existingProduct.setName(updatedProduct.getName());
		existingProduct.setDescription(updatedProduct.getDescription());
		existingProduct.setCategory(updatedProduct.getCategory());
		existingProduct.setSku(updatedProduct.getSku());
		existingProduct.setBrand(updatedProduct.getBrand());
		existingProduct.setQuantity(updatedProduct.getQuantity());
		existingProduct.setUnitPrice(updatedProduct.getUnitPrice());
		existingProduct.setSellingPrice(updatedProduct.getSellingPrice());
		existingProduct.setReorderLevel(updatedProduct.getReorderLevel());
		existingProduct.setSupplier(supplier);
		existingProduct.setProductCategory(productCategory);
		existingProduct.setBarcode(updatedProduct.getBarcode());
		existingProduct.setLocation(updatedProduct.getLocation());
		existingProduct.setLastUpdated(updatedProduct.getLastUpdated());

		Product savedProduct = productRepository.save(existingProduct);
		ProductDTO productDTO = modelMapper.map(savedProduct, ProductDTO.class);
		return productDTO;
	}

	public void deleteProduct(Long id) {
		productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
		productRepository.deleteById(id);
	}

}
