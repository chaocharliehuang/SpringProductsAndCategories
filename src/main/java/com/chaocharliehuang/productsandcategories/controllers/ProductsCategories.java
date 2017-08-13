package com.chaocharliehuang.productsandcategories.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chaocharliehuang.productsandcategories.models.*;
import com.chaocharliehuang.productsandcategories.services.*;

@Controller
@RequestMapping("/")
public class ProductsCategories {
	
	private ProductService productService;
	private CategoryService categoryService;
	
	public ProductsCategories(ProductService productService, CategoryService categoryService) {
		this.productService = productService;
		this.categoryService = categoryService;
	}

	@GetMapping("products/new")
	public String newProduct(@ModelAttribute("product") Product product) {
		return "newproduct.jsp";
	}
	
	@PostMapping("products/new")
	public String createProduct(
			@Valid @ModelAttribute("product") Product product,
			BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "newproduct.jsp";
		} else {
			productService.addProduct(product);
			return "redirect:/products/" + product.getId();
		}
	}
	
	@GetMapping("categories/new")
	public String newCategory(@ModelAttribute("category") Category cateogry) {
		return "newcategory.jsp";
	}
	
	@PostMapping("categories/new")
	public String createCategory(
			@Valid @ModelAttribute("category") Category category,
			BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "newcategory.jsp";
		} else {
			categoryService.addCategory(category);
			return "redirect:/categories/" + category.getId();
		}
	}
	
	@GetMapping("products/{id}")
	public String showProduct(
			@ModelAttribute("category") Category category,
			@PathVariable("id") Long id, Model model) {
		Product product = productService.findProductById(id);
		model.addAttribute("product", product);
		model.addAttribute("categories", categoryService.getAllCategories());
		model.addAttribute("productCategories",
				categoryService.getCategoriesOfProduct(product));
		return "product.jsp";
	}
	
	@PostMapping("products/{id}")
	public String addCategoryToProduct(
			@PathVariable("id") Long id,
			@RequestParam(value="name") Long categoryId,
			Model model) {
		Product product = productService.findProductById(id);
		Category category = categoryService.findCategoryById(categoryId);
		List<Category> categories = product.getCategories();
		if (!categories.contains(category)) {
			categories.add(category);
			product.setCategories(categories);
			productService.updateProduct(product);
		}
		return "redirect:/products/" + id;
	}
	
	@GetMapping("categories/{id}")
	public String showCategory(
			@ModelAttribute("product") Product product,
			@PathVariable("id") Long id, Model model) {
		Category category = categoryService.findCategoryById(id);
		model.addAttribute("category", category);
		model.addAttribute("products", productService.getAllProducts());
		model.addAttribute("categoryProducts",
				productService.getProductsOfCategory(category));
		return "category.jsp";
	}
	
	@PostMapping("categories/{id}")
	public String addProductToCategory(
			@PathVariable("id") Long id,
			@RequestParam(value="name") Long productId,
			Model model) {
		Product product = productService.findProductById(productId);
		Category category = categoryService.findCategoryById(id);
		List<Product> products = category.getProducts();
		if (!products.contains(product)) {
			products.add(product);
			category.setProducts(products);
			categoryService.updateCategory(category);
		}
		return "redirect:/categories/" + id;
	}
}
