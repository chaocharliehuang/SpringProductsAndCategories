package com.chaocharliehuang.productsandcategories.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chaocharliehuang.productsandcategories.models.*;
import com.chaocharliehuang.productsandcategories.repositories.ProductRepository;

@Service
public class ProductService {
	
	private ProductRepository productRepository;
	
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	public void addProduct(Product product) {
		productRepository.save(product);
	}
	
	public List<Product> getAllProducts() {
		return (List<Product>) productRepository.findAll();
	}
	
	public void updateProduct(Product product) {
		productRepository.save(product);
	}
	
	public Product findProductById(Long id) {
		return productRepository.findOne(id);
	}
	
	public List<Product> getProductsOfCategory(Category category) {
		return productRepository.findAllByCategoriesContaining(category);
	}
}
