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

    public CategoryDTO getCategoryById(String id) {
        CategoryDTO cat = convertToDTO(categoryRespository.findByCategoryCodeIgnoreCase(id)
                .orElseThrow(() -> new AppException("Category not found with this id", 404)));
        
        return cat;
    }

    public CategoryDTO createCategory(CategoryDTO dto) {
    	Category cat = new Category();
        cat.setCategoryName(dto.getCategoryName());
        cat.setCategoryCode(dto.getCategoryCode());
        return convertToDTO(categoryRespository.save(cat));
    }

    public CategoryDTO updateCategory(String id, CategoryDTO dto) {

    	Category cat = categoryRespository.findByCategoryCodeIgnoreCase(id)
                .orElseThrow(() -> new AppException("Category not found with this id", 404));
        
        if (dto.getCategoryCode() != null) cat.setCategoryCode(dto.getCategoryCode());
        if (dto.getCategoryName() != null) cat.setCategoryName(dto.getCategoryName());

        return convertToDTO(categoryRespository.save(cat));
    }

    public void deleteCategory(String id) {
        Category cat = categoryRespository.findByCategoryCodeIgnoreCase(id)
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
