package project_oodd.ecom.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import project_oodd.ecom.dto.SubCategoryDTO;
import project_oodd.ecom.exception.AppException;
import project_oodd.ecom.model.SubCategory;
import project_oodd.ecom.repository.SubCategoryRepository;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

	@Autowired
	private SubCategoryRepository subCategoryRespository;

	public List<SubCategoryDTO> getSubCategory() {
		return convertToDTO(subCategoryRespository.findAll());
	}

	public SubCategoryDTO getSubCategoryById(String code) {
		SubCategoryDTO cat = convertToDTO(subCategoryRespository.findBySubCategoryCodeIgnoreCase(code)
				.orElseThrow(() -> new AppException("SubCategory not found with this id", 404)));

		return cat;
	}

	public SubCategoryDTO createSubCategory(SubCategory data) {
		SubCategory cat = new SubCategory();
		cat.setSubCategoryName(data.getSubCategoryName());
		cat.setSubCategoryCode(data.getSubCategoryCode());
		return convertToDTO(subCategoryRespository.save(cat));
	}

	public SubCategoryDTO updateSubCategory(String code, SubCategory dto) {

		SubCategory cat = subCategoryRespository.findBySubCategoryCodeIgnoreCase(code)
				.orElseThrow(() -> new AppException("SubCategory not found with this id", 404));

		if (dto.getSubCategoryCode() != null)
			cat.setSubCategoryCode(dto.getSubCategoryCode());
		if (dto.getSubCategoryName() != null)
			cat.setSubCategoryName(dto.getSubCategoryName());

		return convertToDTO(subCategoryRespository.save(cat));
	}

	public void deleteSubCategory(String code) {
		SubCategory cat = subCategoryRespository.findBySubCategoryCodeIgnoreCase(code)
				.orElseThrow(() -> new AppException("SubCategory not found with this id", 404));
		subCategoryRespository.delete(cat);
	}

	public SubCategoryDTO convertToDTO(SubCategory cat) {
		SubCategoryDTO dto = new SubCategoryDTO();
		dto.setSubCategoryCode(cat.getSubCategoryCode());
		dto.setSubCategoryName(cat.getSubCategoryName());

		return dto;
	}

	public List<SubCategoryDTO> convertToDTO(List<SubCategory> cats) {
		if (cats == null || cats.isEmpty()) {
			return Collections.emptyList();
		}

		return cats.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

}
