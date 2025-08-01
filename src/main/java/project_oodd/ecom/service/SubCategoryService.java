package project_oodd.ecom.service;

import java.util.List;

import project_oodd.ecom.dto.SubCategoryDTO;

public interface SubCategoryService {

	List<SubCategoryDTO> getSubCategory();
	SubCategoryDTO getSubCategoryById(String id);
	SubCategoryDTO createSubCategory(SubCategoryDTO color);
	SubCategoryDTO updateSubCategory(String id, SubCategoryDTO color);
    void deleteSubCategory(String id);
}
