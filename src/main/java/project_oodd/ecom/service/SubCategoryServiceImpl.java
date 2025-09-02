package project_oodd.ecom.service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import project_oodd.ecom.dto.SubCategoryReqDTO;
import project_oodd.ecom.dto.SubCategoryResDTO;
import project_oodd.ecom.exception.AppException;
import project_oodd.ecom.model.Category;
import project_oodd.ecom.model.SubCategory;
import project_oodd.ecom.repository.CategoryRepository;
import project_oodd.ecom.repository.SubCategoryRepository;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

	@Autowired
	private SubCategoryRepository subCategoryRespository;

	@Autowired
	private CategoryRepository categoryRepository;

	public List<SubCategoryResDTO> getSubCategory() {
		return convertToDTO(subCategoryRespository.findAll());
	}

	public SubCategoryResDTO getSubCategoryById(UUID id) {
		SubCategoryResDTO cat = convertToDTO(subCategoryRespository.findById(id)
				.orElseThrow(() -> new AppException("SubCategory not found with this id", 404)));

		return cat;
	}

	public SubCategoryResDTO createSubCategory(SubCategoryReqDTO data) {
		SubCategory cat = new SubCategory();
		cat.setSubCategoryName(data.getSubCategoryName());
		if (data.getCategory() != null) {
			Category category = categoryRepository.findById(data.getCategory())
					.orElseThrow(() -> new AppException("Please, create this type of category first!", 404));
			cat.setCategory(category);
		}
//		cat.setSubCategoryCode(data.getSubCategoryCode());
		return convertToDTO(subCategoryRespository.save(cat));
	}

	public SubCategoryResDTO updateSubCategory(UUID id, SubCategoryReqDTO data) {

		SubCategory cat = subCategoryRespository.findById(id)
				.orElseThrow(() -> new AppException("SubCategory not found with this id", 404));

//		if (dto.getSubCategoryCode() != null)
//			cat.setSubCategoryCode(dto.getSubCategoryCode());
		if (data.getSubCategoryName() != null)
			cat.setSubCategoryName(data.getSubCategoryName());
		if (data.getCategory() != null) {
			Category category = categoryRepository.findById(data.getCategory())
					.orElseThrow(() -> new AppException("Please, create this type of category first!", 404));
			cat.setCategory(category);
		}

		return convertToDTO(subCategoryRespository.save(cat));
	}

	public void deleteSubCategory(UUID id) {
		SubCategory cat = subCategoryRespository.findById(id)
				.orElseThrow(() -> new AppException("SubCategory not found with this id", 404));
		subCategoryRespository.delete(cat);
	}

	public SubCategoryResDTO convertToDTO(SubCategory cat) {
		SubCategoryResDTO dto = new SubCategoryResDTO();
		dto.setSid(cat.getScid());
		dto.setSubCategoryName(cat.getSubCategoryName());
		dto.setCategory(cat.getCategory().getCategoryName());

		return dto;
	}

	public List<SubCategoryResDTO> convertToDTO(List<SubCategory> cats) {
		if (cats == null || cats.isEmpty()) {
			return Collections.emptyList();
		}

		return cats.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

}
