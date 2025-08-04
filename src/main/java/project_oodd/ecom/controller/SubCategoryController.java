package project_oodd.ecom.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project_oodd.ecom.dto.SubCategoryDTO;
import project_oodd.ecom.model.SubCategory;
import project_oodd.ecom.service.SubCategoryService;
import project_oodd.ecom.util.ApiResponse;



@RestController
@RequestMapping("/api/subcategory")
public class SubCategoryController {
	@Autowired
	private SubCategoryService subCategoryService;

	@GetMapping
	public ResponseEntity<ApiResponse<Map<String, Object>>> getAll() {
		List<SubCategoryDTO> subcategory = subCategoryService.getSubCategory();
		Map<String, Object> data = Map.of("data", subcategory);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", subcategory.size(), data);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{code}")
	public ResponseEntity<ApiResponse<Map<String, Object>>> getSubCategoryById(@PathVariable String code) {

		SubCategoryDTO subcategory = subCategoryService.getSubCategoryById(code);
		Map<String, Object> data = Map.of("data", subcategory);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", data);
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<ApiResponse<Map<String, Object>>> createcategory(@RequestBody SubCategory body) {

		SubCategoryDTO subCategory = subCategoryService.createSubCategory(body);
		Map<String, Object> data = Map.of("data", subCategory);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", data);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PatchMapping("/{code}")
	public ResponseEntity<ApiResponse<Map<String, Object>>> updatecategory(@PathVariable String code,
			@RequestBody SubCategory body) {
		SubCategoryDTO category = subCategoryService.updateSubCategory(code, body);
		Map<String, Object> data = Map.of("data", category);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", data);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{code}")
	public ResponseEntity<Void> deleteProduct(@PathVariable String code) {
		subCategoryService.deleteSubCategory(code);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
