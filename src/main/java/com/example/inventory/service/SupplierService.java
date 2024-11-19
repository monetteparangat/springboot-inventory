package com.example.inventory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.inventory.model.Supplier;
import com.example.inventory.repository.SupplierRepository;

@Service
public class SupplierService {

	@Autowired
	private SupplierRepository supplierRepository;

	public List<Supplier> getAllSuppler() {
		return supplierRepository.findAll();
	}

	public Optional<Supplier> getSupplierById(Long id) {
		return supplierRepository.findById(id);
	}

	public Supplier addSupplier(Supplier supplier) {
		return supplierRepository.save(supplier);
	}

	public Supplier updateSupplier(Long id, Supplier updatedSupplier) {
		Supplier existingSupplier = supplierRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Supplier not found"));

		// validation if exists
		existingSupplier.setName(updatedSupplier.getName());
		existingSupplier.setContactInfo(updatedSupplier.getContactInfo());
		existingSupplier.setAddress(updatedSupplier.getAddress());
		existingSupplier.setPaymentTerms(updatedSupplier.getPaymentTerms());
		existingSupplier.setProductCategories(updatedSupplier.getProductCategories());
		existingSupplier.setLeadTime(updatedSupplier.getLeadTime());
		existingSupplier.setDiscountRates(updatedSupplier.getDiscountRates());
		existingSupplier.setStatus(updatedSupplier.getStatus());

		return supplierRepository.save(existingSupplier);
	}

	public void deleteSupplier(Long supplierId) {
		supplierRepository.deleteById(supplierId);
	}

}
