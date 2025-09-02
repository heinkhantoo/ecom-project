package project_oodd.ecom.repository;

import project_oodd.ecom.model.Product;
import project_oodd.ecom.model.SubCategory;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, UUID> {
	Optional<Product> findBySubCategory(SubCategory subcategory);
	Optional<Product> findByProductName(String name);
}
