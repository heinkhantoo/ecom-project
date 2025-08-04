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

import project_oodd.ecom.dto.CategoryDTO;
import project_oodd.ecom.model.Category;
import project_oodd.ecom.service.CategoryService;
import project_oodd.ecom.util.ApiResponse;


@RestController
@RequestMapping("/api/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public ResponseEntity<ApiResponse<Map<String, Object>>> getAll() {
		List<CategoryDTO> category = categoryService.getCategory();
		Map<String, Object> data = Map.of("data", category);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", category.size(), data);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Map<String, Object>>> getCategoryById(@PathVariable String id) {

		CategoryDTO category = categoryService.getCategoryById(id);
		Map<String, Object> data = Map.of("data", category);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", data);
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<ApiResponse<Map<String, Object>>> createcategory(@RequestBody Category body) {

		CategoryDTO category = categoryService.createCategory(body);
		Map<String, Object> data = Map.of("data", category);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", data);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<ApiResponse<Map<String, Object>>> updatecategory(@PathVariable String id,
			@RequestBody Category body) {
		CategoryDTO category = categoryService.updateCategory(id, body);
		Map<String, Object> data = Map.of("data", category);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", data);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
		categoryService.deleteCategory(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
