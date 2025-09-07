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

	@PostMapping
	public ResponseEntity<ApiResponse<Map<String, Object>>> createProduct(@AuthenticationPrincipal User user,
			@RequestPart(value = "product") ProductReqDTO body,
			@RequestPart(value = "image", required = false) MultipartFile imageFile,
			@RequestPart(value = "variantImgs", required = false) List<MultipartFile> vImageFiles) {

		RoleRestriction.restrictTo(user, Role.ADMIN);

		ProductResDTO product = productService.createProduct(body, imageFile, vImageFiles);
		Map<String, Object> data = Map.of("data", product);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", data);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PostMapping("/{pid}/variants")
	public ResponseEntity<ApiResponse<Map<String, Object>>> createVariant(@AuthenticationPrincipal User user,
			@PathVariable UUID pid, @RequestPart(value = "variant") VariantReqDTO body,
			@RequestPart(value = "image", required = false) MultipartFile imageFile) {

		RoleRestriction.restrictTo(user, Role.ADMIN);

		VariantResDTO variant = productService.createVariant(pid, body, imageFile);
		Map<String, Object> data = Map.of("data", variant);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", data);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<ApiResponse<Map<String, Object>>> updateProduct(@AuthenticationPrincipal User user,
			@PathVariable UUID id, @RequestPart(value = "product", required = false) ProductReqDTO body,
			@RequestPart(value = "image", required = false) MultipartFile imageFile) {

		RoleRestriction.restrictTo(user, Role.ADMIN);

		ProductResDTO product = productService.updateProduct(id, body, imageFile);
		Map<String, Object> data = Map.of("data", product);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", data);
		return ResponseEntity.ok(response);
	}

	@PatchMapping("/{pid}/variants/{id}")
	public ResponseEntity<ApiResponse<Map<String, Object>>> updateVariant(@AuthenticationPrincipal User user,
			@PathVariable UUID pid, @PathVariable UUID id, @RequestPart(value = "variant", required = false) VariantReqDTO body,
			@RequestPart(value = "image", required = false) MultipartFile imageFile) {

		RoleRestriction.restrictTo(user, Role.ADMIN);

		VariantResDTO variant = productService.updateVariant(pid, id, body, imageFile);
		Map<String, Object> data = Map.of("data", variant);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", data);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@AuthenticationPrincipal User user, @PathVariable UUID id) {

		RoleRestriction.restrictTo(user, Role.ADMIN);

		productService.deleteProduct(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@DeleteMapping("/variants/{id}")
	public ResponseEntity<Void> deleteVariant(@AuthenticationPrincipal User user, @PathVariable UUID id) {

		RoleRestriction.restrictTo(user, Role.ADMIN);

		productService.deleteVariant(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
