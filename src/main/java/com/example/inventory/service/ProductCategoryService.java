package com.example.inventory.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.inventory.dto.ProductCategoryDTO;
import com.example.inventory.dto.ProductDTO;
import com.example.inventory.model.Product;
import com.example.inventory.model.ProductCategory;
import com.example.inventory.model.Supplier;
import com.example.inventory.repository.ProductCategoryRepository;

@Service
public class ProductCategoryService {

	@Autowired
	private ProductCategoryRepository categoryRepository;
	@Autowired
	private ModelMapper modelMapper;

	public List<ProductCategoryDTO> getAllCategories() {
		List<ProductCategoryDTO> productCategoryDTO = categoryRepository.findAll().stream()
				.map(category -> modelMapper.map(category, ProductCategoryDTO.class)).collect(Collectors.toList());
		return productCategoryDTO;
	}

	public ProductCategoryDTO getCategoryById(Long id) {
		ProductCategory productCategory = categoryRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Category"));
		ProductCategoryDTO ProductCategoryDTO = modelMapper.map(productCategory, ProductCategoryDTO.class);
		return ProductCategoryDTO;
	}

	public ProductCategoryDTO addCategory(ProductCategoryDTO productCategoryDTO) {
		ProductCategory productCategory = modelMapper.map(productCategoryDTO, ProductCategory.class);
		ProductCategory savedCategory = categoryRepository.save(productCategory);
		ProductCategoryDTO savedProductDTO = modelMapper.map(savedCategory, ProductCategoryDTO.class);
		return savedProductDTO;
	}

	public ProductCategoryDTO updateCategory(Long id, ProductCategoryDTO productCategoryDTO) {
		ProductCategory existingCategory = categoryRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Category not found"));

		existingCategory.setName(productCategoryDTO.getName());
		existingCategory.setDescription(productCategoryDTO.getDescription());

		ProductCategory savedCategory = categoryRepository.save(existingCategory);
		ProductCategoryDTO savedCategoryDTO = modelMapper.map(savedCategory, ProductCategoryDTO.class);
		return savedCategoryDTO;
	}

	public void deleteCategory(Long id) {
		categoryRepository.deleteById(id);
	}

}
