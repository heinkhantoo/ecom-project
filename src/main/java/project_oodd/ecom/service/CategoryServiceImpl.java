package project_oodd.ecom.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import project_oodd.ecom.dto.CategoryDTO;
import project_oodd.ecom.exception.AppException;
import project_oodd.ecom.model.Category;
import project_oodd.ecom.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
    private CategoryRepository categoryRespository;
	
	public List<CategoryDTO> getCategory() {
        return convertToDTO(categoryRespository.findAll());
    }

    public CategoryDTO getCategoryById(String code) {
        CategoryDTO cat = convertToDTO(categoryRespository.findByCategoryCodeIgnoreCase(code)
                .orElseThrow(() -> new AppException("Category not found with this id", 404)));
        
        return cat;
    }

    public CategoryDTO createCategory(Category data) {
    	Category cat = new Category();
        cat.setCategoryName(data.getCategoryName());
        cat.setCategoryCode(data.getCategoryCode());
        return convertToDTO(categoryRespository.save(cat));
    }

    public CategoryDTO updateCategory(String code, Category data) {

    	Category cat = categoryRespository.findByCategoryCodeIgnoreCase(code)
                .orElseThrow(() -> new AppException("Category not found with this id", 404));
        
        if (data.getCategoryCode() != null) cat.setCategoryCode(data.getCategoryCode());
        if (data.getCategoryName() != null) cat.setCategoryName(data.getCategoryName());

        return convertToDTO(categoryRespository.save(cat));
    }

    public void deleteCategory(String code) {
        Category cat = categoryRespository.findByCategoryCodeIgnoreCase(code)
                .orElseThrow(() -> new AppException("Category not found with this id", 404));
        categoryRespository.delete(cat);
    }
    
    public CategoryDTO convertToDTO(Category cat) {
    	CategoryDTO dto = new CategoryDTO();
        dto.setCategoryCode(cat.getCategoryCode());
        dto.setCategoryName(cat.getCategoryName());

        return dto;
    }
    
    public List<CategoryDTO> convertToDTO(List<Category> cats) {
        if (cats == null || cats.isEmpty()) {
            return Collections.emptyList();
        }

        return cats.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }
}
