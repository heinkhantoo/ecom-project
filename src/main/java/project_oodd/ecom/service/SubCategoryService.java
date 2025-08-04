package project_oodd.ecom.service;

import java.util.List;

import project_oodd.ecom.dto.SubCategoryDTO;
import project_oodd.ecom.model.SubCategory;

public interface SubCategoryService {

	List<SubCategoryDTO> getSubCategory();
	SubCategoryDTO getSubCategoryById(String code);
	SubCategoryDTO createSubCategory(SubCategory subCat);
	SubCategoryDTO updateSubCategory(String id, SubCategory subCat);
    void deleteSubCategory(String code);
}
