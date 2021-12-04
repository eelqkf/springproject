package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query(value = "select * from product where ca_id = ?",nativeQuery = true)
	public List<Product> selectCateSQL(int kind);

}
