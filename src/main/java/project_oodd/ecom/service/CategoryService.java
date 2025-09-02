package project_oodd.ecom.service;

import java.util.List;
import java.util.UUID;

import project_oodd.ecom.dto.CategoryDTO;
import project_oodd.ecom.model.Category;


public interface CategoryService {

	List<CategoryDTO> getCategory();
	CategoryDTO getCategoryById(UUID id);
	CategoryDTO createCategory(Category category);
	CategoryDTO updateCategory(UUID id, Category category);
    void deleteCategory(UUID id);
}
