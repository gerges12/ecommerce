package com.example.ecomerce.website.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecomerce.website.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
	
		
}
