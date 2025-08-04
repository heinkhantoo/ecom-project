package project_oodd.ecom.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import project_oodd.ecom.service.ProductService;
import project_oodd.ecom.util.ApiResponse;
import project_oodd.ecom.dto.ProductDTO;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping
	public ResponseEntity<ApiResponse<Map<String, Object>>> getAll() {
		List<ProductDTO> products = productService.getProducts();
		Map<String, Object> data = Map.of("data", products);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", products.size(), data);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Map<String, Object>>> getProductById(@PathVariable String id) {

		ProductDTO product = productService.getProductById(id);
		Map<String, Object> data = Map.of("data", product);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", data);
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<ApiResponse<Map<String, Object>>> createProduct(@RequestBody ProductDTO body) {

		ProductDTO product = productService.createProduct(body);
		Map<String, Object> data = Map.of("data", product);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", data);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<ApiResponse<Map<String, Object>>> updateProduct(@PathVariable String id,
			@RequestBody ProductDTO body) {
		ProductDTO product = productService.updateProduct(id, body);
		Map<String, Object> data = Map.of("data", product);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", data);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
		productService.deleteProduct(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
