package project_oodd.ecom.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import project_oodd.ecom.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	Optional<Category> findByCategoryCodeIgnoreCase(String name);
}
