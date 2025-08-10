package project_oodd.ecom.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import project_oodd.ecom.model.User;
import project_oodd.ecom.security.RoleRestriction;
import project_oodd.ecom.service.SubCategoryService;
import project_oodd.ecom.util.ApiResponse;
import project_oodd.ecom.util.Role;

@RestController
@RequestMapping("/api/subcategory")
public class SubCategoryController {
	@Autowired
	private SubCategoryService subCategoryService;

	@GetMapping
	public ResponseEntity<ApiResponse<Map<String, Object>>> getAll(@AuthenticationPrincipal User user) {
		
		RoleRestriction.restrictTo(user, Role.ADMIN, Role.MANAGER);
		
		List<SubCategoryDTO> subcategory = subCategoryService.getSubCategory();
		Map<String, Object> data = Map.of("data", subcategory);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", subcategory.size(), data);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Map<String, Object>>> getSubCategoryById(@AuthenticationPrincipal User user,
			@PathVariable String id) {
		
		RoleRestriction.restrictTo(user, Role.ADMIN, Role.MANAGER);
		
		SubCategoryDTO subcategory = subCategoryService.getSubCategoryById(id);
		Map<String, Object> data = Map.of("data", subcategory);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", data);
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<ApiResponse<Map<String, Object>>> createcategory(@AuthenticationPrincipal User user,
			@RequestBody SubCategory body) {
		
		RoleRestriction.restrictTo(user, Role.ADMIN, Role.MANAGER);
		
		SubCategoryDTO subCategory = subCategoryService.createSubCategory(body);
		Map<String, Object> data = Map.of("data", subCategory);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", data);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<ApiResponse<Map<String, Object>>> updatecategory(@AuthenticationPrincipal User user,
			@PathVariable String id, @RequestBody SubCategory body) {
		
		RoleRestriction.restrictTo(user, Role.ADMIN, Role.MANAGER);
		
		SubCategoryDTO category = subCategoryService.updateSubCategory(id, body);
		Map<String, Object> data = Map.of("data", category);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", data);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@AuthenticationPrincipal User user, @PathVariable String id) {

		RoleRestriction.restrictTo(user, Role.ADMIN, Role.MANAGER);
		subCategoryService.deleteSubCategory(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
