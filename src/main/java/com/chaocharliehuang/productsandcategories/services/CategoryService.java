package com.chaocharliehuang.productsandcategories.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chaocharliehuang.productsandcategories.models.*;
import com.chaocharliehuang.productsandcategories.repositories.CategoryRepository;

@Service
public class CategoryService {

	private CategoryRepository categoryRepository;
	
	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	public void addCategory(Category category) {
		categoryRepository.save(category);
	}
	
	public List<Category> getAllCategories() {
		return (List<Category>) categoryRepository.findAll();
	}
	
	public void updateCategory(Category category) {
		categoryRepository.save(category);
	}
	
	public Category findCategoryById(Long id) {
		return categoryRepository.findOne(id);
	}
	
	public List<Category> getCategoriesOfProduct(Product product) {
		return categoryRepository.findAllByProductsContaining(product);
	}
}
