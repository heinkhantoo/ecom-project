package project_oodd.ecom.repository;

import project_oodd.ecom.model.Product;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {	
	Optional<Product> findByProductCodeIgnoreCase(String code);
}
