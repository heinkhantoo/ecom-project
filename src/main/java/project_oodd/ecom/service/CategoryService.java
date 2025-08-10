package project_oodd.ecom.service;

import java.util.List;

import project_oodd.ecom.dto.CategoryDTO;
import project_oodd.ecom.model.Category;


public interface CategoryService {

	List<CategoryDTO> getCategory();
	CategoryDTO getCategoryById(String id);
	CategoryDTO createCategory(Category color);
	CategoryDTO updateCategory(String id, Category color);
    void deleteCategory(String id);
}
