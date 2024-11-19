package com.example.inventory.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.inventory.dto.SupplierDTO;
import com.example.inventory.model.Supplier;
import com.example.inventory.repository.SupplierRepository;

@Service
public class SupplierService {

	@Autowired
	private SupplierRepository supplierRepository;
	@Autowired
	private ModelMapper modelMapper;

	public List<SupplierDTO> getAllSuppliers() {
		List<Supplier> suppliers = supplierRepository.findAll();
		List<SupplierDTO> supplierDTO = suppliers.stream().map(supplier -> modelMapper.map(supplier, SupplierDTO.class))
				.collect(Collectors.toList());
		return supplierDTO;
	}

	public SupplierDTO getSupplierById(Long id) {
		Supplier supplier = supplierRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Supplier not found"));
		SupplierDTO supplierDTO = modelMapper.map(supplier, SupplierDTO.class);
		return supplierDTO;
	}

	public SupplierDTO addSupplier(SupplierDTO supplierDTO) {
		Supplier supplier = modelMapper.map(supplierDTO, Supplier.class);
		Supplier savedSupplier = supplierRepository.save(supplier);
		SupplierDTO savedSupplierDTO = modelMapper.map(savedSupplier, SupplierDTO.class);
		return savedSupplierDTO;
	}

	public SupplierDTO updateSupplier(Long id, SupplierDTO updatedSupplierDTO) {
		Supplier existingSupplier = supplierRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Supplier not found"));

		// validation if exists
		existingSupplier.setName(updatedSupplierDTO.getName());
		existingSupplier.setContactInfo(updatedSupplierDTO.getContactInfo());
		existingSupplier.setAddress(updatedSupplierDTO.getAddress());
		existingSupplier.setPaymentTerms(updatedSupplierDTO.getPaymentTerms());
		existingSupplier.setProductCategories(updatedSupplierDTO.getProductCategories());
		existingSupplier.setLeadTime(updatedSupplierDTO.getLeadTime());
		existingSupplier.setDiscountRates(updatedSupplierDTO.getDiscountRates());
		existingSupplier.setStatus(updatedSupplierDTO.getStatus());

		Supplier updatedSupplier = supplierRepository.save(existingSupplier);
		SupplierDTO supplierDTO = modelMapper.map(updatedSupplier, SupplierDTO.class);
		return supplierDTO;
	}

	public void deleteSupplier(Long supplierId) {
		Supplier supplier = supplierRepository.findById(supplierId)
				.orElseThrow(() -> new RuntimeException("Supplier not found"));
		supplierRepository.delete(supplier);
	}

}
