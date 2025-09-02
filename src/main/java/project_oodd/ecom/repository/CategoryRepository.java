package project_oodd.ecom.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import project_oodd.ecom.model.Category;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
