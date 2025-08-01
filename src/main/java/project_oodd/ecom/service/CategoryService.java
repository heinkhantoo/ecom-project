package project_oodd.ecom.service;

import java.util.List;

import project_oodd.ecom.dto.CategoryDTO;


public interface CategoryService {

	List<CategoryDTO> getCategory();
	CategoryDTO getCategoryById(String id);
	CategoryDTO createCategory(CategoryDTO color);
	CategoryDTO updateCategory(String id, CategoryDTO color);
    void deleteCategory(String id);
}
