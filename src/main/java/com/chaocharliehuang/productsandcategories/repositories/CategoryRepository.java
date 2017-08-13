package com.chaocharliehuang.productsandcategories.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.chaocharliehuang.productsandcategories.models.*;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
	List<Category> findAllByProductsContaining(Product product);
}
