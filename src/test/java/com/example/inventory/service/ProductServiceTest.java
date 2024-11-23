package com.example.inventory.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.example.inventory.dto.ProductDTO;
import com.example.inventory.model.Product;
import com.example.inventory.repository.ProductRepository;

public class ProductServiceTest {

	@Mock
	private ProductRepository productRepository;

	@Mock
	private ModelMapper modelMapper;

	@InjectMocks
	private ProductService productService;

	private Product product;
	private ProductDTO productDTO;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		product = new Product();
		product.setProductId(1L);
		product.setName("Test Product");
		product.setSellingPrice(100.0);

		productDTO = new ProductDTO();
		productDTO.setId(1L);
		productDTO.setName("Test Product");
		productDTO.setSellingPrice(100.0);
	}

	@Test
	public void testGetProductById() {
		// Simulate the repository returning a product
		when(productRepository.findById(1L)).thenReturn(Optional.of(product));
		
		// Simulate the ModelMapper mapping the product to ProductDTO
		when(modelMapper.map(product, ProductDTO.class)).thenReturn(productDTO);
		
		// Call the service method
		ProductDTO result = productService.getProductById(1L);
		
		// Assertions
		assertNotNull(result);
		assertEquals("Test Product", result.getName());
		verify(productRepository, times(1)).findById(1L);
	}
}
