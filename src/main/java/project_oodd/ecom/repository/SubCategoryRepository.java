package project_oodd.ecom.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import project_oodd.ecom.model.SubCategory;

public interface SubCategoryRepository extends JpaRepository<SubCategory, UUID> {
}
