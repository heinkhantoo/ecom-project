package project_oodd.ecom.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import project_oodd.ecom.service.ProductService;
import project_oodd.ecom.util.ApiResponse;
import project_oodd.ecom.util.FileStorageService;
import project_oodd.ecom.util.Role;
import project_oodd.ecom.dto.ProductReqDTO;
import project_oodd.ecom.dto.ProductResDTO;
import project_oodd.ecom.dto.VariantReqDTO;
import project_oodd.ecom.dto.VariantResDTO;
import project_oodd.ecom.model.User;
import project_oodd.ecom.security.RoleRestriction;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private FileStorageService fileStorageService;

	@GetMapping
	public ResponseEntity<ApiResponse<Map<String, Object>>> getAll() {

		List<ProductResDTO> products = productService.getProducts();
		Map<String, Object> data = Map.of("data", products);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", products.size(), data);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/variants")
	public ResponseEntity<ApiResponse<Map<String, Object>>> getAllVariants() {

		List<VariantResDTO> variants = productService.getVariants();
		Map<String, Object> data = Map.of("data", variants);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", variants.size(), data);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Map<String, Object>>> getProductById(@PathVariable UUID id) {

		ProductResDTO product = productService.getProductById(id);
		Map<String, Object> data = Map.of("data", product);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", data);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/variants/{id}")
	public ResponseEntity<ApiResponse<Map<String, Object>>> getVariantById(@PathVariable UUID id) {

		VariantResDTO variant = productService.getVariantById(id);
		Map<String, Object> data = Map.of("data", variant);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", data);
		return ResponseEntity.ok(response);
	}

	@PostMapping(consumes = { "multipart/form-data" })
	public ResponseEntity<ApiResponse<Map<String, Object>>> createProduct(@AuthenticationPrincipal User user,
			@RequestPart(value = "product", required = false) ProductReqDTO body,
			@RequestPart(value = "image", required = false) MultipartFile imageFile) {

		RoleRestriction.restrictTo(user, Role.ADMIN, Role.MANAGER, Role.ASSISTANT);

		if (imageFile != null && !imageFile.isEmpty()) {
	        String imageUrl = fileStorageService.storeFile(imageFile); // service to save file in "uploads/"
	        body.setImg(imageUrl); // set URL in DTO
	    }
		ProductResDTO product = productService.createProduct(body);
		Map<String, Object> data = Map.of("data", product);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", data);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PostMapping("/{pid}/variants")
	public ResponseEntity<ApiResponse<Map<String, Object>>> createVariant(@AuthenticationPrincipal User user,
			@PathVariable UUID pid, @RequestBody VariantReqDTO body) {

		RoleRestriction.restrictTo(user, Role.ADMIN, Role.MANAGER, Role.ASSISTANT);

		VariantResDTO variant = productService.createVariant(pid, body);
		Map<String, Object> data = Map.of("data", variant);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", data);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<ApiResponse<Map<String, Object>>> updateProduct(@AuthenticationPrincipal User user,
			@PathVariable UUID id, @RequestBody ProductReqDTO body) {

		RoleRestriction.restrictTo(user, Role.ADMIN, Role.MANAGER, Role.ASSISTANT);

		ProductResDTO product = productService.updateProduct(id, body);
		Map<String, Object> data = Map.of("data", product);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", data);
		return ResponseEntity.ok(response);
	}

	@PatchMapping("/{pid}/variants/{id}")
	public ResponseEntity<ApiResponse<Map<String, Object>>> updateProduct(@AuthenticationPrincipal User user,
			@PathVariable UUID id, @RequestBody VariantReqDTO body) {

		RoleRestriction.restrictTo(user, Role.ADMIN, Role.MANAGER, Role.ASSISTANT);

		VariantResDTO variant = productService.updateVariant(id, body);
		Map<String, Object> data = Map.of("data", variant);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", data);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@AuthenticationPrincipal User user, @PathVariable UUID id) {

		RoleRestriction.restrictTo(user, Role.ADMIN, Role.MANAGER, Role.ASSISTANT);

		productService.deleteProduct(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@DeleteMapping("/variants/{id}")
	public ResponseEntity<Void> deleteVariant(@AuthenticationPrincipal User user, @PathVariable UUID id) {

		RoleRestriction.restrictTo(user, Role.ADMIN, Role.MANAGER, Role.ASSISTANT);

		productService.deleteVariant(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
