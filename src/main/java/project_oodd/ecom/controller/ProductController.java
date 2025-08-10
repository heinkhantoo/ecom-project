package project_oodd.ecom.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import project_oodd.ecom.service.ProductService;
import project_oodd.ecom.util.ApiResponse;
import project_oodd.ecom.util.Role;
import project_oodd.ecom.dto.ProductReqDTO;
import project_oodd.ecom.dto.ProductResDTO;
import project_oodd.ecom.model.User;
import project_oodd.ecom.security.RoleRestriction;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping
	public ResponseEntity<ApiResponse<Map<String, Object>>> getAll() {
		
		List<ProductResDTO> products = productService.getProducts();
		Map<String, Object> data = Map.of("data", products);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", products.size(), data);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Map<String, Object>>> getProductById(@PathVariable String id) {

		ProductResDTO product = productService.getProductById(id);
		Map<String, Object> data = Map.of("data", product);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", data);
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<ApiResponse<Map<String, Object>>> createProduct(@AuthenticationPrincipal User user,
			@RequestBody ProductReqDTO body) {

		RoleRestriction.restrictTo(user, Role.ADMIN,Role.MANAGER,Role.ASSISTANT);

		ProductResDTO product = productService.createProduct(body);
		Map<String, Object> data = Map.of("data", product);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", data);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<ApiResponse<Map<String, Object>>> updateProduct(@AuthenticationPrincipal User user,
			@PathVariable String id, @RequestBody ProductReqDTO body) {
		
		RoleRestriction.restrictTo(user, Role.ADMIN,Role.MANAGER,Role.ASSISTANT);
		
		ProductResDTO product = productService.updateProduct(id, body);
		Map<String, Object> data = Map.of("data", product);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", data);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@AuthenticationPrincipal User user, @PathVariable String id) {
		
		RoleRestriction.restrictTo(user, Role.ADMIN,Role.MANAGER,Role.ASSISTANT);
		
		productService.deleteProduct(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
