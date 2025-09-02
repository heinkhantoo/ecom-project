package project_oodd.ecom.service;

import java.util.List;
import java.util.UUID;

import project_oodd.ecom.dto.SubCategoryReqDTO;
import project_oodd.ecom.dto.SubCategoryResDTO;

public interface SubCategoryService {

	List<SubCategoryResDTO> getSubCategory();
	SubCategoryResDTO getSubCategoryById(UUID id);
	SubCategoryResDTO createSubCategory(SubCategoryReqDTO subCat);
	SubCategoryResDTO updateSubCategory(UUID id, SubCategoryReqDTO subCat);
    void deleteSubCategory(UUID id);
}
